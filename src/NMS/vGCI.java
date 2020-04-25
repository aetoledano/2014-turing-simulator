package NMS;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author rsegui
 */
public interface vGCI {

    /**
     *
     * @param p
     */
    public void AddNode(Point p);

    /**
     *
     * @param kind
     * @param p
     */
    public void AddNode(int kind, Point p);

    /**
     *
     * @param i
     * @param point
     */
    public void AddConnector(Node i, Point point);

    /**
     *
     * @param from
     * @param to
     */
    public void AddConnector(Node from, Node to);

    /**
     *
     * @param from
     * @param to
     */
    public void AddConnector(int from, int to);

    /**
     *
     * @param from
     * @param to
     * @param txt
     */
    public void AddConnector(int from, int to, String txt);

    /**
     *
     * @param from
     * @param to
     * @param txt
     */
    public void ModifyConnector(int from, int to, String txt);

    /**
     *
     * @param number
     */
    public void DelNode(int number);

    /**
     *
     * @param selectedNodes
     */
    public void removeSelection(Integer[] selectedNodes);

    /**
     *
     */
    public void removeAll();

    /**
     *
     * @param from
     * @param to
     */
    public void DelConnector(int from, int to);

    /**
     *
     * @return
     */
    public Rectangle getExportImageRectangle();

    /**
     *
     */
    public void UpdateView();

    /**
     *
     * @param g
     */
    public void PaintFor(Graphics2D g);

    /**
     *
     */
    public void DeselectAll();

    /**
     *
     * @param who
     */
    public void SingleSelection(int who);

    /**
     *
     * @return
     */
    public Node[] getNodes();
    
    public Point[] getLocations();
}
