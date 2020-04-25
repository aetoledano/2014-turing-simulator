package UI.TM;

import SUPPORT.config;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author rsegui
 */
public class ConfirmLinkUI extends javax.swing.JDialog {

    /**
     *
     */
    public DefaultComboBoxModel readModel;

    /**
     *
     */
    public DefaultComboBoxModel writeModel;

    /**
     *
     */
    public DefaultComboBoxModel actionModel;

    /**
     *
     */
    public boolean cancel;

    private void myInit(char[] allowedAlphabet, char[] alphabet) {
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

    }
    KeyAdapter common = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if (KeyEvent.VK_ESCAPE == e.getKeyChar()) {
                cancel = true;
                jButton1ActionPerformed(null);
            }
            if (KeyEvent.VK_ENTER == e.getKeyChar()) {
                cancel = false;
                jButton1ActionPerformed(null);
            }
        }
    };

    /**
     *
     * @param modal
     * @param allowedAlphabet
     * @param alphabet
     * @param point
     */
    public ConfirmLinkUI(boolean modal, char[] allowedAlphabet, char[] alphabet, Point point) {
        super();
        setModal(modal);
        initComponents();
        myInit(allowedAlphabet, alphabet);
        setTitle(config.getText("add.lnk.dialog.title"));
        pack();
        setResizable(false);
        read.addKeyListener(common);
        write.addKeyListener(common);
        action.addKeyListener(common);
        jButton1.addKeyListener(common);
        if (point != null) {
            point.x -= getWidth() / 2;
            point.y -= getHeight() / 2;
            setLocation(point);
        } else {
            setLocationRelativeTo(null);
        }
        GraphicsEnvironment ge
                = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if (gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
            setOpacity(0.7F);
        }
        read.setToolTipText(config.getText("UI.TM.MultiLinkUI.read"));
        write.setToolTipText(config.getText("UI.TM.MultiLinkUI.write"));
        action.setToolTipText(config.getText("UI.TM.MultiLinkUI.action"));
        jButton1.setToolTipText(config.getText("UI.add.lnk"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        read = new javax.swing.JComboBox();
        dos_ptos = new javax.swing.JLabel();
        write = new javax.swing.JComboBox();
        coma = new javax.swing.JLabel();
        action = new javax.swing.JComboBox();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(89, 129, 189), 2, true));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        read.setOpaque(false);
        jToolBar1.add(read);

        dos_ptos.setBackground(new java.awt.Color(255, 255, 255));
        dos_ptos.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        dos_ptos.setText(":");
        dos_ptos.setOpaque(true);
        jToolBar1.add(dos_ptos);

        write.setOpaque(false);
        jToolBar1.add(write);

        coma.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        coma.setText(",");
        jToolBar1.add(coma);

        action.setOpaque(false);
        jToolBar1.add(action);
        jToolBar1.add(filler1);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/Done.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setOpaque(false);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jPanel1.add(jToolBar1);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox action;
    private javax.swing.JLabel coma;
    private javax.swing.JLabel dos_ptos;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox read;
    private javax.swing.JComboBox write;
    // End of variables declaration//GEN-END:variables
}
