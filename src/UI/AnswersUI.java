package UI;

import CONTROLLER.GC;
import CONTROLLER.TerminatingException;
import SUPPORT.config;
import java.awt.Image;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

/**
 *
 * @author rsegui
 */
public class AnswersUI extends javax.swing.JPanel {

    TerminatingException ex;
    DefaultComboBoxModel model;
    GC gc;
    final transient ImageIcon bad = new ImageIcon(getClass().getResource("/IMGS/bad.png")),
            good = new ImageIcon(getClass().getResource("/IMGS/good.png"));

    /**
     *
     * @param list
     * @param gc
     */
    public AnswersUI(LinkedList<TerminatingException> list, GC gc) {
        this.gc = gc;
        initComponents();
        model = new DefaultComboBoxModel(list.toArray());
        jComboBox1.setModel(model);
        jComboBox1ItemStateChanged(null);
    }

    /**
     *
     * @param ex
     * @param gc
     */
    public AnswersUI(TerminatingException ex, GC gc) {
        this.gc = gc;
        initComponents();
        model = new DefaultComboBoxModel();
        model.addElement(ex);
        jComboBox1.setModel(model);
        jComboBox1ItemStateChanged(null);
    }
    label inn, out;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        box = new javax.swing.JPanel();
        result = new javax.swing.JLabel();
        why = new javax.swing.JLabel();
        exp = new javax.swing.JLabel();
        img = new javax.swing.JLabel();
        inn = new label(config.getText("cad.in"));
        out = new label(config.getText("cad.out"));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Cadenas>" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        result.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        result.setText("Result:");
        result.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        why.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        why.setText("Razon:");

        exp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        exp.setText("exp:");

        img.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        img.setText("img");
        img.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        img.setIconTextGap(0);
        img.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout boxLayout = new javax.swing.GroupLayout(box);
        box.setLayout(boxLayout);
        boxLayout.setHorizontalGroup(
            boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(result)
            .addComponent(why)
            .addComponent(exp)
            .addComponent(img)
            .addComponent(inn)
            .addComponent(out)
        );
        boxLayout.setVerticalGroup(
            boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boxLayout.createSequentialGroup()
                .addComponent(result)
                .addGap(0, 0, 0)
                .addComponent(why)
                .addGap(0, 0, 0)
                .addComponent(exp)
                .addGap(0, 0, 0)
                .addComponent(inn)
                .addGap(0, 0, 0)
                .addComponent(out)
                .addGap(0, 0, 0)
                .addComponent(img))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(box, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    ImageIcon icon;
    Image tmp;
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        ex = ((TerminatingException) jComboBox1.getSelectedItem());
        if (ex.isIsForLangRecognition()) {
            result.setVisible(true);
            result.setText(config.getText("result") + ex.getResult() + ((ex.wasGoodEnd()) ? " (q ∈ F) " : " (q ∉ F) "));
            result.setIcon((ex.wasGoodEnd()) ? good : bad);
            /*∉∈δ*/
        } else {
            result.setVisible(false);
        }
        why.setText(config.getText("reason") + "δ(q" + ex.getState() + "," + ex.getChar() + ")" + ex.getReason() + "  " + config.getText("in.st") + ex.getState() + " " + config.getText("with.char") + ex.getChar().toString());
        exp.setText("      (" + config.getText("te1.exp") + ")");

        inn.ActualizeSTR(ex.getInitialSTR());
        out.ActualizeSTR(ex.getEndingSTR());

        gc.getVisualSystem().SingleSelection(ex.getState());
        icon = new ImageIcon(gc.getImageTile(true, 2048, 2048));
        tmp = null;
        if (icon.getIconWidth() > 400 && icon.getIconHeight() <= 200) {
            tmp = icon.getImage().getScaledInstance(400, -1, Image.SCALE_SMOOTH);
        }
        if (icon.getIconWidth() < 400 && icon.getIconHeight() > 200) {
            tmp = icon.getImage().getScaledInstance(-1, 200, Image.SCALE_SMOOTH);
        }
        if (icon.getIconWidth() > 400 && icon.getIconHeight() > 200) {
            tmp = icon.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        }
        if (tmp != null) {
            icon = new ImageIcon(tmp);
        }
        img.setIcon(icon);
        img.setText(config.getText("mach"));
    }//GEN-LAST:event_jComboBox1ItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel box;
    private javax.swing.JLabel exp;
    private javax.swing.JLabel img;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel result;
    private javax.swing.JLabel why;
    // End of variables declaration//GEN-END:variables
}
