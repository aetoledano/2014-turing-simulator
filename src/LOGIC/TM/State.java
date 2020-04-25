package LOGIC.TM;

import java.io.Serializable;

/**
 *
 * @author rsegui
 */
public class State implements Serializable{

    //los estados son
    //0-inicial  1-normal  2-final  3-initial_final

    /**
     *
     */
        public int id;

    /**
     *
     */
    public int tp;

    /**
     *
     * @param id
     * @param tp
     */
    public State(int id, int tp) {
        this.id = id;
        this.tp = tp;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String type = "";
        switch (tp) {
            case 0:
                type = "initial";
                break;
            case 1:
                type = "normal";
                break;
            case 2:
                type = "final";
                break;
            case 3:
                type = "initial+final";
                break;
        }
        return "q" + Integer.toString(id) + "->" + type;
    }

}
