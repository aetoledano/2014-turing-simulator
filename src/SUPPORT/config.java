package SUPPORT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author rsegui
 */
public class config {

    private static final HashMap<String, String> lang = new HashMap<String, String>();
    private static int[] configs = new int[2];

    /**
     *
     */
    public static void LoadConfig() {
        File c_file = new File("config.ini");

        if (c_file.exists()) {
            try {
                LoadFromFile(c_file);
            } catch (Exception ex) {
                LoadDefault(c_file);
            }
        } else {
            LoadDefault(c_file);
        }
        //language settings
        switch (configs[0]) {
            case 0:
                LoadSpanish();
                break;
            case 1:
                LoadEnglish();
                break;
            default:
                LoadSpanish();
                break;
        }
    }

    private static void LoadFromFile(File f) throws Exception {
        InputStream conf;
        conf = new FileInputStream(f);
        BufferedReader reader = new BufferedReader(new InputStreamReader(conf));
        String read;
        int i = 0;
        while ((read = reader.readLine()) != null) {
            if (read.charAt(0) != '#' && i < configs.length) {
                configs[i] = Integer.valueOf(read);
                i++;
            }
        }
    }

    private static void LoadDefault(File f) {
        try {
            PrintWriter p = new PrintWriter(new FileOutputStream(f));
            InputStream conf;
            conf = (new Integer(0)).getClass().getResourceAsStream("/SUPPORT/default_config_file");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conf));
            String read;
            int i = 0;
            while ((read = reader.readLine()) != null) {
                p.println(read);
                if (read.charAt(0) != '#' && i < configs.length) {
                    configs[i] = Integer.valueOf(read);
                    i++;
                }
            }
            p.close();
        } catch (IOException ex) {
            Logger.getLogger(config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public static void SaveCurrentConfig() {
        try {
            PrintWriter p = new PrintWriter(new FileOutputStream(new File("config.ini")));
            p.println("#language configuration");
            p.println("# 0->Spanish 1->English");
            p.println(Integer.toString(configs[0]));
            p.println("#analizer speed in ms");
            p.println(Integer.toString(configs[1]));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Unable to save config!" + ex.getMessage());
        }

    }

    //analizer speed
    /**
     *
     * @return
     */
    public static int getCurrentAnalyzerSpeed() {
        return configs[1];
    }

    /**
     *
     * @param new_speed
     */
    public static void adjustAnalyzerSpeed(int new_speed) {
        configs[1] = new_speed;
    }

    //language selection
    /**
     *
     * @return
     */
    public static int getCurrentLanguageNumber() {
        return configs[0];
    }

    /**
     *
     * @return
     */
    public static ImageIcon getCurrentLanguageIcon() {
        URL location;
        switch (configs[0]) {
            case 0:
                location = config.class.getResource("/IMGS/spain.png");
                break;
            case 1:
                location = config.class.getResource("/IMGS/britain.png");
                break;
            default:
                location = config.class.getResource("/IMGS/spain.png");
                break;
        }
        return new ImageIcon(location);
    }

    /**
     *
     * @param number
     * @return
     */
    public static ImageIcon getLanguageIcon(int number) {
        URL location;
        switch (number) {
            case 0:
                location = config.class.getResource("/IMGS/spain.png");
                break;
            case 1:
                location = config.class.getResource("/IMGS/britain.png");
                break;
            default:
                location = config.class.getResource("/IMGS/spain.png");
                break;
        }
        return new ImageIcon(location);
    }

    /**
     *
     * @param key
     * @return
     */
    public static String getText(String key) {
        return lang.get(key);
    }

    /**
     *
     * @param val
     */
    public static void LoadLanguage(int val) {
        if (configs[0] != val) {
            lang.clear();
            configs[0] = val;
            switch (configs[0]) {
                case 0:
                    LoadSpanish();
                    break;
                case 1:
                    LoadEnglish();
                    break;
                default:
                    LoadSpanish();
                    break;
            }
        }
    }

    private static void LoadSpanish() {
        //MainUI
        //menu File
        lang.put("mb_m_home", "Inicio");
        lang.put("jmi_new", "Nuevo");
        lang.put("jmi_open", "Abrir");
        lang.put("jmi_save", "Guardar");
        lang.put("jmi_saveAs", "Guardar como");
        lang.put("jmi_exp", "Exportar como");
        lang.put("jmi_exp.jmi_color", "PNG - Color");
        lang.put("jmi_exp.jmi_gs", "PNG - Escala de Grises (Impresión)");
        lang.put("jmi_out", "Salir");

        //menu Edit
        lang.put("mb_m_edit", "Edición");

        //menu Tools
        lang.put("mb_m_tools", "Herramientas");
        lang.put("jmi_gcp", "Generador de cadenas de prueba");

        //menu Help
        lang.put("mb_m_help", "Ayuda");
        lang.put("jmi_about", "Acerca de ...");

        //buttons tooltips
        lang.put("tb_b_add", "Nuevo Proyecto");
        lang.put("tb_b_open", "Abrir");
        lang.put("tb_b_save", "Guardar");
        lang.put("tb_b_opt", "Opciones");
        lang.put("tb_b_undo", "Deshacer");
        lang.put("tb_b_redo", "Rehacer");
        lang.put("tb_b_ng", "Insertar Nodo");
        lang.put("strIn", "Presionar \"ENTER\" para inicializar el analizador");
        lang.put("tb_b_reload", "Inicializar Analizador");
        lang.put("tb_b_alpha", "Insertar Alfabeto");
        lang.put("tb_b_prev", "Posición Anterior del Análisis");
        lang.put("tb_b_next", "Posición Siguiente del Análisis");
        lang.put("tb_b_stop", "Detener Análisis");
        lang.put("tb_b_play", "Comenzar Análisis");
        lang.put("tb_b_pause", "Pausar Análisis");

        //show panel start page
        lang.put("no.title", "Sin título ");
        lang.put("start_page", "Pestaña Inicial");
        lang.put("sui.newMachine", "Diseñar Máquina de Türing");
        lang.put("sui.newAutomata", "Diseñar Autómata");
        lang.put("sui.open", "Abrir Proyecto");
        lang.put("sui.examples", "Ejemplos");

        //SUPPORT.tools 
        lang.put("tools.select.all", "Seleccionar todos");
        lang.put("tools.del.selection", "Eliminar seleccionados");
        lang.put("tools.del.all", "Eliminar todos");
        lang.put("tools.del", "Eliminar");
        lang.put("tools.multiple.lnk", "Insertar múltiples enlaces");
        lang.put("tools.change.type", "Cambiar tipo");
        lang.put("tools.close", "Cerrar");
        lang.put("tools.close.all", "Cerrar todas");
        lang.put("tools.close.other", "Cerrar todas menos esta");

        //AnswerUI
        lang.put("result", "Resultado: ");
        lang.put("in.st", "Estado: q");
        lang.put("with.char", "Caracter: ");
        lang.put("reason", "Condición de parada: ");
        lang.put("mach", "Máquina:");
        lang.put("cad.in", "Entrada:  ");
        lang.put("cad.out","Salida:     ");
        
        //MultiAnalisisUI
        lang.put("multi.title", "Vista de Análisis Múltiple");
        lang.put("multi.label1", "Nota: Solo se permiten caracteres de la cinta.");
        lang.put("multi.label3", "         Caracter delimitador -> \\n ");
        lang.put("multi.gen", "Generar cadenas de prueba");
        lang.put("multi.open", "Abrir archivo de cadenas de prueba");
        lang.put("multi.save", "Guardar cadenas de prueba");
        lang.put("multi.play", "Comenzar análisis múltiple");

        //UI.TM.MultiLinkUI
        lang.put("UI.TM.MultiLinkUI.read", "Caracteres disponibles para leer");
        lang.put("UI.TM.MultiLinkUI.write", "Caracteres para escribir");
        lang.put("UI.TM.MultiLinkUI.action", "Mover cabezal a la Derecha o a la Izquierda");
        lang.put("UI.TM.MultiLinkUI.st", "Estado destino");

        //UI.TM.ModifyLinkUI
        lang.put("UI.TM.ModifyLinkUI.add", "Insertar transición");
        lang.put("UI.TM.ModifyLinkUI.del", "Quitar transición seleccionada");
        
        //general + UI general
        lang.put("cad.not.set", "No se ha insertado una cadena todavía");
        lang.put("cad.not.recognized", "Cadena no aceptada");
        lang.put("cad.recognized", "Cadena aceptada");
        lang.put("str", "Cad: ");
        lang.put("str.test", "Cadenas de prueba");
        lang.put("alpha", "Alfabeto ");
        lang.put("auto", "Automático");
        lang.put("manual", "Manual");
        lang.put("mode", "Seleccione el modo");
        lang.put("starting", "Iniciando...");
        lang.put("alpha.not.set", "No se ha insertado un alfabeto todavía");
        lang.put("UI.add.lnk", "Insertar enlace");
        lang.put("UI.done", "Hecho");
        lang.put("running.analyze", "Analizando");
        lang.put("never.reached", "No son alcanzados");

        //turing control
        lang.put("char.izq", "I");
        lang.put("char.der", "D");
        lang.put("not.in.tape","no está en la cinta.");
        lang.put("insert.alpha.ribbon", "Inserte el alfabeto de la cinta");
        lang.put("st", "Estados: ");
        lang.put("unable.add.trans", "No se pueden añadir más transiciones");
        lang.put("st0.del", "El estado inicial no debe ser eliminado");
        lang.put("no.final.st.found", "Ne se han definido estados de aceptación");
        lang.put("no.final.st.reached", "No se alcanzan estados de aceptación desde el estado inicial");
        lang.put("te1"," no está definida.");
        lang.put("te1.exp", "No había transición desde este estado con el caracter encontrado");
        lang.put("te2","Se alcanzó el final de la cadena de entrada");
        
        //questions
        lang.put("question.save", "¿Desea guardar ");

        //errors
        lang.put("no.file.loaded.fu", "Imposible Cargar Fichero - Formato desconocido!");
        lang.put("no.file.loaded.ees", "Imposible Cargar Fichero - Error de E/S!");
        lang.put("no.file.loaded.nf", "Imposible Cargar Fichero - No encontrado!");
        lang.put("no.file.saved.ees", "Imposible Guardar Fichero - Error de E/S!");
        lang.put("node.not.added", "Imposible insertar nodo");
        lang.put("lnk.not.added", "Imposible insertar enlace");

        //warnings
        lang.put("are.never.visited", " Nunca son visitados!");
        lang.put("warning", "Advertencia");
        lang.put("no.acceptance.st.def", "No se han definido estados de aceptación!");
        lang.put("file.not.saved", "Ningún archivo guardado!");
        
        //messages
        lang.put("net.empty", "La red está vacía");
        lang.put("press.play", "Presione \"PLAY\" para comenzar");
        lang.put("saved.to", "Guardado en: ");
        lang.put("db.upload.success", "BD actualizada correctamente!");
        lang.put("samples_page", "Ejemplos");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
        lang.put("", "");
    }

    private static void LoadEnglish() {
        LoadSpanish();
    }
}
