package SUPPORT;

import CONTROLLER.TM.tmController;
import NMS.NMS;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

/**
 *
 * @author rsegui
 */
public class tools {

    private static boolean init;
    private static ImageIcon dicon, cct;
    private static JPopupMenu np, ng, ct, eh, node, link;
    private static JTabbedPane container;

    /**
     *
     * @param container
     */
    static public void Init(JTabbedPane container) {
        init = true;
        dicon = new ImageIcon(tools.class.getResource("/IMGS/design.png"));
        cct = new ImageIcon(tools.class.getResource("/IMGS/cct.png"));
        tools.container = container;
        NewProyectPopup();
        NodeGeneratorPopup();
        EventHandlerPopup();
        NodePopup();
        LinkPopup();
        CloseTabsPopup();
    }

    static private void NewProyectPopup() {
        np = new JPopupMenu();
        JMenuItem dmt, daut;
        dmt = new JMenuItem(config.getText("sui.newMachine"));
        daut = new JMenuItem(config.getText("sui.newAutomata"));
        dmt.setIcon(dicon);
        daut.setIcon(dicon);
        dmt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NMS nms = new NMS();
                nms.setController(new tmController(config.getText("no.title") + Integer.toString(container.getTabCount())));
                container.addTab(nms.getController().getName(), cct, nms);
                container.setSelectedIndex(container.getTabCount() - 1);
            }
        });
        daut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Not implemented yet!");
            }
        });
        np.add(dmt);
        np.add(daut);
    }

    static private void NodeGeneratorPopup() {
        ng = new JPopupMenu();
        ImageIcon icon;
        JMenuItem i, fi, n, f;
        i = new JMenuItem("Nodo Inicial",
                new ImageIcon(tools.class.getResource("/IMGS/add_initial.png")));
        fi = new JMenuItem("Nodo Inicial Final",
                new ImageIcon(tools.class.getResource("/IMGS/add_initial-final.png")));
        n = new JMenuItem("Nodo Normal",
                new ImageIcon(tools.class.getResource("/IMGS/add_normal.png")));
        f = new JMenuItem("Nodo Final",
                new ImageIcon(tools.class.getResource("/IMGS/add_final.png")));
        i.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = container.getSelectedIndex();
                if (container.getTabCount() > 1 && index > 0) {
                    NMS obj = (NMS) container.getSelectedComponent();
                    obj.TryToAdd(0);
                }
            }
        });
        fi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = container.getSelectedIndex();
                if (container.getTabCount() > 1 && index > 0) {
                    NMS obj = (NMS) container.getSelectedComponent();
                    obj.TryToAdd(3);
                }
            }
        });
        n.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = container.getSelectedIndex();
                if (container.getTabCount() > 1 && index > 0) {
                    NMS obj = (NMS) container.getSelectedComponent();
                    obj.TryToAdd(1);
                }
            }
        });
        f.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = container.getSelectedIndex();
                if (container.getTabCount() > 1 && index > 0) {
                    NMS obj = (NMS) container.getSelectedComponent();
                    obj.TryToAdd(2);
                }
            }
        });
        ng.add(i);
        ng.add(fi);
        ng.add(n);
        ng.add(f);
    }

    static private void EventHandlerPopup() {
        eh = new JPopupMenu();
        JMenuItem selall, delsel, delall;
        selall = new JMenuItem(config.getText("tools.select.all"),
                new ImageIcon(tools.class.getResource("/IMGS/sel.png")));
        delsel = new JMenuItem(config.getText("tools.del.selection"),
                new ImageIcon(tools.class.getResource("/IMGS/trash2.png")));
        delall = new JMenuItem(config.getText("tools.del.all"),
                new ImageIcon(tools.class.getResource("/IMGS/trash2.png")));

        selall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NMS nms;
                nms = ((NMS) container.getSelectedComponent());
                nms.vh.SelectAll();
            }
        });

        delsel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NMS nms;
                nms = ((NMS) container.getSelectedComponent());
                nms.getController().removeSelection(nms.vh.getSelectedNodes());
            }
        });
        delall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NMS nms;
                nms = ((NMS) container.getSelectedComponent());
                nms.getController().removeAll();
            }
        });
        eh.add(selall);
        eh.add(delsel);
        eh.add(delall);
    }
    private static int number;
    private static Point where;

    static private void NodePopup() {
        node = new JPopupMenu();
        JMenuItem e, ml, ch;
        e = new JMenuItem(config.getText("tools.del"), new ImageIcon(tools.class.getResource("/IMGS/trash2.png")));
        ml = new JMenuItem(config.getText("tools.multiple.lnk"), new ImageIcon(tools.class.getResource("/IMGS/ml.png")));
        ch = new JMenuItem(config.getText("tools.change.type"), new ImageIcon(tools.class.getResource("/IMGS/ch.png")));
        e.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NMS nms;
                nms = ((NMS) container.getSelectedComponent());
                nms.getController().remove(number);
            }
        });
        ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NMS nms;
                nms = ((NMS) container.getSelectedComponent());
                nms.getController().showAddLinkWindow(number, where);
            }
        });
        ch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NMS nms;
                nms = ((NMS) container.getSelectedComponent());
                nms.vh.changeTypeOf(number);
            }
        });

        node.add(e);
        node.add(ml);
        node.add(ch);

    }
    private static int from, to;

    private static void LinkPopup() {
        link = new JPopupMenu();
        JMenuItem e = new JMenuItem(config.getText("tools.del"), new ImageIcon(tools.class.getResource("/IMGS/trash2.png")));
        e.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NMS nms;
                nms = ((NMS) container.getSelectedComponent());
                nms.getController().removeLink(from, to);
            }
        });
        link.add(e);
    }

    private static void CloseTabsPopup() {
        ct = new JPopupMenu();
        JMenuItem close, closeAll, closeOthers;
        close = new JMenuItem(config.getText("tools.close"), new ImageIcon(tools.class.getResource("/IMGS/close.png")));
        closeAll = new JMenuItem(config.getText("tools.close.all"), new ImageIcon(tools.class.getResource("/IMGS/close.png")));
        closeOthers = new JMenuItem(config.getText("tools.close.other"), new ImageIcon(tools.class.getResource("/IMGS/close.png")));
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CloseCurrentTab();
            }
        });
        closeAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = container.getTabCount();
                boolean seguir = true;
                for (int i = count - 1; seguir && i >= 0; i--) {
                    seguir = CloseTabAt(i);
                }
            }
        });
        closeOthers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = container.getTabCount();
                int index = container.getSelectedIndex();
                for (int i = count - 1; i > index; i--) {
                    CloseTabAt(i);
                }
                for (int i = index - 1; i >= 0; i--) {
                    CloseTabAt(i);
                }
            }
        });
        ct.add(close);
        ct.add(closeAll);
        ct.add(closeOthers);
    }

    /**
     *
     * @param comp
     * @param coordinates
     */
    static public void showCT(JComponent comp, Point coordinates) {
        if (init) {
            ct.show(container, coordinates.x, coordinates.y);
        }
    }

    /**
     *
     * @param comp
     * @param coordinates
     */
    static public void showNP(JComponent comp, Point coordinates) {
        if (init) {
            np.show(comp, coordinates.x, coordinates.y);
        }
    }

    /**
     *
     * @param comp
     * @param coordinates
     */
    static public void showNG(JComponent comp, Point coordinates) {
        if (init) {
            ng.show(comp, coordinates.x, coordinates.y);
        }
    }

    /**
     *
     * @param comp
     * @param coordinates
     */
    static public void showEH(JComponent comp, Point coordinates) {
        if (init) {
            eh.show(comp, coordinates.x, coordinates.y);
        }
    }

    /**
     *
     * @param comp
     * @param coordinates
     * @param who
     * @param p
     */
    static public void showNode(JComponent comp, Point coordinates, int who, Point p) {
        if (init) {
            number = who;
            where = p;
            node.show(comp, coordinates.x, coordinates.y);
        }
    }

    /**
     *
     * @param comp
     * @param coordinates
     * @param start
     * @param end
     */
    static public void showLink(JComponent comp, Point coordinates, int start, int end) {
        if (init) {
            from = start;
            to = end;
            link.show(comp, coordinates.x, coordinates.y);
        }
    }

    //for public access
    /**
     *
     */
    public static void CloseCurrentTab() {
        CloseTabAt(container.getSelectedIndex());
    }

    /**
     *
     * @param at
     * @return
     */
    public static boolean CloseTabAt(int at) {
        if (!(container.getComponentAt(at) instanceof NMS)) {
            container.remove(at);
            return true;
        }
        NMS nms = (NMS) container.getComponentAt(at);
        if (nms.getController().ChangeOcurred()) {
            int value = JOptionPane.showConfirmDialog(null,
                    config.getText("question.save") + nms.getController().getName() + "?",
                    nms.getController().getName(),
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(tools.class.getResource("/IMGS/Help2.png")));
            switch (value) {
                case JOptionPane.CANCEL_OPTION:
                    return false;
                case JOptionPane.CLOSED_OPTION:
                    return false;
                case JOptionPane.YES_OPTION:
                    nms.getController().Save();
                    container.remove(at);
                    return true;
                case JOptionPane.NO_OPTION:
                    container.remove(at);
                    return true;
            }
        } else {
            container.remove(at);
        }
        return true;
    }
}
