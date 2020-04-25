package UI;

import CONTROLLER.GC;
import CONTROLLER.TM.tmController;
import CONTROLLER.TM.tmSave;
import DBA.DBStoredObject;
import DBA.Manager;
import NMS.NMS;
import SUPPORT.config;
import SUPPORT.helper;
import SUPPORT.reloadConfig;
import java.awt.Color;
import java.awt.Cursor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author rsegui
 */
public class samplePickUp_UI extends javax.swing.JPanel implements reloadConfig {

    JTabbedPane container;
    ImageIcon tab_icon;
    Manager mgr;
    DBStoredObject obj;

    /**
     *
     * @param container
     */
    public samplePickUp_UI(JTabbedPane container) throws Exception {
        initComponents();
        this.container = container;
        this.tab_icon = new javax.swing.ImageIcon(super.getClass().getResource("/IMGS/cct.png"));
        reloadLang();
        mgr = new Manager("127.0.0.1", "jajtp", "bosz", "admin");
        ArrayList<String> lst = mgr.getAllMachineNamesAsList();
        DefaultComboBoxModel mod = new DefaultComboBoxModel(lst.toArray());
        comboBox.setModel(mod);
        comboBoxItemStateChanged(null);
        jScrollPane1.getViewport().setBackground(new Color(255, 255, 255, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        label_mname = new javax.swing.JLabel();
        label_mname1 = new javax.swing.JLabel();
        label_desc = new javax.swing.JLabel();
        alpha = new javax.swing.JLabel();
        sust_name = new javax.swing.JLabel();
        photo = new javax.swing.JLabel();
        ver = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        auth = new javax.swing.JButton();
        update = new javax.swing.JButton();
        sust_photo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        desc_area = new javax.swing.JTextArea();
        sust_alpha = new javax.swing.JTextField();
        comboBox = new javax.swing.JComboBox();
        logo = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(640, 480));

        label_mname.setForeground(new java.awt.Color(255, 255, 255));
        label_mname.setText("Máquinas Disponibles");
        jLayeredPane1.add(label_mname);
        label_mname.setBounds(10, 60, 170, 30);

        label_mname1.setForeground(new java.awt.Color(255, 255, 255));
        label_mname1.setText("Nombre:");
        jLayeredPane1.add(label_mname1);
        label_mname1.setBounds(10, 130, 220, 15);

        label_desc.setForeground(new java.awt.Color(255, 255, 255));
        label_desc.setText("Descripción:");
        jLayeredPane1.add(label_desc);
        label_desc.setBounds(10, 230, 220, 15);

        alpha.setForeground(new java.awt.Color(255, 255, 255));
        alpha.setText("Alfabeto:");
        jLayeredPane1.add(alpha);
        alpha.setBounds(10, 170, 80, 15);

        sust_name.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        sust_name.setForeground(new java.awt.Color(255, 255, 255));
        sust_name.setText("Sustituir Nombre");
        jLayeredPane1.add(sust_name);
        sust_name.setBounds(30, 150, 200, 16);

        photo.setForeground(new java.awt.Color(255, 255, 255));
        photo.setText("Vista Previa:");
        jLayeredPane1.add(photo);
        photo.setBounds(330, 60, 90, 15);

        ver.setBackground(new java.awt.Color(255, 255, 255));
        ver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/database-down-128x128.png"))); // NOI18N
        ver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ver.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ver.setIconTextGap(0);
        ver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verActionPerformed(evt);
            }
        });
        jLayeredPane1.add(ver);
        ver.setBounds(240, 60, 50, 60);

        edit.setBackground(new java.awt.Color(255, 255, 255));
        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/Pencil.png"))); // NOI18N
        edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        edit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        edit.setIconTextGap(0);
        jLayeredPane1.add(edit);
        edit.setBounds(120, 0, 30, 30);

        delete.setBackground(new java.awt.Color(255, 255, 255));
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/Banned_sign.png"))); // NOI18N
        delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        delete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        delete.setIconTextGap(0);
        jLayeredPane1.add(delete);
        delete.setBounds(180, 0, 30, 30);

        auth.setBackground(new java.awt.Color(255, 255, 255));
        auth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/Key.png"))); // NOI18N
        auth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        auth.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        auth.setIconTextGap(0);
        jLayeredPane1.add(auth);
        auth.setBounds(90, 0, 30, 30);

        update.setBackground(new java.awt.Color(255, 255, 255));
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/Upload.png"))); // NOI18N
        update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        update.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        update.setIconTextGap(0);
        jLayeredPane1.add(update);
        update.setBounds(150, 0, 30, 30);

        sust_photo.setForeground(new java.awt.Color(255, 255, 255));
        sust_photo.setText("No photo available.");
        jLayeredPane1.add(sust_photo);
        sust_photo.setBounds(330, 80, 300, 170);

        jScrollPane1.setBackground(new Color(255,255,255,0));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        desc_area.setEditable(false);
        desc_area.setBackground(new Color(255,255,255,0));
        desc_area.setColumns(20);
        desc_area.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        desc_area.setForeground(new java.awt.Color(255, 255, 255));
        desc_area.setLineWrap(true);
        desc_area.setRows(5);
        desc_area.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setViewportView(desc_area);

        jLayeredPane1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 250, 300, 140);

        sust_alpha.setBackground(new Color(255, 255, 255, 0)
        );
        sust_alpha.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        sust_alpha.setForeground(new java.awt.Color(255, 255, 255));
        sust_alpha.setBorder(null);
        jLayeredPane1.add(sust_alpha);
        sust_alpha.setBounds(30, 190, 260, 30);

        comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxItemStateChanged(evt);
            }
        });
        jLayeredPane1.add(comboBox);
        comboBox.setBounds(10, 90, 230, 30);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/miniLogo.png"))); // NOI18N
        jLayeredPane1.add(logo);
        logo.setBounds(-10, 0, 90, 40);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/Water web - Tom Kijas.jpg"))); // NOI18N
        jLayeredPane1.add(fondo);
        fondo.setBounds(0, 0, 1440, 870);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxItemStateChanged
        String name = (String) comboBox.getSelectedItem();
        try {
            obj = mgr.getDBStoredObjectWith(name);
            if (obj == null) {
                die();;
            } else {
                sust_name.setText(name);
                sust_alpha.setText(obj.getAlphabet());
                sust_photo.setText("");
                sust_photo.setSize(obj.getPhoto().getIconWidth(), obj.getPhoto().getIconHeight());
                sust_photo.setIcon(obj.getPhoto());
                desc_area.setText(obj.getDescription());
            }
        } catch (Exception ex) {
            die();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(samplePickUp_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboBoxItemStateChanged

    private void verActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verActionPerformed
        NMS nms = new NMS();
        GC gc = new tmController(obj.getName());
        nms.setController(gc);
        int outCode;
        if ((outCode = nms.getController().LoadFromDBStoredObject(obj)) == GC.LOAD_OK) {
            nms.setController(gc);
            container.addTab(nms.getController().getName(), tab_icon, nms);
            container.setSelectedIndex(container.getTabCount() - 1);
            nms.paint(nms.getGraphics());
        } else {
            System.err.println("Salida de error: in samplePickUp_UI.java" + outCode);
            JOptionPane.showMessageDialog(null, "Imposible cargar ejemplo", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_verActionPerformed

    private void die() {
        sust_name.setText("");
        sust_alpha.setText("");
        sust_photo.setText("");
        sust_photo.setText("");
        sust_photo.setIcon(null);
        desc_area.setText("");
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("[Vacio]");
        comboBox.setModel(model);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alpha;
    private javax.swing.JButton auth;
    private javax.swing.JComboBox comboBox;
    private javax.swing.JButton delete;
    private javax.swing.JTextArea desc_area;
    private javax.swing.JButton edit;
    private javax.swing.JLabel fondo;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_desc;
    private javax.swing.JLabel label_mname;
    private javax.swing.JLabel label_mname1;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel photo;
    private javax.swing.JTextField sust_alpha;
    private javax.swing.JLabel sust_name;
    private javax.swing.JLabel sust_photo;
    private javax.swing.JButton update;
    private javax.swing.JButton ver;
    // End of variables declaration//GEN-END:variables

    @Override
    public void reloadLang() {

    }
}
