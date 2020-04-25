package CONTROLLER;

import ACTION.*;
import DBA.DBStoredObject;
import NMS.NotifyCenter;
import NMS.Node;
import NMS.vGCI;
import SUPPORT.*;
import UI.AnswersUI;
import UI.TM.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/**
 *
 * @author rsegui
 */
public abstract class GC {

    //static
    /**
     *
     */
    public static final int LOAD_OK = 0,CAST_EXCEPTION=1;

    /**
     *
     */
    protected boolean initial;

    /**
     *
     */
    protected String name;

    protected Stack<Action> undo;
    protected Stack<Action> redo;
    /**
     *
     */
    public String alphabet;

    /**
     *
     */
    protected StrFrameView frame_str;

    /**
     *
     */
    public NotifyCenter nc;

    /**
     *
     */
    protected vGCI c;

    /**
     *
     */
    protected String addr;

    /**
     *
     */
    protected SAVE sav;
    private boolean analizer_change, change;

    /**
     *
     */
    protected int mode;

    /**
     *
     */
    final protected Timer aTimer = new Timer(config.getCurrentAnalyzerSpeed(), new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            innerNextMove();
        }
    });

    //<editor-fold defaultstate="collapsed" desc="Builders">
    /**
     *
     * @param name
     */
    public GC(String name) {
        undo = new Stack<Action>();
        redo = new Stack<Action>();
        alphabet = null;
        this.name = (name == null) ? "Sin Titulo" : name;
        addr = null;
        DeactivateChange();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setting UP">
    /**
     *
     * @param container
     */
    public void setVisualSystem(vGCI container) {
        c = container;
    }

    /**
     *
     * @param obj
     */
    public void updateLoaderSystem(SAVE obj) {
        sav = obj;
    }

    /**
     *
     */
    public abstract void initAlphabet();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get&Set Methods">
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public vGCI getVisualSystem() {
        return c;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Change Manager">
    /**
     *
     * @return
     */
    public final boolean ChangeOcurred() {
        return change;
    }

    /**
     *
     */
    public final void FireChange() {
        change = true;
    }

    /**
     *
     */
    public final void DeactivateChange() {
        change = false;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Analyzer Methods">
    //modes: 0 -> Stopped , 1 -> Running Automatic , 2 -> Running Manual , 3 -> Paused
    /**
     *
     * @return
     */
    final protected boolean hasAnalyzerChanges() {
        return analizer_change;
    }

    /**
     *
     */
    final public void fireAnalyzerChange() {
        System.out.println("Analyzer change Generated");
        analizer_change = true;
    }

    /**
     *
     */
    final protected void deactiveAnalyzerChanges() {
        analizer_change = false;
    }

    /**
     *
     */
    final public void resetAnalyzerSpeed() {
        aTimer.setDelay(config.getCurrentAnalyzerSpeed());
    }

    /**
     *
     * @return
     */
    final public boolean isAnalyzerRunning() {
        return (mode == 1 || mode == 2 || mode == 3);
    }

    /**
     *
     */
    final public void CloseStrFrameView() {
        if (this.frame_str != null) {
            this.frame_str.dispose();
        }
    }

    /**
     *
     * @param cads
     * @param progress
     */
    final public void performMultiAnalysis(ArrayList<String> cads, JProgressBar progress) {
        try {
            progress.setMinimum(0);
            progress.setMaximum(cads.size());
            int count = 0;
            Iterator<String> it = cads.iterator();
            String str;
            LinkedList<TerminatingException> es = new LinkedList<TerminatingException>();
            boolean warningsEnabled = true;
            if (it.hasNext()) {
                do {
                    str = it.next();
                    InitForAutoAnalysis(str, warningsEnabled);
                    warningsEnabled = false;
                    try {
                        AutomaticAnalysis();
                    } catch (TerminatingException e) {
                        es.add(e);
                    }
                    count++;
                    progress.setValue(count);
                } while (it.hasNext());
            }
            JOptionPane.showMessageDialog(null,
                    new AnswersUI(es, this),
                    name,
                    JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(getClass().getResource("/IMGS/info.png")));
        } catch (TerminatingException ex) {
            JOptionPane.showMessageDialog(null,
                    new AnswersUI(ex, this),
                    name,
                    JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(getClass().getResource("/IMGS/info.png")));
        }
    }

    /**
     *
     * @param testing
     */
    public abstract void InitAnalyzer(String testing);

    /**
     *
     * @param new_test
     * @throws TerminatingException
     */
    public abstract void InitForAutoAnalysis(String new_test, boolean enableWarnings) throws TerminatingException;

    /**
     *
     */
    public abstract void Start();

    /**
     *
     * @throws TerminatingException
     */
    public abstract void AutomaticAnalysis() throws TerminatingException;

    /**
     *
     */
    public abstract void Pause();

    /**
     *
     */
    public abstract void Stop();

    /**
     *
     */
    public abstract void Next();

    /**
     *
     */
    public abstract void innerNextMove();

    /**
     *
     */
    public abstract void Back();

    /**
     *
     * @return
     */
    public abstract String getCurrentSTR();
    //</editor-fold>

    /**
     *
     * @param p
     */
    public abstract void addNode(Point p);

    /**
     *
     * @param kind
     * @param p
     */
    public abstract void addNode(int kind, Point p);

    /**
     *
     * @param from
     * @param to
     * @param point
     * @return
     */
    public abstract boolean addLink(Node from, Node to, Point point);

    /**
     *
     * @param from
     * @param to
     * @return
     */
    protected abstract String PreparateText(int from, int to);

    /**
     *
     * @param from
     * @param p
     */
    public abstract void showAddLinkWindow(int from, Point p);

    /**
     *
     * @param from
     * @param to
     * @param point
     */
    public abstract void ModifyConnector(int from, int to, Point point);

    /**
     *
     * @param id
     */
    public abstract void remove(int id);

    /**
     *
     * @param selectedNodes
     */
    public abstract void removeSelection(LinkedList<Integer> selectedNodes);

    /**
     *
     */
    public abstract void removeAll();

    /**
     *
     * @param from
     * @param to
     */
    public abstract void removeLink(int from, int to);

    /**
     *
     * @param id
     * @param newType
     */
    public abstract void changeType(int id, int newType);

    /**
     *
     */
    public void Undo() {
        if (!undo.isEmpty()) {
            Action a = undo.pop();
            System.out.println("undo " + a.toString());
            a.undo(this);
            redo.push(a);
            c.UpdateView();
        }
    }

    public void Redo() {
        if (!redo.isEmpty()) {
            Action a = redo.pop();
            System.out.println("redo " + a.toString());
            a.redo(this);
            undo.push(a);
            c.UpdateView();
        }
    }

    /**
     *
     */
    public abstract void Save();

    /**
     *
     */
    public abstract void SaveAs();

    /**
     *
     * @return
     */
    public abstract int Load();

    /**
     *
     * @param width
     * @param height
     */
    public void ExportAsImageRGB(int width, int height) {
        File file = null;
        file = helper.fixExt(helper.Get_File(false, false, "png", name), "png");
        if (file != null) {
            BufferedImage b;
            b = getImageTile(true, width, height);
            try {
                ImageIO.write(b, "PNG", file);
                nc.quickReport("Guardada en: \n" + file.getAbsolutePath());
                //JOptionPane.showMessageDialog(null, "Imagen Guardada en: \n" + file.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Imposible Salvar Imagen", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     *
     * @param width
     * @param height
     */
    public void ExportAsImageGrayScale(int width, int height) {
        File file = null;
        file = helper.fixExt(helper.Get_File(false, false, "png", name), "png");
        if (file != null) {
            BufferedImage b;
            b = getImageTile(false, width, height);
            try {
                ImageIO.write(b, "PNG", file);
                nc.quickReport("Guardada en: \n" + file.getAbsolutePath());
                //JOptionPane.showMessageDialog(null, "Imagen Guardada en: \n" + file.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Imposible Salvar Imagen", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     *
     * @param rgb
     * @param width
     * @param height
     * @return
     */
    public BufferedImage getImageTile(boolean rgb, int width, int height) {
        if (rgb) {
            BufferedImage b;
            b = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) b.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setStroke(new BasicStroke(2.5f));
            c.PaintFor(g);
            Rectangle r = c.getExportImageRectangle();
            return b.getSubimage(r.x, r.y, r.width, r.height);
        } else {
            BufferedImage b;
            b = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D g = (Graphics2D) b.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setStroke(new BasicStroke(2.5f));
            g.setColor(Color.white);
            g.fillRect(0, 0, width, height);
            c.PaintFor(g);
            Rectangle r = c.getExportImageRectangle();
            return b.getSubimage(r.x, r.y, r.width, r.height);
        }
    }

    /**
     *
     * @param nc
     */
    public void setNotifyCenter(NotifyCenter nc) {
        this.nc = nc;
    }

    public NotifyCenter getNc() {
        return nc;
    }

    public void putAction(Action a) {
        undo.push(a);
        if (!redo.isEmpty()) {
            redo.clear();
        }
    }

    public abstract void AuthenticateUser();

    public abstract void uploadMach(String nameSel, String desc, int vhh, int vhw);

    public abstract int LoadFromDBStoredObject(DBStoredObject obj);
}
