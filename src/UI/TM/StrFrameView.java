package UI.TM;

import java.awt.Insets;

/**
 *
 * @author rsegui
 */
public class StrFrameView extends javax.swing.JFrame {

    Insets insets;
    StrPanelDraw DrawPanel;

    /**
     *
     */
    public StrFrameView() {
        initComponents();
        setIconImage(new javax.swing.ImageIcon(super.getClass().getResource("/IMGS/FVI.png")).getImage());
        insets = this.getInsets();
        DrawPanel = new StrPanelDraw();
        add(DrawPanel);
        pack();
        setResizable(false);
    }

    /**
     *
     * @return
     */
    public StrPanelDraw getDrawPanel() {
        return DrawPanel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setTitle("StrFrameView");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
