package NMS;

import CONTROLLER.GC;
import NMS.NMS.ViewPort;
import SUPPORT.tools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author rsegui
 */
public class VisualController extends JComponent {

    NotifyCenter nc;
    Container container;
    int fx, fy, tx, ty, tta_type;
    Dimension dim;
    Color rect_color;
    boolean peforming_multiple_selection, tryingToAdd;

    // <editor-fold defaultstate="collapsed" desc="Builder">
    /**
     *
     */
    public VisualController() {
        container = new Container(this);
        rect_color = new Color(79, 129, 189, 75);
        setRequestFocusEnabled(true);
        dim = getSize();
        setBorder(new EmptyBorder(0, 0, 0, 0));
        AddEvents();
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Overriden Paint">
    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        container.paint((Graphics2D) g);
        if (peforming_multiple_selection) {
            g.setColor(rect_color);
            g.drawRect(Math.min(fx, tx), Math.min(fy, ty), Math.abs(tx - fx), Math.abs(ty - fy));
        } else {
            nc.Paint((Graphics2D) g);
        }
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Event Management">
    private void AddEvents() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!getController().isAnalyzerRunning()) {
                    if (!container.keyPressed(e.getKeyChar())) {
                    }
                }
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension s = getSize();
                if (!dim.equals(s)) {
                    Rectangle b = getBounds();
                    Rectangle c = container.getExportImageRectangle();
                    if (!b.contains(c)) {
                        dim = new Dimension((c.x + c.width + 50), (c.y + c.height + 50));
                        setPreferredSize(dim);
                        container.UpdateUI();
                    }
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!getController().isAnalyzerRunning()) {
                    requestFocus();
                    if (!container.mousePressed(e)) {
                        fx = e.getX();
                        fy = e.getY();
                        tx = fx;
                        ty = fy;
                        peforming_multiple_selection = true;
                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!getController().isAnalyzerRunning()) {
                    requestFocus();
                    if (!container.mouseClicked(e)) {
                        if (e.getButton() == 2 || e.getButton() == 3) {
                            invokeMenu(e);
                        } else {
                            if (tryingToAdd) {
                                tryingToAdd = false;
                                Point loc = e.getPoint();
                                loc.x -= 25;
                                loc.y -= 25;
                                if (tta_type == -1) {
                                    getController().addNode(loc);
                                } else {
                                    getController().addNode(tta_type, loc);
                                }
                            } else {
                                container.DeselectAll();
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!getController().isAnalyzerRunning()) {
                    requestFocus();
                    if (!container.mouseReleased(e)) {
                        tx = e.getX();
                        ty = e.getY();
                        peforming_multiple_selection = false;
                        container.performMultipleSelection(new Rectangle(Math.min(fx, tx), Math.min(fy, ty), Math.abs(tx - fx), Math.abs(ty - fy)));
                    }
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!getController().isAnalyzerRunning()) {
                    if (!container.mouseDragged(e)) {
                        tx = e.getX();
                        ty = e.getY();
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (!getController().isAnalyzerRunning()) {
                    container.mouseMoved(e);
                }
                nc.updateMousePosition(e.getPoint());
            }
        });
    }

    private void invokeMenu(MouseEvent e) {
        tools.showEH(this, e.getPoint());
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="setContainerViewPort">
    /**
     *
     * @param vp
     */
    public void setContainerViewPort(ViewPort vp) {
        container.view_port = vp;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Controller Handler">
    void setController(GC controller) {
        nc = new NotifyCenter(container.view_port);
        controller.setNotifyCenter(nc);
        container.setController(controller);
    }

    GC getController() {
        return container.getController();
    }// </editor-fold>

    void TryToAdd(int type) {
        tryingToAdd = true;
        tta_type = type;
    }

    /**
     *
     */
    public void SelectAll() {
        container.performMultipleSelection(new Rectangle(0, 0, getWidth(), getHeight()));
    }

    /**
     *
     * @return
     */
    public LinkedList<Integer> getSelectedNodes() {
        return container.getSelectedNumbers();
    }

    /**
     *
     * @param number
     */
    public void changeTypeOf(int number) {
        getController().changeType(number, container.nodes.get(number).changeType());
        container.UpdateView();
    }
}
