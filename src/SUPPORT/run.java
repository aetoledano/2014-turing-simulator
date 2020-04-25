
package SUPPORT;

import UI.MainUI;

/**
 *
 * @author rsegui
 */
public class run {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception ex) {
                }
                break;
            }
        }
        //</editor-fold>
        config.LoadConfig();
        UI.MainUI obj = new MainUI();
        obj.setVisible(true);
    }
}
