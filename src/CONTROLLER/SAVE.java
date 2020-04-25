package CONTROLLER;

import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author rsegui
 */
public class SAVE implements Serializable {

    private String name;
    private String alphabet;
    private Point loc[];
    private String file_addr;

    /**
     *
     * @param name
     * @param alphabet
     * @param loc
     */
    public SAVE(String name, String alphabet, Point[] loc) {
        this.name = name;
        this.alphabet = alphabet;
        this.loc = loc;
        this.file_addr = null;
    }

    /**
     *
     * @return
     */
    public String getFile_addr() {
        return file_addr;
    }

    /**
     *
     * @param file_addr
     */
    public void setFile_addr(String file_addr) {
        this.file_addr = file_addr;
    }

    /**
     *
     * @return
     */
    public String getAlphabet() {
        return alphabet;
    }

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
    public Point[] getLoc() {
        return loc;
    }
}
