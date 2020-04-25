package NMS;

import ACTION.MoveAction;
import CONTROLLER.GC;
import NMS.NMS.ViewPort;
import SUPPORT.tools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author rsegui
 */
public class Container implements vGCI {

    ViewPort view_port;
    LinkedList<Node> nodes;
    LinkedList<Connector> connectors;
    Iterator<Node> itn;
    Iterator<Connector> itc;
    private final Color ccolor;
    private final Color ncolor;
    boolean isAdding;
    GC controller;

    /**
     *
     */
    public VisualController parent;

    /**
     *
     * @param s
     */
    public Container(VisualController s) {
        nodes = new LinkedList<Node>();
        connectors = new LinkedList<Connector>();
        isAdding = false;
        parent = s;
        ccolor = new Color(79, 129, 189);
        ncolor = Color.black;
    }

    // <editor-fold defaultstate="collapsed" desc="Graphics Management">
    /**
     *
     * @param g
     */
    public void paint(Graphics2D g) {
        g.setColor(ccolor);
        itc = connectors.iterator();
        while (itc.hasNext()) {
            itc.next().paint(g, view_port);
        }
        g.setColor(ncolor);
        itn = nodes.iterator();
        Node n;
        int i = 0;
        while (itn.hasNext()) {
            n = itn.next();
            n.number = i;
            n.paint(g, view_port);
            i++;
        }
    }

    /**
     *
     */
    @Override
    public void UpdateView() {
        view_port.paint(view_port.getGraphics());
    }

    /**
     *
     */
    public void UpdateUI() {
        view_port.updateUI();
    }
    // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="Event Management">
    boolean keyPressed(char keyChar) {
        boolean working = true;
        LinkedList<Integer> nums = new LinkedList<Integer>();
        if (keyChar == KeyEvent.VK_DELETE) {
            working = false;
            Node i;
            itn = nodes.iterator();
            while (itn.hasNext()) {
                if ((i = itn.next()).isSelected) {
                    nums.add(i.number);
                }
            }
            if (nums.size() == nodes.size()) {
                controller.removeAll();
            } else {
                controller.removeSelection(nums);
            }
        }
        return !working;
    }

    private Point toUNDO;

    boolean mousePressed(MouseEvent e) {
        itn = nodes.iterator();
        Node i;
        boolean working = true;
        while (itn.hasNext() && working) {
            if ((i = itn.next()).isPointInside(e.getPoint())) {
                if (i.bounds.isInsideConnectingRingBounds(e.getPoint())) {
                    connectors.add(new Connector(i, e.getPoint()));
                    isAdding = true;
                    working = false;
                } else {
                    i.isPressed = true;
                    i.draggedAtX = e.getX() - i.loc.x;
                    i.draggedAtY = e.getY() - i.loc.y;
                    working = false;
                    toUNDO = new Point(i.loc.x, i.loc.y);
                }
            }
        }
        return !working;
    }

    boolean mouseReleased(MouseEvent e) {
        if (isAdding) {
            itc = connectors.iterator();
            Connector c;
            Node n;
            while (itc.hasNext() && isAdding) {
                if ((c = itc.next()).isCreating) {
                    itn = nodes.iterator();
                    while (itn.hasNext() && isAdding) {
                        if ((n = itn.next()).isPointInside(e.getPoint())) {
                            isAdding = false;
                            c.setEndingNode(n);
                            if (!controller.addLink(c.start, c.end, e.getLocationOnScreen())) {
                                itc.remove();
                            }
                        }
                    }
                    if (isAdding) {
                        isAdding = false;
                        itc.remove();
                    }
                }
            }
            UpdateView();
            controller.FireChange();
            return !isAdding;
        } else {
            itn = nodes.iterator();
            Node i = null;
            boolean working = true;
            while (itn.hasNext() && working) {
                if ((i = itn.next()).isPressed) {
                    i.isPressed = false;
                    working = false;
                    controller.putAction(new MoveAction(i.number, new Point(toUNDO.x, toUNDO.y), new Point(i.loc.x, i.loc.y)));
                }
            }
            if (i != null) {
                if (parent.getSize().width < i.loc.x || parent.getSize().height < i.loc.y) {
                    parent.setPreferredSize(
                            new Dimension(
                                    Math.max(parent.getSize().width, e.getX() + 150),
                                    Math.max(parent.getSize().height, e.getY() + 150)));
                    view_port.updateUI();
                }
            }
            controller.FireChange();
            return !working;
        }
    }

