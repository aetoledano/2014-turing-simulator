package UI;

import CONTROLLER.GC;
import CONTROLLER.TM.tmController;
import CONTROLLER.TM.tmSave;
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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author rsegui
 */
public class StartUI extends javax.swing.JPanel implements reloadConfig {

    JTabbedPane container;
    ImageIcon tab_icon;

    /**
     *
     * @param container
     */
    public StartUI(JTabbedPane container) {
        initComponents();
        this.container = container;
        this.tab_icon = new javax.swing.ImageIcon(super.getClass().getResource("/IMGS/cct.png"));
        try {
            Cursor mouse = new Cursor(Cursor.HAND_CURSOR);
            newMachine.setCursor(mouse);
            newAutomata.setCursor(mouse);
            open.setCursor(mouse);
            examples.setCursor(mouse);
        } catch (Exception e) {
            System.out.println("Unable to load hand cursor");
        }
        reloadLang();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        newMachine = new javax.swing.JButton();
        examples = new javax.swing.JButton();
        open = new javax.swing.JButton();
        newAutomata = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(640, 480));

        newMachine.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        newMachine.setForeground(new java.awt.Color(255, 255, 255));
        newMachine.setText("Diseñar Maquina de Turing");
        newMachine.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        newMachine.setContentAreaFilled(false);
        newMachine.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        newMachine.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newMachine.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        newMachine.setIconTextGap(0);
        newMachine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newMachineMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                newMachineMouseExited(evt);
            }
        });
        newMachine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMachineActionPerformed(evt);
            }
        });
        jLayeredPane1.add(newMachine);
        newMachine.setBounds(400, 40, 270, 19);

        examples.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        examples.setForeground(new java.awt.Color(255, 255, 255));
        examples.setText("Ejemplos");
        examples.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        examples.setContentAreaFilled(false);
        examples.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        examples.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        examples.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        examples.setIconTextGap(0);
        examples.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                examplesMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                examplesMouseEntered(evt);
            }
        });
        examples.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examplesActionPerformed(evt);
            }
        });
        jLayeredPane1.add(examples);
        examples.setBounds(400, 130, 240, 19);

        open.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        open.setForeground(new java.awt.Color(255, 255, 255));
        open.setText("Abrir Proyecto");
        open.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        open.setContentAreaFilled(false);
        open.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        open.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        open.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        open.setIconTextGap(0);
        open.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                openMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                openMouseEntered(evt);
            }
        });
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        jLayeredPane1.add(open);
        open.setBounds(400, 100, 250, 19);

        newAutomata.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        newAutomata.setForeground(new java.awt.Color(255, 255, 255));
        newAutomata.setText("Diseñar Automata");
        newAutomata.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        newAutomata.setContentAreaFilled(false);
        newAutomata.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        newAutomata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newAutomata.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        newAutomata.setIconTextGap(0);
        newAutomata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newAutomataMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                newAutomataMouseExited(evt);
            }
        });
        jLayeredPane1.add(newAutomata);
        newAutomata.setBounds(400, 70, 250, 19);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/logo.png"))); // NOI18N
        jLayeredPane1.add(logo);
        logo.setBounds(-50, 40, 450, 210);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/matica.jpg"))); // NOI18N
        jLayeredPane1.add(fondo);
        fondo.setBounds(0, 0, 1440, 870);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newMachineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMachineActionPerformed
        NMS nms = new NMS();
        nms.setController(new tmController(config.getText("no.title") + Integer.toString(container.getTabCount())));
        container.addTab(nms.getController().getName(), tab_icon, nms);
        container.setSelectedIndex(container.getTabCount() - 1);
    }//GEN-LAST:event_newMachineActionPerformed

    private void newAutomataMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newAutomataMouseEntered
        newAutomata.setForeground(Color.yellow);
    }//GEN-LAST:event_newAutomataMouseEntered

    private void newAutomataMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newAutomataMouseExited
        newAutomata.setForeground(Color.white);
    }//GEN-LAST:event_newAutomataMouseExited

    private void openMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openMouseEntered
        open.setForeground(Color.yellow);
    }//GEN-LAST:event_openMouseEntered

    private void openMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openMouseExited
        open.setForeground(Color.white);
    }//GEN-LAST:event_openMouseExited

    private void examplesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_examplesMouseEntered
        examples.setForeground(Color.yellow);
    }//GEN-LAST:event_examplesMouseEntered

    private void examplesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_examplesMouseExited
        examples.setForeground(Color.white);
    }//GEN-LAST:event_examplesMouseExited

    private void newMachineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newMachineMouseEntered
        newMachine.setForeground(Color.yellow);
    }//GEN-LAST:event_newMachineMouseEntered

    private void newMachineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newMachineMouseExited
        newMachine.setForeground(Color.white);
    }//GEN-LAST:event_newMachineMouseExited

    private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
        try {
            NMS nms = new NMS();
            Object obj = helper.LoadFromFile();

            if ((obj != null)) {
                if (obj instanceof tmSave) {
                    GC gc = new tmController("");
                    gc.updateLoaderSystem((tmSave) obj);
                    nms.setController(gc);
                    if (nms.getController().Load() == GC.LOAD_OK) {
                        container.addTab(nms.getController().getName(), tab_icon, nms);
                        container.setSelectedIndex(container.getTabCount() - 1);
                        nms.paint(nms.getGraphics());
                    }
                }
            }
        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null,
                    config.getText("no.file.loaded.fu"),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    config.getText("no.file.loaded.ees"),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_openActionPerformed

    private void examplesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examplesActionPerformed
        samplePickUp_UI s;
        try {
            s = new samplePickUp_UI(container);
            container.addTab(config.getText("samples_page"), tab_icon, s);
            container.setSelectedComponent(s);
            container.remove(this);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(StartUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_examplesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton examples;
    private javax.swing.JLabel fondo;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel logo;
    private javax.swing.JButton newAutomata;
    private javax.swing.JButton newMachine;
    private javax.swing.JButton open;
    // End of variables declaration//GEN-END:variables

    @Override
    public void reloadLang() {
        newMachine.setText(config.getText("sui.newMachine"));
        newAutomata.setText(config.getText("sui.newAutomata"));
        open.setText(config.getText("sui.open"));
        examples.setText(config.getText("sui.examples"));
    }
}
