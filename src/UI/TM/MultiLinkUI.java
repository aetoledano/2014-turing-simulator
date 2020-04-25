package UI.TM;

import LOGIC.TM.State;
import CONTROLLER.TM.tmController;
import SUPPORT.config;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author rsegui
 */
public class MultiLinkUI extends javax.swing.JDialog {

    DefaultComboBoxModel readModel;
    DefaultComboBoxModel writeModel;
    DefaultComboBoxModel actionModel;
    DefaultComboBoxModel stModel;
    int count = -1;
    tmController controller;
    int from;

    private void myInit(char[] allowedAlphabet, char[] alphabet, ArrayList<State> states) {
        count = allowedAlphabet.length;

        Character[] array = new Character[allowedAlphabet.length];
        for (int i = 0; i < allowedAlphabet.length; i++) {
            array[i] = new Character(allowedAlphabet[i]);
        }
        readModel = new DefaultComboBoxModel(array);
        read.setModel(readModel);

        array = new Character[alphabet.length];
        for (int i = 0; i < alphabet.length; i++) {
            array[i] = new Character(alphabet[i]);
        }
        writeModel = new DefaultComboBoxModel(array);
        write.setModel(writeModel);

        Character[] actions = new Character[2];
        actions[0] = 'R';
        actions[1] = 'L';
        actionModel = new DefaultComboBoxModel(actions);
        action.setModel(actionModel);
        State[] items = new State[states.size()];
        items = states.toArray(items);
        stModel = new DefaultComboBoxModel(items);
        state.setModel(stModel);
    }

    /**
     *
     * @param modal
     * @param allowedAlphabet
     * @param alphabet
     * @param states
     * @param from
     * @param tmController
     * @param point
     */
    public MultiLinkUI(boolean modal, char[] allowedAlphabet, char[] alphabet, ArrayList<State> states, int from, tmController tmController, Point point) {
        super();
        setModal(modal);
        initComponents();
        myInit(allowedAlphabet, alphabet, states);
        this.controller = tmController;
        this.from = from;
        pack();
        if (point != null) {
            point.x -= getWidth() / 2;
            point.y -= getHeight() / 2;
            setLocation(point);
        } else {
            setLocationRelativeTo(null);
        }
        read.addKeyListener(common);
        write.addKeyListener(common);
        action.addKeyListener(common);
        state.addKeyListener(common);
        done.addKeyListener(common);
        add.addKeyListener(common);
        GraphicsEnvironment ge
                = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if (gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
            setOpacity(0.7F);
        }
        read.setToolTipText(config.getText("UI.TM.MultiLinkUI.read"));
        write.setToolTipText(config.getText("UI.TM.MultiLinkUI.write"));
        action.setToolTipText(config.getText("UI.TM.MultiLinkUI.action"));
        state.setToolTipText(config.getText("UI.TM.MultiLinkUI.st"));
        done.setToolTipText(config.getText("UI.done"));
        add.setToolTipText(config.getText("UI.add.lnk"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JPanel();
        state = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        read = new javax.swing.JComboBox();
        dos_ptos = new javax.swing.JLabel();
        write = new javax.swing.JComboBox();
        coma = new javax.swing.JLabel();
        action = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        add = new javax.swing.JButton();
        done = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(89, 129, 189), 2, true));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.add(read);

        dos_ptos.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        dos_ptos.setText(":");
        jPanel1.add(dos_ptos);

        jPanel1.add(write);

        coma.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        coma.setText(",");
        jPanel1.add(coma);

        jPanel1.add(action);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        add.setBackground(new java.awt.Color(255, 255, 255));
        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/add7.png"))); // NOI18N
        add.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        add.setFocusable(false);
        add.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jToolBar1.add(add);

        done.setBackground(new java.awt.Color(255, 255, 255));
        done.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        done.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/Done.png"))); // NOI18N
        done.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        done.setFocusable(false);
        done.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        done.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneActionPerformed(evt);
            }
        });
        jToolBar1.add(done);

        jPanel2.add(jToolBar1);

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        if (count > 0) {
            try {
                controller.AddTransition(
                        from,
                        ((State) stModel.getSelectedItem()).id,
                        (Character) readModel.getSelectedItem(),
                        (Character) writeModel.getSelectedItem(),
                        (Character) actionModel.getSelectedItem());
                count--;
                Thread.sleep(10);
                readModel.removeElement(readModel.getSelectedItem());
                if (count == 0) {
                    DefaultComboBoxModel m = new DefaultComboBoxModel();
                    read.setModel(m);
                    m.addElement("{ }");
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(MultiLinkUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_addActionPerformed

    private void doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneActionPerformed
        this.dispose();
    }//GEN-LAST:event_doneActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox action;
    private javax.swing.JButton add;
    private javax.swing.JLabel coma;
    private javax.swing.JButton done;
    private javax.swing.JLabel dos_ptos;
    private javax.swing.JPanel fondo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox read;
    private javax.swing.JComboBox state;
    private javax.swing.JComboBox write;
    // End of variables declaration//GEN-END:variables
    KeyAdapter common = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (KeyEvent.VK_ENTER == e.getKeyChar()) {
                doneActionPerformed(null);
            }
            if (KeyEvent.VK_ESCAPE == e.getKeyChar()) {
                doneActionPerformed(null);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    };
}
