package NMS;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
import javax.swing.ImageIcon;

/**
 *
 * @author rsegui
 */
public class Connector {

    private final Image lazo;
    private final int lw, lh;

    /**
     *
     */
    public Node start,

    /**
     *
     */
    end;

    /**
     *
     */
    public Polygon bounds;
    private double r, angle;
    private int a1p, a2p, common_point, irange, lp;
    private final int ratio;

    /**
     *
     */
    public boolean isCreating;
    private Point endPoint;

    /**
     *
     */
    public String txt[];
    private final Font font;

    /**
     *
     * @param i
     * @param f
     */
    public Connector(Node i, Node f) {
        ImageIcon tmp;
        tmp = new ImageIcon(super.getClass().getResource("/IMGS/lazo.png"));
        lazo = tmp.getImage();
        lw = tmp.getIconWidth();
        lh = tmp.getIconHeight();
        start = i;
        end = f;
        bounds = new Polygon();
        r = start.bounds.width / 2;
        ratio = 3;
        txt = null;
        font = new Font("Dialog", Font.BOLD, 13);
    }

    /**
     *
     * @param i
     * @param p
     */
    public Connector(Node i, Point p) {
        ImageIcon tmp;
        tmp = new ImageIcon(super.getClass().getResource("/IMGS/lazo.png"));
        lazo = tmp.getImage();
        lw = tmp.getIconWidth();
        lh = tmp.getIconHeight();
        start = i;
        end = null;
        isCreating = true;
        endPoint = p;
        bounds = new Polygon();
        r = start.bounds.width / 2;
        ratio = 3;
        txt = null;
        font = new Font("Dialog", Font.BOLD, 13);
    }

    // <editor-fold defaultstate="collapsed" desc="Sets">

    /**
     *
     * @param init
     */
        public void setStartingNode(Node init) {
        this.start = init;
    }

