package CONTROLLER.TM;

import CONTROLLER.SAVE;
import LOGIC.TM.tmNetWork;
import java.awt.Point;

/**
 *
 * @author rsegui
 */
public class tmSave extends SAVE {

    private final tmNetWork net;

    /**
     *
     * @param name
     * @param alphabet
     * @param loc
     * @param net
     */
    public tmSave(String name, String alphabet, Point[] loc, tmNetWork net) {
        super(name, alphabet, loc);
        this.net = net;
    }

    /**
     *
     * @return
     */
    public tmNetWork getNet() {
        return net;
    }
}
