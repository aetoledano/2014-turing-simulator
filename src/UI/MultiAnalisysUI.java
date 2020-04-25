package UI;

import CONTROLLER.TM.tmController;
import CONTROLLER.GC;
import LOGIC.TM.*;
import SUPPORT.config;
import SUPPORT.helper;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author rsegui
 */
public class MultiAnalisysUI extends javax.swing.JDialog {

    GC gc;
    ArrayList<String> strings;
    String allowed;

    /**
     *
     * @param parent
     * @param modal
     * @param controller
     */
    public MultiAnalisysUI(java.awt.Frame parent, boolean modal, GC controller) {
        super(parent, modal);
        initComponents();
        setTitle(config.getText("multi.title"));
        allowed = controller.alphabet;
        gc = controller;
        jProgressBar1.setVisible(false);
        load.setVisible(false);
        setLocationRelativeTo(parent);
        jLabel1.setText(config.getText("multi.label1"));
        jLabel3.setText(config.getText("multi.label3"));
        jLabel2.setText(config.getText("alpha") + " -> {" + allowed + "}");
        gen.setToolTipText(config.getText("multi.gen"));
        open.setToolTipText(config.getText("multi.open"));
        save.setToolTipText(config.getText("multi.save"));
        play.setToolTipText(config.getText("multi.play"));
        pack();
        setIconImage(parent.getIconImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        edit = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        gen = new javax.swing.JButton();
        open = new javax.swing.JButton();
        save = new javax.swing.JButton();
        play = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jProgressBar1 = new javax.swing.JProgressBar();
        load = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        edit.setColumns(15);
        edit.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        edit.setForeground(new java.awt.Color(89, 129, 189));
        edit.setLineWrap(true);
        edit.setRows(5);
        edit.setWrapStyleWord(true);
        edit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                editKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(edit);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar1.setFloatable(false);
        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);
        jToolBar1.setOpaque(false);

        gen.setBackground(new java.awt.Color(255, 255, 255));
        gen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/gen.png"))); // NOI18N
        gen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        gen.setFocusable(false);
        gen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genActionPerformed(evt);
            }
        });
        jToolBar1.add(gen);

        open.setBackground(new java.awt.Color(255, 255, 255));
        open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/open.png"))); // NOI18N
        open.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        open.setFocusable(false);
        open.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        open.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        jToolBar1.add(open);

        save.setBackground(new java.awt.Color(255, 255, 255));
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/save.png"))); // NOI18N
        save.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        save.setFocusable(false);
        save.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        save.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jToolBar1.add(save);

        play.setBackground(new java.awt.Color(255, 255, 255));
        play.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/play.png"))); // NOI18N
        play.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        play.setFocusable(false);
        play.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        play.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });
        jToolBar1.add(play);

        jLabel1.setText("label");

        jLabel2.setText("label");

        jLabel3.setText("label");

        jToolBar2.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        jProgressBar1.setOrientation(1);
        jProgressBar1.setAlignmentX(0.0F);
        jProgressBar1.setAlignmentY(0.0F);
        jProgressBar1.setBorderPainted(false);
        jProgressBar1.setFocusable(false);
        jProgressBar1.setPreferredSize(new java.awt.Dimension(23, 146));
        jToolBar2.add(jProgressBar1);

        load.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/loading.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jToolBar2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(load)))
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(load))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        Thread t = new Thread(runtime_do);
        t.start();
    }//GEN-LAST:event_playActionPerformed

    private void editKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editKeyTyped
        String h = new String();
        h += evt.getKeyChar();
        if (!allowed.contains(h)) {
            evt.consume();
        }
    }//GEN-LAST:event_editKeyTyped

    private void genActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genActionPerformed
        Thread t = new Thread(runtime_gen);
        t.start();
    }//GEN-LAST:event_genActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        Thread t = new Thread(runtime_save);
        t.start();
    }//GEN-LAST:event_saveActionPerformed

    private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
        Thread t = new Thread(runtime_open);
        t.start();
    }//GEN-LAST:event_openActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea edit;
    private javax.swing.JButton gen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel load;
    private javax.swing.JButton open;
    private javax.swing.JButton play;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
    BufferedImage buf;
    Dimension old;

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        if (buf == null || old == null || old != getSize()) {
            old = getSize();
            buf = new BufferedImage((int) old.getWidth(), (int) old.getHeight(), BufferedImage.TYPE_INT_ARGB);
        }
        synchronized (this) {
            Graphics bg = buf.getGraphics();
            super.paint(bg);
        }
        g.drawImage(buf, 0, 0, this);
    }

    private String WS(String full) {
        int len = full.length();
        String clean = "";
        for (int i = 0; i < len; i++) {
            if (full.charAt(i) != ' '
                    && full.charAt(i) != '\r'
                    && full.charAt(i) != '\t') {
                clean += full.charAt(i);
            }
        }
        return clean;
    }

    Runnable runtime_save = new Runnable() {

        @Override
        public void run() {
            load.setVisible(true);
            open.setEnabled(false);
            save.setEnabled(false);
            gen.setEnabled(false);
            paint(getGraphics());
            File file = helper.Get_File(false, false, "txt", gc.getName() + " - " + config.getText("str.test"));
            if (file != null) {
                file = helper.fixExt(file, "txt");
                String str = edit.getText();
                BufferedWriter b = null;
                try {
                    b = new BufferedWriter(new FileWriter(file));
                    b.write(str);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, config.getText("no.file.saved.ees"), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        if (b != null) {
                            b.close();
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, config.getText("no.file.saved.ees"), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            load.setVisible(false);
            open.setEnabled(true);
            save.setEnabled(true);
            gen.setEnabled(true);
            paint(getGraphics());
        }
    };

    Runnable runtime_open = new Runnable() {

        @Override
        public void run() {
            load.setVisible(true);
            open.setEnabled(false);
            save.setEnabled(false);
            play.setEnabled(false);
            paint(getGraphics());
            File file = helper.Get_File(false, true, "txt", null);
            if (file != null) {
                BufferedReader b = null;
                String readed;
                StringBuffer sb;
                try {
                    sb = new StringBuffer();
                    b = new BufferedReader(new FileReader(file));
                    while ((readed = b.readLine()) != null) {
                        sb.append(readed + '\n');
                    }
                    edit.append(sb.toString());
                    paint(getGraphics());
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, config.getText("no.file.loaded.nf"), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, config.getText("no.file.loaded.ees"), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    if (b != null) {
                        try {
                            b.close();
                        } catch (IOException ex) {
                        }
                    }
                }
            }
            load.setVisible(false);
            open.setEnabled(true);
            save.setEnabled(true);
            play.setEnabled(true);
            paint(getGraphics());
        }
    };

    Runnable runtime_gen = new Runnable() {

        @Override
        public void run() {
            load.setVisible(true);
            open.setEnabled(false);
            save.setEnabled(false);
            gen.setEnabled(false);
            paint(getGraphics());
            Generador g = new Generador(((tmController) gc).getNet());
            ArrayList<String> s = g.Gen();
            String str = "";
            if (s.size() > 1) {
                str += s.get(0);
                for (int i = 1; i < s.size(); i++) {
                    str += "\n" + s.get(i);
                }
            } else {
                if (!s.isEmpty()) {
                    str = s.get(0);
                }
            }
            edit.append(str);
            load.setVisible(false);
            open.setEnabled(true);
            save.setEnabled(true);
            play.setEnabled(true);
            gen.setEnabled(true);
            paint(getGraphics());
        }
    };

    Runnable runtime_do = new Runnable() {
        @Override
        public void run() {
            load.setVisible(true);
            paint(getGraphics());
            play.setEnabled(false);
            gen.setEnabled(false);
            open.setEnabled(false);
            strings = new ArrayList<String>();
            String clean = WS(edit.getText());
            String array[] = clean.split("\n");
            if (array.length > 0) {
                strings.addAll(Arrays.asList(array));
                jProgressBar1.setVisible(true);
                paint(getGraphics());
            }
            load.setVisible(false);
            paint(getGraphics());
            gc.performMultiAnalysis(strings, jProgressBar1);
            jProgressBar1.setVisible(false);
            play.setEnabled(true);
            gen.setEnabled(true);
            open.setEnabled(true);
        }
    };
}
