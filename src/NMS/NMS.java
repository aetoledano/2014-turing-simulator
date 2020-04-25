package NMS;

import CONTROLLER.GC;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author rsegui
 */
public class NMS extends JScrollPane {

    /**
     *
     */
    public VisualController vh;

    // <editor-fold defaultstate="collapsed" desc="Builder">

    /**
     *
     */
        public NMS() {
        vh = new VisualController();
        ViewPort vp = new ViewPort();
        vp.setView(vh);
        setViewport(vp);
        vh.setContainerViewPort(vp);
        setAutoscrolls(true);
        setRequestFocusEnabled(true);
        setNextFocusableComponent(vh);
        setBorder(new EmptyBorder(0, 0, 0, 0));
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Controller Handler">

    /**
     *
     * @param controller
     */
        public void setController(GC controller) {
        vh.setController(controller);
    }

    /**
     *
     * @return
     */
    public GC getController() {
        return vh.getController();
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ViewPort with WHITE background - DoubleBuffered">

    /**
     *
     */
        public class ViewPort extends JViewport {

        Dimension oldSize;
        Image buffer;
        Graphics2D bg;

        /**
         *
         */
        public ViewPort() {
            setBackground(Color.white);
        }

        /**
         *
         * @param g
         */
        @Override
        public void paint(Graphics g) {
            if (isShowing()) {
                if (oldSize == null || buffer == null || oldSize != getSize()) {
                    oldSize = getSize();
                    buffer = createImage((int) oldSize.getWidth(), (int) oldSize.getHeight());
                    bg = (Graphics2D) buffer.getGraphics();
                }
                bg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                bg.setStroke(new BasicStroke(2.5f));
                super.paint(bg);
                g.drawImage(buffer, 0, 0, this);
            }
        }
    }

    /**
     *
     * @return
     * @throws HeadlessException
     */
    @Override
    public Point getMousePosition() throws HeadlessException {
        Point pos = viewport.getMousePosition();
        if (pos == null) {
            return pos;
        }
        pos.x += viewport.getViewPosition().x - 25;
        pos.y += viewport.getViewPosition().y - 25;
        return pos;
    }// </editor-fold>  

    /**
     *
     * @param type
     */
    public void TryToAdd(int type) {
        vh.TryToAdd(type);
    }

    /**
     *
     * @return
     */
    public int getVHW() {
        return vh.getWidth();
    }

    /**
     *
     * @return
     */
    public int getVHH() {
        return vh.getHeight();
    }
}
