/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBA;

import LOGIC.TM.tmNetWork;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author raven
 */
public class Manager {

    // nombre del controlador de JDBC y URL de la base de datos
    final String DRIVER = "org.postgresql.Driver";
    String URL_DB = "jdbc:postgresql://";

    //conexion
    Connection conexion = null; // maneja la conexión
    Statement inst = null; // instrucción de consulta
    ResultSet res = null; // maneja los resultados
    ResultSetMetaData metaData = null;
    String user, pass;

    public Manager(String db_server_ip, String db_name, String user, String pass) throws Exception {
        if (db_name == null || db_server_ip == null || user == null || pass == null) {
            throw new Exception("Error de acceso a datos: Parametro Null - Name/IP/User/Pass");
        }
        URL_DB += db_server_ip + "/" + db_name;
        this.user = user;
        this.pass = pass;
        Class.forName(DRIVER);
        validateDB();
    }

    public ArrayList<String> getAllMachineNamesAsList() {
        try {
            connectInit();
            //la consulta
            String sql = "select name from machine";
            res = inst.executeQuery(sql);
            int colIndex = res.findColumn("name");
            ArrayList<String> list = new ArrayList<>();
            while (res.next()) {
                list.add(res.getString(colIndex));
            }
            return list;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de acceso a datos!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // asegura que conjuntoResultados, instruccion y conexion estén cerrados
            tryTOclose();
        }
        return null;
    }

    public boolean uploadMachine(String name, String desc, String alpha, tmNetWork net, BufferedImage img, Point[] locations) {
        try {
            connectInit();
            //conversion de la red a un arreglo de bytes
            ObjectOutputStream netObjectStream = null;
            ByteArrayOutputStream st = new ByteArrayOutputStream();
            netObjectStream = new ObjectOutputStream(st);
            netObjectStream.writeObject(net);
            byte[] regBytes = st.toByteArray();
            netObjectStream.close();
            st.close();
            ByteArrayInputStream netbytearray = new ByteArrayInputStream(regBytes);

            //conversion de la imagen en un arreglo de bytes
            File fil = new File("tmp.img");
            ImageOutputStream out = new FileImageOutputStream(fil);
            ImageIO.write((RenderedImage) img,"png",out);
            FileInputStream imgin = new FileInputStream(fil);
            out.close();
            
            //conversion del arreglo de posiciones en arreglo de bytes
            ByteArrayOutputStream BAOSforLocations = new ByteArrayOutputStream();
            ObjectOutputStream OOSforLocations = new ObjectOutputStream(BAOSforLocations);
            OOSforLocations.writeObject(locations);
            byte[] locationsAsByteA = BAOSforLocations.toByteArray();
            BAOSforLocations.close();
            ByteArrayInputStream BAISforLocations = new ByteArrayInputStream(locationsAsByteA);
            
            PreparedStatement ps = null;
            String sql = "insert into machine (name, alpha, net, description,preview,locations) values (?,?,?,?,?,?)";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, alpha);
            ps.setBinaryStream(3, netbytearray, regBytes.length);
            ps.setString(4, desc);
            ps.setBinaryStream(5, imgin, (int)fil.length());
            ps.setBinaryStream(6, BAISforLocations,locationsAsByteA.length);
            if (ps.executeUpdate() == 1) {
                tryTOclose();
                return true;
            }
            imgin.close();
            fil.delete();
            tryTOclose();
            return false;
        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de acceso a datos!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            tryTOclose();
        }
        return false;
    }

    public DBStoredObject getDBStoredObjectWith(String name) throws Exception {
        try {
            connectInit();
            String sql = "select name,alpha,description,net,preview,locations from machine where name = ?";
            PreparedStatement ps = null;
            ps = conexion.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeQuery();
            res = ps.getResultSet();

            if (res.next()) {
                String alpha = res.getString(2);
                String desc = res.getString(3);
                
                //recuperar la red
                byte[] netBuf = res.getBytes(4);
                ByteArrayInputStream netBAIS = new ByteArrayInputStream(netBuf);
                ObjectInputStream netOIS = new ObjectInputStream(netBAIS);
                Object net = netOIS.readObject();
                netBAIS.close();
                
                //recuperar la imagen
                byte[] imgBuf = res.getBytes(5);
                Image img = ImageIO.read(new ByteArrayInputStream(imgBuf));
                
                //recuperar el arreglo de las locations
                byte[] locations = res.getBytes(6);
                ByteArrayInputStream BAISforLocations = new ByteArrayInputStream(locations);
                ObjectInputStream OISforLocations = new ObjectInputStream(BAISforLocations);
                Point[] loc = (Point[]) OISforLocations.readObject();
                BAISforLocations.close();
                
                return new DBStoredObject(name, alpha, desc, img, net,loc);
            } else {
                throw new Exception("No results were collected from DB");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de acceso a datos!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            tryTOclose();
        }
        return null;
    }

    private void validateDB() throws SQLException {
        conexion = DriverManager.getConnection(URL_DB, user, pass);
        Properties p=conexion.getClientInfo();
        p.list(System.out);
    }

    private void connectInit() throws SQLException {
        //connectar con la base de datos
        conexion = DriverManager.getConnection(URL_DB, user, pass);
        //para realizar consultas es necesario crear un objeto Statement
        inst = conexion.createStatement();
    }

    private void tryTOclose() {
        try {
            if (res != null) {
                res.close();
            }
            if (inst != null) {
                inst.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } // fin de try
        catch (SQLException excepcion) {
            JOptionPane.showMessageDialog(null,
                    excepcion.getMessage(),
                    "Error de acceso a datos!",
                    JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, excepcion);
        } // fin de catch
    }

}