    boolean mouseDragged(MouseEvent e) {
        boolean working = true;
        if (isAdding) {
            itc = connectors.iterator();
            Connector c;
            while (itc.hasNext() && working) {
                if ((c = itc.next()).isCreating) {
                    c.updateEndPoint(e.getPoint());
                    working = false;
                }
            }
        }
        itn = nodes.iterator();
        Node i;
        while (itn.hasNext() && working) {
            if ((i = itn.next()).isPressed) {
                if (e.getX() - i.draggedAtX > 0 && e.getY() - i.draggedAtY > 0) {
                    i.setLocation(e.getX() - i.draggedAtX, e.getY() - i.draggedAtY);
                }
                working = false;
            }
        }
        UpdateView();
        return !working;
    }

    boolean mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            //double click
            boolean working = true;
            itn = nodes.iterator();
            Node i;
            while (itn.hasNext() && working) {
                if ((i = itn.next()).isPointInside(e.getPoint())) {
                    controller.changeType(i.number, i.changeType());
                    i.changeSelection();
                    working = false;
                }
            }
            UpdateView();
            return !working;
        } else {
            //simple click
            if (e.getButton() == 2 || e.getButton() == 3) {
                boolean working = true;
                itn = nodes.iterator();
                Node i;
                while (itn.hasNext() && working) {
                    if ((i = itn.next()).isPointInside(e.getPoint())) {
                        tools.showNode(view_port, view_port.getMousePosition(), i.number, e.getLocationOnScreen());
                        working = false;
                    }
                }
                itc = connectors.iterator();
                Connector c;
                while (itc.hasNext() && working) {
                    if ((c = itc.next()).isPointInside(e.getPoint())) {
                        tools.showLink(view_port, view_port.getMousePosition(), c.start.number, c.end.number);
                        working = false;
                    }
                }
                return !working;
            } else {
                boolean working = true;
                itn = nodes.iterator();
                Node i;
                while (itn.hasNext() && working) {
                    if ((i = itn.next()).isPointInside(e.getPoint())) {
                        i.changeSelection();
                        working = false;
                    }
                }
                itc = connectors.iterator();
                Connector c;
                while (itc.hasNext() && working) {
                    if ((c = itc.next()).isPointInside(e.getPoint())) {
                        controller.ModifyConnector(c.start.number, c.end.number, e.getLocationOnScreen());
                        working = false;
                    }
                }
                UpdateView();
                return !working;
            }
        }
    }

    void mouseMoved(MouseEvent e) {
        itn = nodes.iterator();
        Node i;
        boolean found = false, paint = false;
        int n = -1;
        while (itn.hasNext() && !found) {
            if ((i = itn.next()).isPointInside(e.getPoint())) {
                found = true;
                i.mouseIN();
                n = i.number;
            }
        }
        if (found) {
            itn = nodes.iterator();
            while (itn.hasNext()) {
                if ((i = itn.next()).number != n) {
                    i.mouseOUT();
                }
            }
            UpdateView();
        } else {
            itn = nodes.iterator();
            while (itn.hasNext()) {
                if ((i = itn.next()).isMouseInside) {
                    i.mouseOUT();
                    paint = true;
                }
            }
            if (paint) {
                UpdateView();
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Controller Handler">
    void setController(GC controller) {
        this.controller = controller;
        controller.setVisualSystem(this);
    }

    GC getController() {
        return controller;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Interaction">
    void performMultipleSelection(Rectangle selection) {
        itn = nodes.iterator();
        Node i;
        while (itn.hasNext()) {
            if (selection.contains((i = itn.next()).bounds.getRectBounds())) {
                if (!i.isSelected) {
                    i.changeSelection();
                }
            }
        }
        UpdateView();
    }

    LinkedList<Integer> getSelectedNumbers() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        itn = nodes.iterator();
        Node i;
        while (itn.hasNext()) {
            if ((i = itn.next()).isSelected) {
                list.add(i.number);
            }
        }
        return list;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Controller Interface">
    /**
     *
     * @param p
     */
    @Override
    public void AddNode(Point p) {
        if (nodes.isEmpty()) {
            nodes.add(new Node(0, p));
        } else {
            nodes.add(new Node(1, p));
        }
        UpdateView();
    }

    /**
     *
     * @param kind
     * @param p
     */
    @Override
    public void AddNode(int kind, Point p) {
        nodes.add(new Node(kind, p));
        UpdateView();
    }

    /**
     *
     * @param i
     * @param point
     */
    @Override
    public void AddConnector(Node i, Point point) {
        connectors.add(new Connector(i, point));
        UpdateView();
    }

    /**
     *
     * @param from
     * @param to
     */
    @Override
    public void AddConnector(Node from, Node to) {
        connectors.add(new Connector(from, to));
        UpdateView();
    }

    /**
     *
     * @param from
     * @param to
     */
    @Override
    public void AddConnector(int from, int to) {
        connectors.add(new Connector(nodes.get(from), nodes.get(to)));
        UpdateView();
    }

    /**
     *
     * @param from
     * @param to
     * @param txt
     */
    @Override
    public void AddConnector(int from, int to, String txt) {
        Connector c = new Connector(nodes.get(from), nodes.get(to));
        c.updateTxt(txt);
        connectors.add(c);
        UpdateView();
    }

    /**
     *
     * @param from
     * @param to
     * @param txt
     */
    @Override
    public void ModifyConnector(int from, int to, String txt) {
        itc = connectors.iterator();
        Connector c;
        boolean working = true;
        while (itc.hasNext() && working) {
            c = itc.next();
            if (c.start.number == from && c.end.number == to) {
                c.updateTxt(txt);
                working = false;
            }
        }
        if (working) {
            AddConnector(from, to, txt);
        }
        UpdateView();
    }

    /**
     *
     * @param number
     */
    @Override
    public void DelNode(int number) {
        itn = nodes.iterator();
        itc = connectors.iterator();
        Connector c;
        boolean working = true;
        while (itn.hasNext() && working) {
            if (itn.next().number == number) {
                itn.remove();
                while (itc.hasNext()) {
                    c = itc.next();
                    if (c.start.number == number || c.end.number == number) {
                        itc.remove();
                    }
                }
                working = false;
            }
        }
        UpdateView();
    }

    /**
     *
     * @param selection
     */
    @Override
    public void removeSelection(Integer selection[]) {
        Connector c;
        for (int i = selection.length - 1; i >= 0; i--) {
            nodes.remove(selection[i].intValue());
            itc = connectors.iterator();
            while (itc.hasNext()) {
                c = itc.next();
                if (c.start.number == selection[i].intValue() || c.end.number == selection[i].intValue()) {
                    itc.remove();
                }
            }
        }
        UpdateView();
    }

    /**
     *
     */
    @Override
    public void removeAll() {
        nodes.clear();
        connectors.clear();
        UpdateView();
    }

    /**
     *
     * @param from
     * @param to
     */
    @Override
    public void DelConnector(int from, int to) {
        itc = connectors.iterator();
        Connector c;
        boolean working = true;
        while (itc.hasNext() && working) {
            c = itc.next();
            if (c.start.number == from && c.end.number == to) {
                itc.remove();
                working = false;
            }
        }
        UpdateView();
    }

    /**
     *
     * @return
     */
    @Override
    public Rectangle getExportImageRectangle() {
        int x = Integer.MAX_VALUE,
                y = Integer.MAX_VALUE,
                xm = Integer.MIN_VALUE,
                ym = Integer.MIN_VALUE;
        itn = nodes.iterator();
        itc = connectors.iterator();
        if (!itn.hasNext() && !itc.hasNext()) {
            return new Rectangle(0, 0);
        }
        Node e;
        while (itn.hasNext()) {
            e = itn.next();
            if (x > e.bounds.MinimalX()) {
                x = e.bounds.MinimalX();
            }
            if (y > e.bounds.MinimalY()) {
                y = e.bounds.MinimalY();
            }
            if (xm < e.bounds.MaximalX()) {
                xm = e.bounds.MaximalX();
            }
            if (ym < e.bounds.MaximalY()) {
                ym = e.bounds.MaximalY();
            }
        }
        Connector c;
        while (itc.hasNext()) {
            c = itc.next();
            if (y > c.MinimalY()) {
                y = c.MinimalY();
            }
        }
        Rectangle r = new Rectangle(x, y, xm - x, ym - y);
        return r;
    }

    /**
     *
     * @param g
     */
    @Override
    public void PaintFor(Graphics2D g) {
        this.paint(g);
    }

    /**
     *
     */
    @Override
    public void DeselectAll() {
        itn = nodes.iterator();
        Node i;
        while (itn.hasNext()) {
            i = itn.next();
            if (i.isSelected) {
                i.changeSelection();
            }
        }
        UpdateView();
    }

    /**
     *
     * @param who
     */
    @Override
    public void SingleSelection(int who) {
        DeselectAll();
        itn = nodes.iterator();
        Node i;
        boolean working = true;
        while (itn.hasNext() && working) {
            i = itn.next();
            if (i.number == who) {
                i.changeSelection();
                working = false;
            }
        }
        UpdateView();
    }

    /**
     *
     * @return
     */
    @Override
    public Node[] getNodes() {
        Node[] array = new Node[nodes.size()];
        return nodes.toArray(array);
    }
    
    @Override
    public Point[] getLocations() {
        Point[] loc = new Point[nodes.size()];
        itn = nodes.iterator();
        int i = 0;
        while (itn.hasNext()){
            loc[i] = itn.next().loc;
            i++;
        }
        return loc;
    }
    
    // </editor-fold>

    
}