    /**
     *
     * @param end
     */
    public void setEndingNode(Node end) {
        this.end = end;
        isCreating = false;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="Gets">

    /**
     *
     * @param p
     * @return
     */
        public boolean isPointInside(Point p) {
        return bounds.contains(p);
    }

    /**
     *
     * @return
     */
    public int MinimalY() {
        int y[] = bounds.ypoints;
        int a = y[0];
        for (int i = 1, n = bounds.npoints; i < n; i++) {
            if (a > y[i]) {
                a = y[i];
            }
        }
        return a;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="Updates">
    void updateEndPoint(Point point) {
        endPoint = point;
    }

    void updateTxt(String txt) {
        String regex = "@";
        this.txt = txt.split(regex);
    }

    private void updateAngle(double x1, double y1, double x2, double y2) {
        angle = Math.atan((y2 - y1) / (x2 - x1));
    }

    private void updateBounds() {
        bounds = new Polygon();
        if (start.equals(end) || (end == null && start.isPointInside(endPoint))) {
            bounds.addPoint(start.bounds.getCenterX() - lw / 2, start.loc.y - lh);
            lp = bounds.npoints - 1;
            bounds.addPoint(start.bounds.getCenterX() - lw / 2, start.loc.y);
            bounds.addPoint(start.bounds.getCenterX() + lw / 2, start.loc.y);
            bounds.addPoint(start.bounds.getCenterX() + lw / 2, start.loc.y - lh);
            if (txt != null) {
                int strlen = 3 * font.getSize() - 2;
                for (int i = 0; i < txt.length; i++) {
                    bounds.addPoint(start.bounds.getCenterX() + strlen / 2, start.loc.y - lh - (i * font.getSize()));
                }
                bounds.addPoint(start.bounds.getCenterX() + strlen / 2, start.loc.y - lh - (txt.length * font.getSize()));
                bounds.addPoint(start.bounds.getCenterX() - strlen / 2, start.loc.y - lh - (txt.length * font.getSize()));
                irange = bounds.npoints;
                for (int i = 1; i <= txt.length; i++) {
                    bounds.addPoint(start.bounds.getCenterX() - strlen / 2, (start.loc.y - lh - (txt.length * font.getSize())) + (i * font.getSize()));
                }
            }
            angle = 0;
        } else {
            if (isCreating) {
                bounds = reloadBounds(start.bounds.getCenterX(), start.bounds.getCenterY(),
                        endPoint.x, endPoint.y);
            } else {
                bounds = reloadBounds(start.bounds.getCenterX(), start.bounds.getCenterY(),
                        end.bounds.getCenterX(), end.bounds.getCenterY());
            }
        }
        /*
         for (int i = 0 ; i<bounds.npoints ; i++)
         System.out.print("["+bounds.xpoints[i]+","+bounds.ypoints[i]+"] ");
         System.out.println();
         */
    }

    private Polygon reloadBounds(double x1, double y1, double x2, double y2) {
        if (isCreating) {
            updateAngle(start.bounds.getCenterX(), start.bounds.getCenterY(), endPoint.x, endPoint.y);
        } else {
            updateAngle(start.bounds.getCenterX(), start.bounds.getCenterY(), end.bounds.getCenterX(), end.bounds.getCenterY());
        }
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dist = Math.sqrt(dx * dx + dy * dy);
        Line[] lines;
        LinkedList<Line> lineL = null;
        int strlen = 0;
        if (txt == null) {
            lines = new Line[6];
        } else {
            strlen = 3 * font.getSize() - 2;
            lines = new Line[8];
            lineL = new LinkedList<Line>();
        }
        //center line
        lines[0] = new Line(dy, -dx, -(dy * x1 + (-dx * y1)));
        lines[1] = new Line(dx, dy, -(dx * x1 + dy * y1) - 25 * dist);
        if (isCreating) {
            lines[2] = new Line(dx, dy, -(dx * x2 + dy * y2) + 15 * dist);
            lines[5] = new Line(dx, dy, -(dx * x2 + dy * y2) - dist);
        } else {
            lines[2] = new Line(dx, dy, -(dx * x2 + dy * y2) + 40 * dist);
            lines[5] = new Line(dx, dy, -(dx * x2 + dy * y2) + 25 * dist);
        }
        //costados
        lines[3] = new Line(dy, -dx, -(dy * x1 + (-dx * y1)) + 4 * dist);
        lines[4] = new Line(dy, -dx, -(dy * x1 + (-dx * y1)) - 4 * dist);
        if (txt != null) {
            double val = Math.abs((Math.abs(dx) > Math.abs(dy)) ? dx / 2 : dy / 2) - strlen / 2;
            if (x1 < x2) {
                lines[6] = new Line(dx, dy, -(dx * x1 + dy * y1) - (val * dist));
                lines[7] = new Line(dx, dy, -(dx * x1 + dy * y1) - ((val + strlen) * dist));
            } else {
                lines[6] = new Line(dx, dy, -(dx * x1 + dy * y1) - ((val + strlen) * dist));
                lines[7] = new Line(dx, dy, -(dx * x1 + dy * y1) - (val * dist));
            }
            int factor = 4;
            for (int i = 1; i <= txt.length; i++) {
                lineL.add(new Line(dy, -dx, -(dy * x1 + (-dx * y1)) - (font.getSize() * i + factor) * dist));
                if (factor != 0) {
                    factor--;
                }
            }
        }
        Point2D.Double[] pts = new Point2D.Double[5];
        pts[0] = (Point2D.Double) GIP(lines[1], lines[3]);
        pts[2] = (Point2D.Double) GIP(lines[2], lines[3]);
        pts[4] = (Point2D.Double) GIP(lines[5], lines[0]);
        pts[3] = (Point2D.Double) GIP(lines[2], lines[4]);
        pts[1] = (Point2D.Double) GIP(lines[1], lines[4]);

        Polygon p = new Polygon();
        p.addPoint((int) pts[0].x, (int) pts[0].y);
        p.addPoint((int) pts[2].x, (int) pts[2].y);
        a2p = p.npoints - 1;
        p.addPoint((int) pts[4].x, (int) pts[4].y);
        common_point = p.npoints - 1;
        p.addPoint((int) pts[3].x, (int) pts[3].y);
        a1p = p.npoints - 1;
        if (txt != null) {
            Point2D.Double i;
            i = (Point2D.Double) GIP(lines[4], lines[7]);
            p.addPoint((int) i.x, (int) i.y);
            for (int j = 1; j <= txt.length; j++) {
                i = (Point2D.Double) GIP(lineL.get(j - 1), lines[7]);
                p.addPoint((int) i.x, (int) i.y);
            }
            int v = lineL.size();
            if (x1 < x2) {
                i = (Point2D.Double) GIP(lineL.get(v - 1), lines[6]);
                p.addPoint((int) i.x, (int) i.y);
                irange = p.npoints;
                for (int j = 2; j <= txt.length; j++) {
                    i = (Point2D.Double) GIP(lineL.get(v - j), lines[6]);
                    p.addPoint((int) i.x, (int) i.y);
                }
            } else {
                irange = p.npoints;
                for (int j = 1; j <= txt.length; j++) {
                    i = (Point2D.Double) GIP(lineL.get(v - j), lines[6]);
                    p.addPoint((int) i.x, (int) i.y);
                }
            }
            i = (Point2D.Double) GIP(lines[4], lines[6]);
            p.addPoint((int) i.x, (int) i.y);
        }
        p.addPoint((int) pts[1].x, (int) pts[1].y);
        return p;
    }

    /**
     *
     */
    protected class Line {

        double a, b, c;

        /**
         *
         * @param a
         * @param b
         * @param c
         */
        public Line(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    private Point2D GIP(Line l1, Line l2) {
        double x = (l1.b * l2.c - l2.b * l1.c) / (l1.a * l2.b - l2.a * l1.b);
        double y = (l2.a * l1.c - l1.a * l2.c) / (l1.a * l2.b - l2.a * l1.b);
        return new Point2D.Double(x, y);
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="Paint">

    /**
     *
     * @param g
     * @param view_port
     */
        public void paint(Graphics2D g, ImageObserver view_port) {
        if (start.equals(end) || (end == null && start.isPointInside(endPoint))) {
            updateBounds();
            g.drawImage(lazo, bounds.xpoints[lp], bounds.ypoints[lp], view_port);
        } else {
            g.drawLine(FrX(), FrY(), ToX(), ToY());
            updateBounds();
            g.drawLine(A1X(), A1Y(), ACX(), ACY());
            g.drawLine(A2X(), A2Y(), ACX(), ACY());
        }
        if (txt != null) {
            AffineTransform at;
            g.setFont(font);
            int k = irange;
            for (int i = 0; i < txt.length; i++, k++) {
                at = g.getTransform();
                g.rotate(angle, bounds.xpoints[k], bounds.ypoints[k]);
                g.drawString(txt[i], bounds.xpoints[k], bounds.ypoints[k]);
                g.setTransform(at);
            }
        }
    }

    /**
     *
     * @return
     */
    public int FrX() {
        return start.bounds.getCenterX();
    }

    /**
     *
     * @return
     */
    public int FrY() {
        return start.bounds.getCenterY();
    }

    /**
     *
     * @return
     */
    public int ToX() {
        return (isCreating) ? endPoint.x : end.bounds.getCenterX();
    }

    /**
     *
     * @return
     */
    public int ToY() {
        return (isCreating) ? endPoint.y : end.bounds.getCenterY();
    }

    /**
     *
     * @return
     */
    public int A1X() {
        return bounds.xpoints[a1p];
    }

    /**
     *
     * @return
     */
    public int A1Y() {
        return bounds.ypoints[a1p];
    }

    /**
     *
     * @return
     */
    public int A2X() {
        return bounds.xpoints[a2p];
    }

    /**
     *
     * @return
     */
    public int A2Y() {
        return bounds.ypoints[a2p];
    }

    /**
     *
     * @return
     */
    public int ACX() {
        return bounds.xpoints[common_point];
    }

    /**
     *
     * @return
     */
    public int ACY() {
        return bounds.ypoints[common_point];
    }// </editor-fold> 
}
