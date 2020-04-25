package UI;

import CONTROLLER.GC;
import CONTROLLER.TM.tmController;
import CONTROLLER.TM.tmSave;
import DBA.Manager;
import NMS.NMS;
import SUPPORT.config;
import SUPPORT.helper;
import SUPPORT.reloadConfig;
import SUPPORT.tools;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author rsegui
 */
public class MainUI extends javax.swing.JFrame implements reloadConfig {

    ImageIcon ti;
    StartUI pi;
    //dragging system
    boolean dragging;
    Point where;
    Color c;
    Font f;

    /**
     *
     * @param bg
     */
    public void paintOval(Graphics2D bg) {
        if (dragging) {
            bg.setColor(c);
            where = getMousePosition();
            if (where != null) {
                bg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                bg.setStroke(new BasicStroke(2.5f));
                bg.fillOval(where.x - 25, where.y - 25, 50, 50);
                bg.setFont(f);
                bg.drawString("q", where.x - 20, where.y + 10);
            }
        }
    }
    Dimension oldSize;
    Image buffer;
    Graphics2D bg;

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        if (oldSize == null || buffer == null || oldSize != getSize()) {
            oldSize = getSize();
            buffer = createImage((int) oldSize.getWidth(), (int) oldSize.getHeight());
            bg = (Graphics2D) buffer.getGraphics();
        }
        super.paint(bg);
        paintOval(bg);
        g.drawImage(buffer, 0, 0, this);
    }

    /**
     *
     */
    public MainUI() {
        initComponents();

        setLocationRelativeTo(null);
        setTitle("Just Another Java Turing Project");
        setIconImage((new ImageIcon(super.getClass().getResource("/IMGS/App2.png"))).getImage());
        ti = new ImageIcon(super.getClass().getResource("/IMGS/cct.png"));
        pi = new StartUI(view);
        c = new Color(79, 129, 189, 75);
        f = new Font("Dialog", Font.BOLD, 50);
        my_Init();
        view.addTab(config.getText("start_page"), ti, new StartUI(view));
        reloadLang();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        MenuBarBackground = new javax.swing.JPanel();
        tb = new javax.swing.JToolBar();
        tb_b_add = new javax.swing.JButton();
        tb_b_open = new javax.swing.JButton();
        tb_b_save = new javax.swing.JButton();
        tb_b_opt = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        tb_b_undo = new javax.swing.JButton();
        tb_b_redo = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        tb_b_ng = new javax.swing.JButton();
        strIn = new javax.swing.JTextField();
        tb_b_reload = new javax.swing.JButton();
        tb_b_alpha = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        tb_b_prev = new javax.swing.JButton();
        tb_b_next = new javax.swing.JButton();
        tb_b_play = new javax.swing.JButton();
        tb_b_pause = new javax.swing.JButton();
        tb_b_stop = new javax.swing.JButton();
        mb = new javax.swing.JMenuBar();
        mb_m_home = new javax.swing.JMenu();
        jmi_new = new javax.swing.JMenuItem();
        jmi_open = new javax.swing.JMenuItem();
        jmi_save = new javax.swing.JMenuItem();
        jmi_saveAs = new javax.swing.JMenuItem();
        jmi_exp = new javax.swing.JMenu();
        jmi_color = new javax.swing.JMenuItem();
        jmi_gs = new javax.swing.JMenuItem();
        jmi_out = new javax.swing.JMenuItem();
        mb_m_edit = new javax.swing.JMenu();
        jmi_undo = new javax.swing.JMenuItem();
        jmi_redo = new javax.swing.JMenuItem();
        mb_m_tools = new javax.swing.JMenu();
        jmi_gcp = new javax.swing.JMenuItem();
        jmi_upload = new javax.swing.JMenuItem();
        mb_m_help = new javax.swing.JMenu();
        jmi_about = new javax.swing.JMenuItem();
        mFiller = new javax.swing.JMenu();
        mb_b_report = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(706, 513));

        background.setBackground(new java.awt.Color(203, 219, 231));

        view.setForeground(new java.awt.Color(255, 255, 255));
        view.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                viewStateChanged(evt);
            }
        });
        view.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_click(evt);
            }
        });

        MenuBarBackground.setBackground(new java.awt.Color(255, 255, 255));
        MenuBarBackground.setPreferredSize(new java.awt.Dimension(686, 26));
        MenuBarBackground.setLayout(new java.awt.BorderLayout());

        tb.setBackground(new java.awt.Color(255, 255, 255));
        tb.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 204, 255), 1, true));
        tb.setFloatable(false);
        tb.setRollover(true);
        tb.setBorderPainted(false);
        tb.setMaximumSize(new java.awt.Dimension(442, 2147483647));
        tb.setPreferredSize(new java.awt.Dimension(620, 37));

        tb_b_add.setBackground(new java.awt.Color(255, 255, 255));
        tb_b_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/add.png"))); // NOI18N
        tb_b_add.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tb_b_add.setFocusable(false);
        tb_b_add.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_b_add.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_b_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_b_addMouseClicked(evt);
            }
        });
        tb_b_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_b_addActionPerformed(evt);
            }
        });
        tb.add(tb_b_add);

        tb_b_open.setBackground(new java.awt.Color(255, 255, 255));
        tb_b_open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/open.png"))); // NOI18N
        tb_b_open.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tb_b_open.setFocusable(false);
        tb_b_open.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_b_open.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_b_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_b_openActionPerformed(evt);
            }
        });
        tb.add(tb_b_open);

        tb_b_save.setBackground(new java.awt.Color(255, 255, 255));
        tb_b_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/save.png"))); // NOI18N
        tb_b_save.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tb_b_save.setFocusable(false);
        tb_b_save.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_b_save.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_b_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_b_saveActionPerformed(evt);
            }
        });
        tb.add(tb_b_save);

        tb_b_opt.setBackground(new java.awt.Color(255, 255, 255));
        tb_b_opt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/opt.png"))); // NOI18N
        tb_b_opt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tb_b_opt.setFocusable(false);
        tb_b_opt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_b_opt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_b_opt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_b_optActionPerformed(evt);
            }
        });
        tb.add(tb_b_opt);
        tb.add(filler1);

        tb_b_undo.setBackground(new java.awt.Color(255, 255, 255));
        tb_b_undo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/undo.png"))); // NOI18N
        tb_b_undo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tb_b_undo.setFocusable(false);
        tb_b_undo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_b_undo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_b_undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_b_undoActionPerformed(evt);
            }
        });
        tb.add(tb_b_undo);

        tb_b_redo.setBackground(new java.awt.Color(255, 255, 255));
        tb_b_redo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/redo.png"))); // NOI18N
        tb_b_redo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tb_b_redo.setFocusable(false);
        tb_b_redo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_b_redo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_b_redo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_b_redoActionPerformed(evt);
            }
        });
        tb.add(tb_b_redo);
        tb.add(filler2);

        tb_b_ng.setBackground(new java.awt.Color(255, 255, 255));
        tb_b_ng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/add_normal.png"))); // NOI18N
        tb_b_ng.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tb_b_ng.setFocusable(false);
        tb_b_ng.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_b_ng.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_b_ng.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tb_b_ngMouseDragged(evt);
            }
        });
        tb_b_ng.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_b_ngMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_b_ngMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_b_ngMouseReleased(evt);
            }
        });
        tb.add(tb_b_ng);
        tb_b_ng.getAccessibleContext().setAccessibleName("ng");

        strIn.setMaximumSize(new java.awt.Dimension(420, 2147483647));
        strIn.setPreferredSize(new java.awt.Dimension(270, 35));
        strIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                strInMouseClicked(evt);
            }
        });
        strIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                strInKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                strInKeyTyped(evt);
            }
        });
        tb.add(strIn);

        tb_b_reload.setBackground(new java.awt.Color(255, 255, 255));
        tb_b_reload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/reload.png"))); // NOI18N
        tb_b_reload.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tb_b_reload.setFocusable(false);
        tb_b_reload.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_b_reload.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_b_reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_b_reloadActionPerformed(evt);
            }
        });
        tb.add(tb_b_reload);

        tb_b_alpha.setBackground(new java.awt.Color(255, 255, 255));
        tb_b_alpha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/alpha.png"))); // NOI18N
        tb_b_alpha.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tb_b_alpha.setFocusable(false);
        tb_b_alpha.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_b_alpha.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_b_alpha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_b_alphaActionPerformed(evt);
            }
        });
        tb.add(tb_b_alpha);
        tb.add(filler3);

        tb_b_prev.setBackground(new java.awt.Color(255, 255, 255));
        tb_b_prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/back.png"))); // NOI18N
        tb_b_prev.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tb_b_prev.setFocusable(false);
        tb_b_prev.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_b_prev.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_b_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_b_prevActionPerformed(evt);
            }
        });
        tb.add(tb_b_prev);

        tb_b_next.setBackground(new java.awt.Color(255, 255, 255));
        tb_b_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/next.png"))); // NOI18N
        tb_b_next.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tb_b_next.setFocusable(false);
        tb_b_next.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_b_next.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_b_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_b_nextActionPerformed(evt);
            }
        });
        tb.add(tb_b_next);

        tb_b_play.setBackground(new java.awt.Color(255, 255, 255));
        tb_b_play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/play.png"))); // NOI18N
        tb_b_play.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tb_b_play.setFocusable(false);
        tb_b_play.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_b_play.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_b_play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_b_playActionPerformed(evt);
            }
        });
        tb.add(tb_b_play);

        tb_b_pause.setBackground(new java.awt.Color(255, 255, 255));
        tb_b_pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/pause.png"))); // NOI18N
        tb_b_pause.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tb_b_pause.setFocusable(false);
        tb_b_pause.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_b_pause.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_b_pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_b_pauseActionPerformed(evt);
            }
        });
        tb.add(tb_b_pause);

        tb_b_stop.setBackground(new java.awt.Color(255, 255, 255));
        tb_b_stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/stop.png"))); // NOI18N
        tb_b_stop.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tb_b_stop.setFocusable(false);
        tb_b_stop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_b_stop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_b_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_b_stopActionPerformed(evt);
            }
        });
        tb.add(tb_b_stop);

        MenuBarBackground.add(tb, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(view)
            .addComponent(MenuBarBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(MenuBarBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(view, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
        );

        mb.setBackground(new java.awt.Color(255, 255, 255));
        mb.setBorderPainted(false);
        mb.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mb.setFocusable(false);
        mb.setFont(new java.awt.Font("Gisha", 1, 10)); // NOI18N
        mb.setPreferredSize(new java.awt.Dimension(114, 32));

        mb_m_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/home.png"))); // NOI18N

        jmi_new.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jmi_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/add.png"))); // NOI18N
        jmi_new.setText("Nuevo Proyecto");
        jmi_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_newActionPerformed(evt);
            }
        });
        mb_m_home.add(jmi_new);

        jmi_open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jmi_open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/open.png"))); // NOI18N
        jmi_open.setText("Abrir Proyecto");
        jmi_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_openActionPerformed(evt);
            }
        });
        mb_m_home.add(jmi_open);

        jmi_save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jmi_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/save.png"))); // NOI18N
        jmi_save.setText("Guardar");
        jmi_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_saveActionPerformed(evt);
            }
        });
        mb_m_home.add(jmi_save);

        jmi_saveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmi_saveAs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/save1.png"))); // NOI18N
        jmi_saveAs.setText("Guardar como");
        jmi_saveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_saveAsActionPerformed(evt);
            }
        });
        mb_m_home.add(jmi_saveAs);

        jmi_exp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/Briefcase.png"))); // NOI18N
        jmi_exp.setText("Exportar como:");

        jmi_color.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jmi_color.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/PNG.png"))); // NOI18N
        jmi_color.setText("PNG - A Color");
        jmi_color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_colorActionPerformed(evt);
            }
        });
        jmi_exp.add(jmi_color);

        jmi_gs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jmi_gs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/PNGGS.png"))); // NOI18N
        jmi_gs.setText("PNG - Escala de grises (Impresion)");
        jmi_gs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_gsActionPerformed(evt);
            }
        });
        jmi_exp.add(jmi_gs);

        mb_m_home.add(jmi_exp);

        jmi_out.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jmi_out.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/Shutdown.png"))); // NOI18N
        jmi_out.setText("Salir");
        jmi_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_outActionPerformed(evt);
            }
        });
        mb_m_home.add(jmi_out);

        mb.add(mb_m_home);

        mb_m_edit.setText("Edicion");

        jmi_undo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jmi_undo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/undo.png"))); // NOI18N
        jmi_undo.setText("Deshacer");
        jmi_undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_undoActionPerformed(evt);
            }
        });
        mb_m_edit.add(jmi_undo);

        jmi_redo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jmi_redo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/redo.png"))); // NOI18N
        jmi_redo.setText("Rehacer");
        jmi_redo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_redoActionPerformed(evt);
            }
        });
        mb_m_edit.add(jmi_redo);

        mb.add(mb_m_edit);

        mb_m_tools.setText("Herramientas");

        jmi_gcp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jmi_gcp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/gen.png"))); // NOI18N
        jmi_gcp.setText("Generador de Cadenas de Prueba");
        jmi_gcp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_gcpActionPerformed(evt);
            }
        });
        mb_m_tools.add(jmi_gcp);

        jmi_upload.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmi_upload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/Database.png"))); // NOI18N
        jmi_upload.setText("Upload to DB server");
        jmi_upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_uploadActionPerformed(evt);
            }
        });
        mb_m_tools.add(jmi_upload);

        mb.add(mb_m_tools);

        mb_m_help.setText("Ayuda");

        jmi_about.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jmi_about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/help.png"))); // NOI18N
        jmi_about.setText("Acerca de...");
        jmi_about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_aboutActionPerformed(evt);
            }
        });
        mb_m_help.add(jmi_about);

        mb.add(mb_m_help);

        mFiller.setEnabled(false);
        mFiller.setPreferredSize(new java.awt.Dimension(20, 19));
        mb.add(mFiller);

        mb_b_report.setBackground(new java.awt.Color(255, 255, 255));
        mb_b_report.setBorder(null);
        mb_b_report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGS/Information2.png"))); // NOI18N
        mb_b_report.setContentAreaFilled(false);
        mb_b_report.setEnabled(false);
        mb.add(mb_b_report);

        setJMenuBar(mb);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tab_click(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_click
        int tab = view.getSelectedIndex();
        if (tab > -1) {
            Rectangle tmp = view.getBoundsAt(tab);
            Rectangle test = new Rectangle((tmp.x + tmp.width) - 20, (tmp.y + tmp.height) - 20, 20, 20);
            if (test.contains(evt.getPoint())) {
                tools.CloseCurrentTab();
            }
            if (evt.getClickCount() == 2 && tmp.contains(evt.getPoint())) {
                tools.CloseCurrentTab();
            }
            if (evt.getButton() == 2 || evt.getButton() == 3 && tmp.contains(evt.getPoint())) {
                tools.showCT(null, evt.getPoint());
            }
        }
    }//GEN-LAST:event_tab_click

    private void tb_b_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_b_addActionPerformed
        jmi_newActionPerformed(evt);
    }//GEN-LAST:event_tb_b_addActionPerformed

    private void jmi_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_openActionPerformed
        try {
            NMS nms = new NMS();
            Object obj = helper.LoadFromFile();

            if ((obj != null)) {
                if (obj instanceof tmSave) {
                    GC gc = new tmController("");
                    gc.updateLoaderSystem((tmSave) obj);
                    nms.setController(gc);
                    if (nms.getController().Load() == GC.LOAD_OK) {
                        view.addTab(nms.getController().getName(), ti, nms);
                        view.setSelectedIndex(view.getTabCount() - 1);
                        nms.paint(nms.getGraphics());
                    }
                }
            }
        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null,
                    config.getText("no.file.loaded.fu"),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    config.getText("no.file.loaded.ees"),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jmi_openActionPerformed

    private void tb_b_ngMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_b_ngMouseReleased
        if (view.getSelectedComponent() instanceof NMS && !tb_b_ng.contains(evt.getX(), evt.getY())) {
            NMS obj = (NMS) view.getSelectedComponent();
            if (!obj.getController().isAnalyzerRunning()) {
                Point p = obj.getMousePosition();
                if (p != null) {
                    obj.getController().addNode(p);
                }
            }
        }
        dragging = false;
        paint(getGraphics());
    }//GEN-LAST:event_tb_b_ngMouseReleased

    private void tb_b_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_b_addMouseClicked
        if (evt.getButton() == 3 || evt.getButton() == 2) {
            tools.showNP(tb_b_add, evt.getPoint());
        }
    }//GEN-LAST:event_tb_b_addMouseClicked

    private void tb_b_ngMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_b_ngMousePressed
        if (view.getSelectedComponent() instanceof NMS) {
            dragging = true;
        }
    }//GEN-LAST:event_tb_b_ngMousePressed

    private void tb_b_ngMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_b_ngMouseDragged
        if (view.getMousePosition() != null) {
            paint(getGraphics());
        }
    }//GEN-LAST:event_tb_b_ngMouseDragged

    private void tb_b_alphaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_b_alphaActionPerformed
        if (view.getSelectedComponent() instanceof NMS) {
            if (!((NMS) view.getSelectedComponent()).getController().isAnalyzerRunning()) {
                NMS obj = (NMS) view.getSelectedComponent();
                obj.getController().initAlphabet();
                tape = obj.getController().alphabet;
                mb_b_report.setText(config.getText("alpha") + " -> " + ((tape == null) ? "{ }" : tape));
            }
        }
    }//GEN-LAST:event_tb_b_alphaActionPerformed

    private void tb_b_ngMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_b_ngMouseClicked
        if (view.getSelectedComponent() instanceof NMS) {
            NMS obj = (NMS) view.getSelectedComponent();
            if (!obj.getController().isAnalyzerRunning()) {
                if (evt.getButton() == 3 || evt.getButton() == 2) {
                    tools.showNG(tb_b_ng, evt.getPoint());
                } else {
                    obj.TryToAdd(-1);
                }
            }
        }
    }//GEN-LAST:event_tb_b_ngMouseClicked

    private void tb_b_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_b_saveActionPerformed
        jmi_saveActionPerformed(evt);
    }//GEN-LAST:event_tb_b_saveActionPerformed

    private void tb_b_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_b_openActionPerformed
        jmi_openActionPerformed(evt);
    }//GEN-LAST:event_tb_b_openActionPerformed

    private void tb_b_undoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_b_undoActionPerformed
        jmi_undoActionPerformed(evt);
    }//GEN-LAST:event_tb_b_undoActionPerformed

    private void tb_b_redoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_b_redoActionPerformed
        jmi_redoActionPerformed(evt);
    }//GEN-LAST:event_tb_b_redoActionPerformed

    private void tb_b_reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_b_reloadActionPerformed
        if (view.getSelectedComponent() instanceof NMS) {
            if (!((NMS) view.getSelectedComponent()).getController().isAnalyzerRunning()) {
                String str = strIn.getText();
                if (!"".equals(str)) {
                    ((NMS) view.getSelectedComponent()).getController().InitAnalyzer(str);
                }

            }
        }
    }//GEN-LAST:event_tb_b_reloadActionPerformed

    private void tb_b_playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_b_playActionPerformed
        if (view.getSelectedComponent() instanceof NMS) {
            tb_b_reloadActionPerformed(null);
            ((NMS) view.getSelectedComponent()).getController().Start();
            if (((NMS) view.getSelectedComponent()).getController().isAnalyzerRunning()) {
                strIn.setText("");
                mb_b_report.setText(config.getText("alpha") + ": " + ((NMS) view.getSelectedComponent()).getController().alphabet);
            }
        }
    }//GEN-LAST:event_tb_b_playActionPerformed

    private void tb_b_pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_b_pauseActionPerformed
        if (view.getSelectedComponent() instanceof NMS) {
            ((NMS) view.getSelectedComponent()).getController().Pause();
        }
    }//GEN-LAST:event_tb_b_pauseActionPerformed

    private void tb_b_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_b_stopActionPerformed
        if (view.getSelectedComponent() instanceof NMS) {
            ((NMS) view.getSelectedComponent()).getController().Stop();
            strIn.setText("");
        }
    }//GEN-LAST:event_tb_b_stopActionPerformed

    private void tb_b_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_b_prevActionPerformed
        if (view.getSelectedComponent() instanceof NMS) {
            ((NMS) view.getSelectedComponent()).getController().Back();
        }
    }//GEN-LAST:event_tb_b_prevActionPerformed

    private void tb_b_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_b_nextActionPerformed
        if (view.getSelectedComponent() instanceof NMS) {
            ((NMS) view.getSelectedComponent()).getController().Next();
        }
    }//GEN-LAST:event_tb_b_nextActionPerformed
    String tape;

    private void viewStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_viewStateChanged
        if (view.getSelectedComponent() instanceof NMS) {
            setVisibleTuring(true);
            GC gc = ((NMS) view.getSelectedComponent()).getController();
            tape = gc.alphabet;
            mb_b_report.setText(" " + config.getText("alpha") + " -> " + ((tape == null) ? "{ }" : tape));
            if (gc.getCurrentSTR() == null) {
                strIn.setText("");
            } else {
                if (gc.isAnalyzerRunning()) {
                    strIn.setText(config.getText("running.analyze"));
                } else {
                    strIn.setText("");
                }
            }
        } else {
            setVisibleTuring(false);
        }
    }//GEN-LAST:event_viewStateChanged

    private void strInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_strInKeyPressed
        if (view.getSelectedComponent() instanceof NMS) {
            NMS obj = (NMS) view.getSelectedComponent();
            if (obj.getController().isAnalyzerRunning()) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_strInKeyPressed

    private void strInKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_strInKeyTyped
        if (view.getSelectedComponent() instanceof NMS) {
            NMS obj = (NMS) view.getSelectedComponent();
            if (obj.getController().isAnalyzerRunning()) {
                evt.consume();
            } else {
                if (tape == null) {
                    ((NMS) view.getSelectedComponent()).getController().nc.quickReport(config.getText("alpha.not.set"));
                    evt.consume();
                } else {
                    if (tape.contains(String.valueOf(evt.getKeyChar()))) {
                    } else {
                        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                            tb_b_reloadActionPerformed(null);
                        } else if (evt.getKeyChar() == KeyEvent.VK_BACK_SPACE
                                || evt.getKeyChar() == KeyEvent.VK_DELETE
                                || evt.getKeyChar() == KeyEvent.VK_COPY
                                || evt.getKeyChar() == KeyEvent.VK_PASTE) {
                        } else {
                            ((NMS) view.getSelectedComponent()).getController().nc.quickReport(" \"" + evt.getKeyChar() + "\" " + config.getText("not.in.tape"));
                            evt.consume();
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_strInKeyTyped

    private void strInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_strInMouseClicked
        if (view.getSelectedComponent() instanceof NMS) {
            if (strIn.getText().contains(config.getText("running.analyze")) && !((NMS) view.getSelectedComponent()).getController().isAnalyzerRunning()) {
                strIn.setText("");
            }
        }
    }//GEN-LAST:event_strInMouseClicked

    private void jmi_aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_aboutActionPerformed
        JOptionPane.showMessageDialog(null,
                new AboutBox(),
                config.getText("jmi_about"),
                JOptionPane.PLAIN_MESSAGE,
                new ImageIcon(super.getClass().getResource("/IMGS/A&J-1.png")));
    }//GEN-LAST:event_jmi_aboutActionPerformed

    private void jmi_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_saveActionPerformed
        if (view.getSelectedComponent() instanceof NMS) {
            if (!((NMS) view.getSelectedComponent()).getController().isAnalyzerRunning()) {
                ((NMS) view.getSelectedComponent()).getController().Save();
                int index = view.getSelectedIndex();
                view.setTitleAt(index,
                        ((NMS) view.getSelectedComponent()).getController().getName());
            }
        }
    }//GEN-LAST:event_jmi_saveActionPerformed

    private void jmi_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_newActionPerformed
        int tab = 0;
        if (view.getTabCount() == 0) {
            view.addTab(config.getText("start_page"), ti, pi);
        } else {
            if (view.getComponentAt(tab) instanceof StartUI) {
                view.setSelectedIndex(tab);
            } else {
                view.insertTab(config.getText("start_page"), ti, pi, config.getText("start_page"), tab);
                view.setSelectedIndex(tab);
            }
        }
    }//GEN-LAST:event_jmi_newActionPerformed

    private void jmi_undoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_undoActionPerformed
        if (view.getSelectedComponent() instanceof NMS) {
            if (!((NMS) view.getSelectedComponent()).getController().isAnalyzerRunning()) {
                ((NMS) view.getSelectedComponent()).getController().Undo();
            }
        }
    }//GEN-LAST:event_jmi_undoActionPerformed

    private void jmi_redoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_redoActionPerformed
        if (view.getSelectedComponent() instanceof NMS) {
            if (!((NMS) view.getSelectedComponent()).getController().isAnalyzerRunning()) {
                ((NMS) view.getSelectedComponent()).getController().Redo();
            }
        }
    }//GEN-LAST:event_jmi_redoActionPerformed

    private void jmi_saveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_saveAsActionPerformed
        if (view.getSelectedComponent() instanceof NMS) {
            if (!((NMS) view.getSelectedComponent()).getController().isAnalyzerRunning()) {
                ((NMS) view.getSelectedComponent()).getController().SaveAs();
            }
        }
    }//GEN-LAST:event_jmi_saveAsActionPerformed

    private void jmi_colorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_colorActionPerformed
        if (view.getSelectedComponent() instanceof NMS) {
            NMS obj = ((NMS) view.getSelectedComponent());
            if (!obj.getController().isAnalyzerRunning()) {
                obj.getController().ExportAsImageRGB(obj.getVHW(), obj.getVHH());
            }
        }
    }//GEN-LAST:event_jmi_colorActionPerformed

    private void jmi_gsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_gsActionPerformed
        if (view.getSelectedComponent() instanceof NMS) {
            NMS obj = ((NMS) view.getSelectedComponent());
            if (!obj.getController().isAnalyzerRunning()) {
                obj.getController().ExportAsImageGrayScale(obj.getVHW(), obj.getVHH());
            }
        }
    }//GEN-LAST:event_jmi_gsActionPerformed

    private void jmi_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_outActionPerformed
        int count = view.getTabCount();
        boolean go = true;
        for (int i = count - 1; i >= 0 && go; i--) {
            go = tools.CloseTabAt(i);
        }
        if (go && view.getTabCount() == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jmi_outActionPerformed

    private void jmi_gcpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_gcpActionPerformed
        if (((NMS) view.getSelectedComponent()).getController().alphabet == null) {
            ((NMS) view.getSelectedComponent()).getController().nc.quickReport(config.getText("alpha.not.set"));
        } else {
            if (!((NMS) view.getSelectedComponent()).getController().isAnalyzerRunning()) {
                MultiAnalisysUI obj = new MultiAnalisysUI(
                        this, true,
                        ((NMS) view.getSelectedComponent()).getController());
                obj.setVisible(true);
            }
        }
    }//GEN-LAST:event_jmi_gcpActionPerformed

    private void tb_b_optActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_b_optActionPerformed
        OptionsUI opt = new OptionsUI(this);
        opt.setVisible(true);
    }//GEN-LAST:event_tb_b_optActionPerformed

    private void jmi_uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_uploadActionPerformed
        if (view.getSelectedComponent() instanceof NMS) {
            NMS obj = ((NMS) view.getSelectedComponent());
            if (!obj.getController().isAnalyzerRunning()) {
                
                DBRD_UI pan = new DBRD_UI(obj.getController().getName());
                int res = JOptionPane.showConfirmDialog(this, pan, "Input Data", JOptionPane.OK_CANCEL_OPTION);
                if (res != JOptionPane.CANCEL_OPTION) {
                    obj.getController().uploadMach(pan.getNameSel(), pan.getDesc(),obj.getVHH(),obj.getVHW());
                }
            }
        }
    }//GEN-LAST:event_jmi_uploadActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenuBarBackground;
    private javax.swing.JPanel background;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JMenuItem jmi_about;
    private javax.swing.JMenuItem jmi_color;
    private javax.swing.JMenu jmi_exp;
    private javax.swing.JMenuItem jmi_gcp;
    private javax.swing.JMenuItem jmi_gs;
    private javax.swing.JMenuItem jmi_new;
    private javax.swing.JMenuItem jmi_open;
    private javax.swing.JMenuItem jmi_out;
    private javax.swing.JMenuItem jmi_redo;
    private javax.swing.JMenuItem jmi_save;
    private javax.swing.JMenuItem jmi_saveAs;
    private javax.swing.JMenuItem jmi_undo;
    private javax.swing.JMenuItem jmi_upload;
    private javax.swing.JMenu mFiller;
    private javax.swing.JMenuBar mb;
    private javax.swing.JMenu mb_b_report;
    private javax.swing.JMenu mb_m_edit;
    private javax.swing.JMenu mb_m_help;
    private javax.swing.JMenu mb_m_home;
    private javax.swing.JMenu mb_m_tools;
    private javax.swing.JTextField strIn;
    private javax.swing.JToolBar tb;
    private javax.swing.JButton tb_b_add;
    private javax.swing.JButton tb_b_alpha;
    private javax.swing.JButton tb_b_next;
    private javax.swing.JButton tb_b_ng;
    private javax.swing.JButton tb_b_open;
    private javax.swing.JButton tb_b_opt;
    private javax.swing.JButton tb_b_pause;
    private javax.swing.JButton tb_b_play;
    private javax.swing.JButton tb_b_prev;
    private javax.swing.JButton tb_b_redo;
    private javax.swing.JButton tb_b_reload;
    private javax.swing.JButton tb_b_save;
    private javax.swing.JButton tb_b_stop;
    private javax.swing.JButton tb_b_undo;
    public final javax.swing.JTabbedPane view = new javax.swing.JTabbedPane();
    // End of variables declaration//GEN-END:variables

    private void my_Init() {
        tools.Init(view);
    }

    private void setVisibleTuring(boolean val) {
        tb_b_save.setVisible(val);
        tb_b_undo.setVisible(val);
        tb_b_redo.setVisible(val);
        tb_b_ng.setVisible(val);
        strIn.setVisible(val);
        tb_b_reload.setVisible(val);
        tb_b_alpha.setVisible(val);
        tb_b_prev.setVisible(val);
        tb_b_next.setVisible(val);
        tb_b_play.setVisible(val);
        tb_b_pause.setVisible(val);
        tb_b_stop.setVisible(val);
        mb_b_report.setVisible(val);
        jmi_save.setEnabled(val);
        jmi_saveAs.setEnabled(val);
        jmi_exp.setEnabled(val);
        jmi_undo.setEnabled(val);
        jmi_redo.setEnabled(val);
        jmi_gcp.setEnabled(val);
        jmi_upload.setEnabled(val);
    }

    @Override
    public void reloadLang() {
        mb_m_home.setToolTipText(config.getText("mb_m_home"));
        jmi_new.setText(config.getText("jmi_new"));
        jmi_open.setText(config.getText("jmi_open"));
        jmi_save.setText(config.getText("jmi_save"));
        jmi_saveAs.setText(config.getText("jmi_saveAs"));
        jmi_exp.setText(config.getText("jmi_exp"));
        jmi_color.setText(config.getText("jmi_exp.jmi_color"));
        jmi_gs.setText(config.getText("jmi_exp.jmi_gs"));
        jmi_out.setText(config.getText("jmi_out"));

        mb_m_edit.setText(config.getText("mb_m_edit"));
        jmi_undo.setText(config.getText("tb_b_undo"));
        jmi_redo.setText(config.getText("tb_b_redo"));

        mb_m_tools.setText(config.getText("mb_m_tools"));
        jmi_gcp.setText(config.getText("jmi_gcp"));

        mb_m_help.setText(config.getText("mb_m_help"));
        jmi_about.setText(config.getText("jmi_about"));

        tb_b_add.setToolTipText(config.getText("tb_b_add"));
        tb_b_open.setToolTipText(config.getText("tb_b_open"));
        tb_b_save.setToolTipText(config.getText("tb_b_save"));
        tb_b_opt.setToolTipText(config.getText("tb_b_opt"));
        tb_b_undo.setToolTipText(config.getText("tb_b_undo"));
        tb_b_redo.setToolTipText(config.getText("tb_b_redo"));
        tb_b_ng.setToolTipText(config.getText("tb_b_ng"));
        strIn.setToolTipText(config.getText("strIn"));
        tb_b_reload.setToolTipText(config.getText("tb_b_reload"));
        tb_b_alpha.setToolTipText(config.getText("tb_b_alpha"));
        tb_b_prev.setToolTipText(config.getText("tb_b_prev"));
        tb_b_next.setToolTipText(config.getText("tb_b_next"));
        tb_b_play.setToolTipText(config.getText("tb_b_play"));
        tb_b_pause.setToolTipText(config.getText("tb_b_pause"));
        tb_b_stop.setToolTipText(config.getText("tb_b_stop"));
    }
}
