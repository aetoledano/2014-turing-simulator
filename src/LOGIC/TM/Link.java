package LOGIC.TM;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author rsegui
 */
public class Link implements Serializable {

    /**
     *
     */
    public ArrayList<Transition> transitions;

    /**
     *
     */
    public State destin;

    /**
     *
     * @param destin
     * @param letter
     * @param change
     * @param action
     */
    public Link(State destin, char letter, char change, char action) {
        this.destin = destin;
        this.transitions = new ArrayList<Transition>();
        this.transitions.add(new Transition(letter, change, action));
    }

    /**
     *
     * @param letter
     * @param change
     * @param action
     */
    public void addTransition(char letter, char change, char action) {
        transitions.add(new Transition(letter, change, action));
    }

    /**
     *
     * @return
     */
    public String ViewTransitions() {
        String str = new String();
        Iterator<Transition> it = transitions.iterator();
        while (it.hasNext()) {
            str += it.next().toString();
            if (it.hasNext()) {
                str += "@";
            }
        }
        return str;
    }
}
