package UI.TM;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author rsegui
 */
public class StrPanelDraw extends javax.swing.JPanel {

    JPopupMenu menu;

    /**
     *
     */
    public StrPanelDraw() {
        initComponents();
        //setSize(298, 77);
        setPreferredSize(new Dimension(298, 77));
        JMenuItem copy = new JMenuItem("Copy Cadena");
        copy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (show == null) {
                    System.out.println("No string added yet!");
                } else {
                    System.out.println(show);
                }
            }
        });
        menu = new JPopupMenu();
        menu.add(copy);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MostrarMenu(e);
            }
        });
    }

    private void MostrarMenu(MouseEvent e) {
        menu.show(this, e.getX(), e.getY());
    }
    String show;

    /**
     *
     * @param str
     * @param position
     */
    public void ActualizeString(String str) {
        strDrawLabel.setText("..." + str + "...");
        paint(getGraphics());
    }
    BufferedImage buffer;
    Dimension oldSize;
    Insets insets;

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        synchronized (this) {
            if (oldSize == null || oldSize != getSize()) {
                oldSize = getSize();
                buffer = new BufferedImage((int) oldSize.getWidth(), (int) oldSize.getHeight(), BufferedImage.TYPE_INT_ARGB);
            }
            Graphics2D bg = (Graphics2D) buffer.getGraphics();
            bg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            bg.setStroke(new BasicStroke(2.5f));
            super.paint(bg);
        }
        g.drawImage(buffer, 0, 0, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        strDrawLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 204, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusable(false);
        setLayout(new java.awt.GridLayout(1, 0));

        strDrawLabel.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        strDrawLabel.setForeground(new java.awt.Color(255, 255, 255));
        strDrawLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        strDrawLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/Marker.png"))); // NOI18N
        strDrawLabel.setText("Waiting...");
        strDrawLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        strDrawLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        strDrawLabel.setIconTextGap(0);
        strDrawLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        add(strDrawLabel);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel strDrawLabel;
    // End of variables declaration//GEN-END:variables
}
