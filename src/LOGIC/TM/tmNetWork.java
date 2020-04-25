package LOGIC.TM;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author rsegui
 */
public class tmNetWork implements Serializable {

    /**
     *
     */
    public ArrayList<State> states;

    /**
     *
     */
    public ArrayList<ArrayList<Link>> links;

    /**
     *
     */
    public tmNetWork() {
        this.states = new ArrayList<State>();
        this.links = new ArrayList<ArrayList<Link>>();
    }

    /**
     *
     * @return
     */
    public int getNextStateId() {
        return states.size();
    }

    /**
     *
     * @param st
     * @return
     */
    public boolean addState(State st) {
        if (states.indexOf(st) == -1) {
            states.add(st);
            links.add(new ArrayList<Link>());
            return true;
        }
        return false;
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean delState(int id) {

        if (id >= 0 && id < states.size()) {
            states.remove(id);
            links.remove(id);
            removeAsociateLinks(id);
            decrementStates(id);
            return true;
        }
        return false;
    }

    /**
     *
     * @param a
     * @return
     */
    public boolean removeList(Integer[] a) {
        if (states.isEmpty()) {
            return false;
        }
        for (int i = a.length - 1; i >= 0; i--) {
            states.remove(a[i].intValue());
            links.remove(a[i].intValue());
            removeAsociateLinks(a[i].intValue());
            decrementStates(a[i].intValue());
        }
        return true;
    }

    /**
     *
     * @param from
     * @param to
     * @param letter
     * @param change
     * @param action
     * @throws Exception
     */
    public void addLink(int from, int to, char letter, char change, char action) throws Exception {
        int exist = existLink(from, to);
        if (exist != -1) {
            agregateTransition(from, exist, letter, change, action);
        } else {
            links.get(from).add(new Link(states.get(to), letter, change, action));
        }
    }

    private void agregateTransition(int from, int to, char letter, char change, char action) {
        links.get(from).get(to).addTransition(letter, change, action);
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    public int existLink(int from, int to) {
        ArrayList<Link> ls = links.get(from);
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).destin.id == to) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    public boolean delLink(int from, int to) {

        to = existLink(from, to);
        int N = links.get(from).get(to).transitions.size();
        ArrayList<Transition> transitions = links.get(from).get(to).transitions;

        JRadioButton[] buttons = new JRadioButton[N + 1];
        for (int i = 0; i < N; i++) {
            buttons[i] = new JRadioButton(transitions.get(i).toString());
        }
        buttons[N] = new JRadioButton("All");

        JOptionPane.showMessageDialog(null, buttons);

        boolean all = buttons[N].isSelected();

        if (all) {
            links.get(from).remove(to);
            return true;
        } else {
            boolean some = false;
            for (int i = 0, j = 0; j < buttons.length - 1; i++, j++) {
                if (buttons[j].isSelected()) {
                    transitions.remove(i);
                    i--;
                } else {
                    some = true;
                }
            }
            if (transitions.isEmpty()) {
                links.get(from).remove(to);
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param id
     */
    public void removeAsociateLinks(int id) {

        ArrayList<Link> ls;
        for (int i = 0; i < states.size(); i++) {
            ls = links.get(i);
            for (int j = 0; j < ls.size(); j++) {
                if (ls.get(j).destin.id == id) {
                    ls.remove(j);
                    j--;
                }
            }
        }
    }

    /**
     *
     * @param id
     */
    public void decrementStates(int id) {
        for (int i = id; i < states.size(); i++) {
            states.get(i).id--;
        }
    }

    /**
     *
     * @param id
     * @param tp
     */
    public void changeStateType(int id, int tp) {
        states.get(id).tp = tp;
    }

    /**
     *
     * @param from
     * @param alphabet
     * @return
     */
    public char[] posibleTransitions(int from, char[] alphabet) {
        int ap = 0;
        char[] goods = new char[250];
        for (int i = 0; i < alphabet.length; i++) {
            if (!isTransition(from, alphabet[i])) {
                goods[ap++] = alphabet[i];
            }
        }
        char array[] = new char[ap];
        System.arraycopy(goods, 0, array, 0, ap);
        return array;
    }

    /**
     *
     * @param from
     * @param letter
     * @return
     */
    public boolean isTransition(int from, char letter) {
        ArrayList<Link> ls = links.get(from);
        ArrayList<Transition> trans;
        for (int i = 0; i < ls.size(); i++) {
            trans = ls.get(i).transitions;
            for (int j = 0; j < trans.size(); j++) {
                if (trans.get(j).letter == letter) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param from
     * @return
     */
    public int[] asociateStates(int from) {
        int ap = 0;
        int[] ids = new int[states.size() + 1];
        ArrayList<Link> ls = links.get(from);
        int top = ls.size();
        for (int i = 0; i < ls.size(); i++) {
            ids[ap++] = ls.get(i).destin.id;
        }
        return Arrays.copyOf(ids, top);
    }
}
