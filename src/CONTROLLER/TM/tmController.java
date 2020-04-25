package CONTROLLER.TM;

import CONTROLLER.GC;
import CONTROLLER.TerminatingException;
import DBA.DBStoredObject;
import DBA.Manager;
import LOGIC.TM.Analyzer;
import LOGIC.TM.Link;
import LOGIC.TM.Move;
import LOGIC.TM.State;
import LOGIC.TM.Transition;
import LOGIC.TM.tmNetWork;
import NMS.Node;
import SUPPORT.*;
import UI.*;
import UI.TM.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author jorge
 */
public class tmController extends GC {

    //<editor-fold defaultstate="collapsed" desc="Variables Declaration">
    tmNetWork net;
    tmSave r;
    Analyzer a;
    Move current;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Builders">
    /**
     *
     * @param name
     */
    public tmController(String name) {
        super(name);
        net = new tmNetWork();
        DeactivateChange();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Inicializar el alfabeto">
    /**
     *
     */
    @Override
    public void initAlphabet() {
        final JTextField f = new JTextField();
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                int codePoint = e.getKeyCode();
                String str = new String();
                str += c;
                if (isValidChar(c)) {
                    if (f.getText().contains(str)) {
                        e.consume();
                    }
                } else {
                    e.consume();
                }
            }

            private boolean isValidChar(char c) {
                return !Character.isSpaceChar(c) && c != '@' && c != '#';
            }
        });
        f.setPreferredSize(new Dimension(270, 32));
        int option = JOptionPane.showConfirmDialog(
                null,
                f,
                config.getText("insert.alpha.ribbon"),
                JOptionPane.CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                new ImageIcon(tmController.class.getResource("/IMGS/alpha_1.png")));
        if (option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION) {
            alphabet = f.getText() + "#";
            fireAnalyzerChange();
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Analyzer System">
    /**
     *
     * @param testing
     */
    @Override
    public void InitAnalyzer(String testing) {

        if (net.states.isEmpty()) {
            nc.quickReport(config.getText("net.empty"));
            //JOptionPane.showMessageDialog(null, config.getText("msg.net.empty"));
        } else {
            try {
                if (a == null || hasAnalyzerChanges() || !testing.equals(a.getTuringString().getOriginal())) {
                    a = new Analyzer(net, testing, alphabet.toCharArray(), nc, true);
                    deactiveAnalyzerChanges();
                }
                nc.startLiveReport(config.getText("press.play"));
            } catch (TerminatingException ex) {
                JOptionPane.showMessageDialog(null,
                        new AnswersUI(ex, this),
                        name,
                        JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(getClass().getResource("/IMGS/info.png")));
            }
        }
    }

    /**
     *
     * @param new_test
     * @throws TerminatingException
     */
    @Override
    public void InitForAutoAnalysis(String new_test, boolean enableWarnings) throws TerminatingException {
        a = new Analyzer(net, new_test, alphabet.toCharArray(), nc, enableWarnings);
    }

    /**
     *
     * @throws TerminatingException
     */
    @Override
    public void AutomaticAnalysis() throws TerminatingException {
        Move c;
        while (true) {
            a.getNextMove();
        }
    }

    /**
     *
     */
    @Override
    public void Start() {
        switch (mode) {
            case 0:
                if (a == null) {
                    nc.quickReport(config.getText("cad.not.set"));
                    //JOptionPane.showMessageDialog(null, config.getText("msg.string.not.set"));
                } else {
                    try {
                        if (hasAnalyzerChanges()) {
                            a = new Analyzer(net, a.getTuringString().getOriginal(), alphabet.toCharArray(), nc, true);
                            deactiveAnalyzerChanges();
                        }
                        c.SingleSelection(0);
                        ButtonGroup gr = new ButtonGroup();
                        JRadioButton opc[] = new JRadioButton[2];
                        opc[0] = new JRadioButton(config.getText("auto"));
                        opc[1] = new JRadioButton(config.getText("manual"));
                        opc[0].setSelected(true);
                        gr.add(opc[0]);
                        gr.add(opc[1]);
                        JOptionPane.showMessageDialog(null, opc, config.getText("mode"), JOptionPane.QUESTION_MESSAGE);
                        if (opc[0].isSelected()) {
                            mode = 1;
                            nc.startLiveReport(config.getText("starting"));
                            nc.updateLiveReport(a.getTuringString().getOutput(nc.getMaxCharacterAllowed()), a.getTuringString().getPaint());
                            aTimer.start();
                        } else {
                            mode = 2;
                            nc.startLiveReport(config.getText("starting"));
                            nc.updateLiveReport(a.getTuringString().getOutput(nc.getMaxCharacterAllowed()), a.getTuringString().getPaint());
                        }
                    } catch (TerminatingException ex) {
                        JOptionPane.showMessageDialog(null,
                                new AnswersUI(ex, this),
                                name,
                                JOptionPane.PLAIN_MESSAGE,
                                new ImageIcon(getClass().getResource("/IMGS/info.png")));
                    }
                }
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                aTimer.start();
                mode = 1;
                break;
        }

    }

    /**
     *
     */
    @Override
    public void Pause() {
        switch (mode) {
            case 0:
                break;
            case 1:
                aTimer.stop();
                mode = 3;
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    /**
     *
     */
    @Override
    public void Stop() {
        nc.stopLiveReport(null);
        switch (mode) {
            case 0:
                if (a != null) {
                    a.restart();
                }
                break;
            case 1:
                aTimer.stop();
                mode = 0;
                if (a != null) {
                    a.restart();
                }
                break;
            case 2:
                mode = 0;
                if (a != null) {
                    a.restart();
                }
                break;
            case 3:
                mode = 0;
                if (a != null) {
                    a.restart();
                }
                break;
        }
    }

    /**
     *
     */
    @Override
    public void Next() {
        switch (mode) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                innerNextMove();
                break;
            case 3:
                innerNextMove();
                break;
        }

    }

    /**
     *
     */
    @Override
    public void innerNextMove() {
        try {
            current = a.getNextMove();
            c.SingleSelection(current.state.id);
            nc.updateLiveReport(a.getTuringString().getOutput(nc.getMaxCharacterAllowed()), a.getTuringString().getPaint());
        } catch (TerminatingException ex) {
            if (ex.getTermState() == 3 || ex.getTermState() == 4) {
                JOptionPane.showMessageDialog(null,
                        new AnswersUI(ex, this),
                        name,
                        JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(getClass().getResource("/IMGS/info.png")));
            } else {
                JOptionPane.showMessageDialog(null,
                        new AnswersUI(ex, this),
                        name,
                        JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(getClass().getResource("/IMGS/info.png")));
            }
            Stop();
        }
    }

    /**
     *
     */
    @Override
    public void Back() {
        switch (mode) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                current = a.getPreviousMove();
                c.SingleSelection(current.state.id);
                nc.updateLiveReport(a.getTuringString().getOutput(nc.getMaxCharacterAllowed()), a.getTuringString().getPaint());
                break;
            case 3:
                current = a.getPreviousMove();
                c.SingleSelection(current.state.id);
                nc.updateLiveReport(a.getTuringString().getOutput(nc.getMaxCharacterAllowed()), a.getTuringString().getPaint());
                break;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getCurrentSTR() {
        if (a != null) {
            return a.getTuringString().getOriginal();
        }
        return null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get&Set Methods">
    /**
     *
     * @return
     */
    public tmNetWork getNet() {
        return net;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Add Nodes&Connectors">
    /**
     *
     * @param p
     */
    @Override
    public void addNode(Point p) {
        State ist;
        if (net.states.isEmpty()) {
            ist = new State(0, 0);
        } else {
            ist = new State(net.states.size(), 1);
        }
        if (net.addState(ist)) {
            FireChange();
            fireAnalyzerChange();
            c.AddNode(p);
        } else {
            nc.quickReport(config.getText("node.not.added"));
            //JOptionPane.showMessageDialog(null, config.getText("msg.node.not.added"));
        }
    }

    /**
     *
     * @param kind
     * @param p
     */
    @Override
    public void addNode(int kind, Point p) {

        if (net.states.isEmpty()) {
            State ist = new State(net.states.size(), kind);
            if (kind == 0 || kind == 3) {
                if (net.addState(ist)) {
                    FireChange();
                    fireAnalyzerChange();
                    c.AddNode(kind, p);
                } else {
                    nc.quickReport(config.getText("node.not.added"));
                    // JOptionPane.showMessageDialog(null, config.getText("msg.node.not.added"));
                }
            }
        } else {
            State ist = new State(net.states.size(), kind);
            if (kind == 1 || kind == 2) {
                if (net.addState(ist)) {
                    FireChange();
                    fireAnalyzerChange();
                    c.AddNode(kind, p);
                } else {
                    nc.quickReport(config.getText("node.not.added"));
                    //  JOptionPane.showMessageDialog(null, config.getText("msg.node.not.added"));
                }
            }
        }
    }

    /**
     *
     * @param from
     * @param to
     * @param point
     * @return
     */
    @Override
    public boolean addLink(Node from, Node to, Point point) {
        if (!isAlphabetOK()) {
            nc.quickReport(config.getText("alpha.not.set"));
            /*JOptionPane.showMessageDialog(
             null,
             "No se ha insertado un alfabeto todavia!");*/
            return false;
        }
        try {
            char[] posible;
            posible = net.posibleTransitions(from.number, alphabet.toCharArray());
            if (posible.length < 1) {
                nc.quickReport(config.getText("unable.add.trans"));
                /*JOptionPane.showMessageDialog(
                 null,
                 "No se pueden añadir mas enlaces desde este estado");*/
                return false;
            }
            ConfirmLinkUI w;
            w = new ConfirmLinkUI(true, posible, alphabet.toCharArray(), point);
            w.setVisible(true);
            if (w.cancel) {
                return false;
            }
            AddTransition(
                    from.number,
                    to.number,
                    (Character) w.readModel.getSelectedItem(),
                    (Character) w.writeModel.getSelectedItem(),
                    (Character) w.actionModel.getSelectedItem());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return false;
        }
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    @Override
    protected String PreparateText(int from, int to) {
        ArrayList<Link> a = this.net.links.get(from);
        Link e = null;
        for (Link t : a) {
            if (t.destin.id == to) {
                e = t;
                break;
            }
        }
        String str = "";
        if (e != null) {
            str = e.transitions.get(0).toString();
            for (int i = 1; i < e.transitions.size(); i++) {
                str += "@" + e.transitions.get(i);
            }
        }
        return str;
    }

    /**
     *
     * @param from
     * @param p
     */
    @Override
    public void showAddLinkWindow(int from, Point p) {
        if (!isAlphabetOK()) {
            nc.quickReport(config.getText("alpha.not.set"));
            /* JOptionPane.showMessageDialog(
             null,
             "No se ha insertado un alfabeto todavia!");*/
        } else {
            char[] posible;
            posible = net.posibleTransitions(from, alphabet.toCharArray());
            if (posible.length != 0) {
                MultiLinkUI lw = new MultiLinkUI(true, posible, alphabet.toCharArray(), net.states, from, this, p);
                lw.setVisible(true);
            } else {
                nc.quickReport(config.getText("lnk.not.added"));
                //JOptionPane.showMessageDialog(null, config.getText("msg.lnk.cant.add"));
            }
        }
    }

    /**
     *
     * @param from
     * @param to
     * @param point
     */
    @Override
    public void ModifyConnector(int from, int to, Point point) {
        try {
            char[] posible;
            posible = net.posibleTransitions(from, alphabet.toCharArray());
            ModifyLinkUI obj;
            Link e = null;
            for (Link t : net.links.get(from)) {
                if (t.destin.id == to) {
                    e = t;
                    break;
                }
            }
            obj = new ModifyLinkUI(true, posible, alphabet.toCharArray(), e, point);
            obj.setVisible(true);
            if (!obj.cancel) {
                e.transitions.clear();
                for (int i = 0; i < obj.listModel.size(); i++) {
                    e.transitions.add((Transition) obj.listModel.get(i));
                }
                String str = PreparateText(from, to);
                FireChange();
                fireAnalyzerChange();
                c.ModifyConnector(from, to, str);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     *
     * @param id
     */
    @Override
    public void remove(int id) {
        if (id != 0) {
            if (net.delState(id)) {
                c.DelNode(id);
                FireChange();
                fireAnalyzerChange();
            }
        } else {
            nc.quickReport(config.getText("st0.del"));
            //JOptionPane.showMessageDialog(null, config.getText("msg.node.initial.del"));
        }
    }

    /**
     *
     * @param selectedNodes
     */
    @Override
    public void removeSelection(LinkedList<Integer> selectedNodes) {
        if (!selectedNodes.isEmpty()) {
            if (selectedNodes.get(0).intValue() == 0) {
                selectedNodes.remove(0);
            }
            Integer inta[] = new Integer[selectedNodes.size()];
            net.removeList(selectedNodes.toArray(inta));
            c.removeSelection(inta);
            FireChange();
            fireAnalyzerChange();
        }
    }

    /**
     *
     */
    @Override
    public void removeAll() {
        net.states.clear();
        net.links.clear();
        c.removeAll();
        fireAnalyzerChange();
        DeactivateChange();
    }

    /**
     *
     * @param from
     * @param to
     */
    @Override
    public void removeLink(int from, int to) {
        net.delLink(from, to);
        if (net.existLink(from, to) == -1) {
            c.DelConnector(from, to);
        } else {
            c.ModifyConnector(from, to, PreparateText(from, to));
        }
        FireChange();
        fireAnalyzerChange();
    }

    /**
     *
     * @param id
     * @param newType
     */
    @Override
    public void changeType(int id, int newType) {
        net.states.get(id).tp = newType;
        FireChange();
        fireAnalyzerChange();
    }

    /**
     *
     */
    @Override
    public void Save() {
        if (ChangeOcurred()) {
            Point loc[] = c.getLocations();
            tmSave to_save;
            to_save = new tmSave(name, alphabet, loc, net);
            String array[];
            array = helper.SaveToFile(to_save, "jajtp", addr, name);
            if (array == null) {
                nc.quickReport(config.getText("file.not.saved"));
            } else {
                addr = array[0];
                name = array[1].substring(0, array[1].lastIndexOf("."));
                nc.quickReport(config.getText("saved.to") + "\n\"" + addr + "\"");
                DeactivateChange();
            }
        }
    }

    /**
     *
     */
    @Override
    public void SaveAs() {
        if (addr == null || name == null) {
            FireChange();
            Save();
        } else {
            char address[] = addr.toCharArray();
            char nomb[] = name.toCharArray();
            addr = null;
            FireChange();
            Save();
            addr = String.valueOf(address);
            name = String.valueOf(nomb);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int Load() {
        tmSave obj;
        if (sav instanceof tmSave) {
            obj = (tmSave) sav;
        } else {
            return 1;
        }
        try {
            name = obj.getName();
            alphabet = obj.getAlphabet();
            addr = obj.getFile_addr();
            net = obj.getNet();
            //comienzo del proceso de carga;
            Point[] loc = obj.getLoc();
            for (int i = 0, n = net.states.size(); i < n; i++) {
                c.AddNode(net.states.get(i).tp, loc[i]);
            }
            Iterator<Link> it;
            Link e;
            for (int i = 0, n = net.links.size(); i < n; i++) {
                it = net.links.get(i).iterator();
                while (it.hasNext()) {
                    e = it.next();
                    c.AddConnector(i, e.destin.id, e.ViewTransitions());
                }
            }
            sav = null;
            undo.clear();
            redo.clear();
        } catch (Exception ex) {
            return 1;
        }
        return 0;
    }

    //referent to this
    private boolean isAlphabetOK() {
        return (alphabet != null);
    }

    /**
     *
     * @param from
     * @param to
     * @param read
     * @param write
     * @param dir
     */
    public void AddTransition(int from, int to, Character read, Character write, Character dir) {
        try {
            net.addLink(from, to, read, write, dir);
            String txt = PreparateText(from, to);
            c.ModifyConnector(from, to, txt);
            FireChange();
            fireAnalyzerChange();
        } catch (Exception ex) {
        }
    }

    @Override
    public void uploadMach(String name, String desc, int h, int w) {
        try {
            AuthenticateUser();
            Manager mgr = new Manager("127.0.0.1", "jajtp", user, pass);
            BufferedImage img = getImageTile(true, w, h);

            if (mgr.uploadMachine(name, desc, alphabet, net, img,c.getLocations())) {
                JOptionPane.showMessageDialog(null, config.getText("db.upload.success"), config.getText("UI.done"), JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(tmController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    String user, pass;

    @Override
    public void AuthenticateUser() {
        user = "bosz";
        pass = "admin";
    }

    @Override
    public int LoadFromDBStoredObject(DBStoredObject obj) {
        if (obj.getNet() instanceof tmNetWork) {
            alphabet = obj.getAlphabet();
            net = (tmNetWork) obj.getNet();
            Point[] loc = obj.getLocations();
            for (int i = 0, n = net.states.size(); i < n; i++) {
                c.AddNode(net.states.get(i).tp, loc[i]);
            }
            Iterator<Link> it;
            Link e;
            for (int i = 0, n = net.links.size(); i < n; i++) {
                it = net.links.get(i).iterator();
                while (it.hasNext()) {
                    e = it.next();
                    c.AddConnector(i, e.destin.id, e.ViewTransitions());
                }
            }
            sav = null;
            undo.clear();
            redo.clear();
            fireAnalyzerChange();
            FireChange();
            return GC.LOAD_OK;
        } else {
            return GC.CAST_EXCEPTION;
        }
    }
}
