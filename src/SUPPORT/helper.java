package SUPPORT;

import CONTROLLER.TM.tmSave;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author rsegui
 */
public class helper {

    //Hola esta es mi clase ayudante
    private static Properties p;

    /**
     *
     * @param folder
     * @param open
     * @param tipo
     * @param name
     * @return
     */
    public static File Get_File(boolean folder, boolean open, String tipo, String name) {
        final JFileChooser ch = new JFileChooser();
        ch.setFileSystemView(ch.getFileSystemView());
        ch.setMultiSelectionEnabled(false);
        ch.setDialogTitle("Choose file");
        if (folder) {
            ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        } else {
            ch.setFileSelectionMode(JFileChooser.FILES_ONLY);
        }
        if (open) {
            FileFilter filter = getFilter(tipo);
            ch.setFileFilter(filter);
            ch.showOpenDialog(null);
        } else {
            if (name != null) {
                ch.setSelectedFile(new File(name));
            }
            ch.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getActionCommand().equals("CancelSelection")) {
                        ch.setSelectedFile(null);
                    }
                }
            });
            ch.showSaveDialog(null);
        }
        return ch.getSelectedFile();
    }

    private static FileFilter getFilter(String type) {
        FileFilter filter = null;
        if (type.equals("jajtp")) {
            filter = new FileNameExtensionFilter("Archivos Tipo \"JAJTP\"", "jajtp");
        } else if (type.equals("png")) {
            filter = new FileNameExtensionFilter("Imagenes Tipo PNG", "png");
        } else if (type.equals("txt")) {
            filter = new FileNameExtensionFilter("Archivos de texto", "txt");
        }
        return filter;
    }

    /**
     *
     * @return @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object LoadFromFile() throws IOException, ClassNotFoundException {
        File file = Get_File(false, true, "jajtp", null);
        if (file != null) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            Object obj;
            try {
                obj = in.readObject();
            } catch (NoClassDefFoundError err) {
                throw new ClassNotFoundException();
            }
            in.close();
            //carga de objetos de tipo maquina de turing
            if (obj instanceof tmSave) {
                ((tmSave) obj).setFile_addr(file.getAbsolutePath());
                String filname = file.getName().substring(0, file.getName().lastIndexOf('.'));
                ((tmSave) obj).setName(filname);
                return obj;
            }
            //carga de objetos de tipo automata
            //if (obj instanceof logic.automat.atSave){}    
        }
        return null;
    }

    /**
     *
     * @param obj
     * @param ext
     * @param dir
     * @param name
     * @return
     */
    public static String[] SaveToFile(Object obj, String ext, String dir, String name) {
        String[] dir_name = null;
        File file = null;
        if (dir == null) {
            file = helper.fixExt(helper.Get_File(false, false, ext, name), ext);
            if (file != null && file.exists()) {
                int opt = JOptionPane.showConfirmDialog(null, "Desea reemplazar \"" + file.getName() + "\"", "Advertencia", JOptionPane.YES_NO_OPTION);
                if (opt == JOptionPane.NO_OPTION) {
                    name = file.getName();
                    int contador = 1;
                    do {
                        file = new File(file.getParent() + "/copia " + contador + " - " + name);
                        contador++;
                    } while (file.exists());
                }
            }
        } else {
            file = new File(dir);
        }
        if (file == null) {
            return dir_name;
        }
        ObjectOutputStream st;
        try {
            st = new ObjectOutputStream(new FileOutputStream(file));
            st.writeObject(obj);
            st.close();
            dir_name = new String[2];
            dir_name[0] = file.getAbsolutePath();
            dir_name[1] = file.getName();
        } catch (IOException ex) {
            return null;
        }
        return dir_name;
    }

    /**
     *
     * @return
     */
    public static String getUsrName() {
        if (p == null) {
            p = System.getProperties();
        }
        return p.getProperty("user.name");
    }

    /**
     *
     * @return
     */
    public static String getWorkingDir() {
        if (p == null) {
            p = System.getProperties();
        }
        return p.getProperty("user.dir");
    }

    /**
     *
     * @return
     */
    public static String get_java_runtime_version() {
        if (p == null) {
            p = System.getProperties();
        }
        return p.getProperty("java.runtime.version");
    }

    /**
     *
     * @return
     */
    public static String get_OS_name() {
        if (p == null) {
            p = System.getProperties();
        }
        return p.getProperty("os.name");
    }

    /**
     *
     * @return
     */
    public static String get_Home_Dir() {
        if (p == null) {
            p = System.getProperties();
        }
        return p.getProperty("user.home");
    }

    /**
     *
     * @return
     */
    public static String get_FileSystem_Separator() {
        if (p == null) {
            p = System.getProperties();
        }
        return p.getProperty("file.separator");
    }

    /**
     *
     * @return
     */
    public static String getClassPath() {
        if (p == null) {
            p = System.getProperties();
        }
        return p.getProperty("java.class.path");
    }

    /**
     *
     * @param dir
     * @return
     */
    public static ImageIcon loadJARImage(String dir) {

        return null;
    }

    /**
     *
     * @param file
     * @param extension
     * @return
     */
    public static File fixExt(File file, String extension) {
        if (file != null) {
            String str = file.getAbsolutePath();
            String ext = null;
            String pChar = ".";
            if (str.indexOf(pChar) != -1) {
                ext = str.substring(str.lastIndexOf(pChar));
                if (ext.length() == 1) {
                    ext = "";
                } else {
                    ext = ext.substring(1);
                }
            }
            if (ext == null) {
                return new File(str + "." + extension);
            } else {
                if (ext.equalsIgnoreCase(extension)) {
                    return new File(file.getAbsolutePath());
                } else {
                    return new File(str + "." + extension);
                }
            }
        } else {
            return null;
        }
    }
}
