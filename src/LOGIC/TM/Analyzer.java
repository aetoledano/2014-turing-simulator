package LOGIC.TM;

import CONTROLLER.TerminatingException;
import NMS.NotifyCenter;
import SUPPORT.config;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rsegui
 */
public class Analyzer {

    /*
     para saber el tipo de machine  que se esta analizando  */
    boolean langRecognition;
    /**
     *
     */
    protected final int MAXL;

    protected int cursor;

    protected char[] tape_alphabet;

    /**
     *
     */
    protected State state;

    /**
     *
     */
    protected tmNetWork net;

    /**
     *
     */
    protected Validator validator;

    /**
     *
     */
    protected Stack<Move> moves;

    protected TuringString text;

    /**
     *
     * @param net
     * @param input
     * @param alphabet
     * @param nc
     * @throws TerminatingException
     */
    public Analyzer(tmNetWork net, String input, char[] alphabet, NotifyCenter nc, boolean enabledWarnings) throws TerminatingException {
        this.MAXL = input.length();
        this.net = net;
        this.text = new TuringString(input);
        this.tape_alphabet = alphabet;
        this.cursor = 0;
        this.state = net.states.get(0);
        this.moves = new Stack<Move>();
        this.moves.add(new Move(state, cursor, text.Current()));
        this.validator = new Validator(net, tape_alphabet);

        langRecognition = true;
        if (!validator.haveEndStates()) {
            if (enabledWarnings) {
                nc.warningReport(config.getText("warning") + ": " + config.getText("no.acceptance.st.def"));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                }
            }
            langRecognition = false;
        } else {
            boolean wrong = validator.ConnectedFirstWithEnd();
            if (wrong) {
                throw new TerminatingException(config.getText("no.final.st.reached"), 6, langRecognition);
            }
        }

        ArrayList<Integer> states = validator.ValidateGraph();

        if (!states.isEmpty()) {
            String ss = "";
            ss += " q" + String.valueOf(states.get(0));
            for (int i = 1; i < states.size(); i++) {
                ss += " ,q" + String.valueOf(states.get(i));
            }
            if (enabledWarnings) {
                nc.warningReport(
                        config.getText("warning")
                        + ": " + config.getText("never.reached") + " :" + ss);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    public boolean isForLangRecognition() {
        return langRecognition;
    }

    public Move getNextMove() throws TerminatingException {

        Link link = null;
        Transition transition = null;

        ArrayList<Link> ls = net.links.get(state.id);
        ArrayList<Transition> ts;

        int next = -1;
        for (int i = 0; i < ls.size() && next == -1; i++) {
            link = ls.get(i);
            ts = link.transitions;
            char current = text.Current();
            for (int j = 0; j < ts.size(); j++) {
                transition = ts.get(j);
                if (transition.letter == current) {
                    next = i;
                    break;
                }
            }
        }

        if (next == -1) {
            if (state.tp == 2 || state.tp == 3) {
                //Termino en un estado final...
                throw new TerminatingException(
                        config.getText("te1"),
                        4,
                        state.id,
                        text.Current(),
                        getTuringString().getOriginal(),
                        getTuringString().getInput(),
                        getTuringString().getOutput(),
                        langRecognition);
            } else {
                //Termino en un estado no final...
                throw new TerminatingException(
                        config.getText("te1"),
                        2,
                        state.id,
                        text.Current(),
                        getTuringString().getOriginal(),
                        getTuringString().getInput(),
                        getTuringString().getOutput(),
                        langRecognition);
            }
        }

        char clast = text.Current();
        state = link.destin;
        text.Set(transition.change);
        cursor = 0;
        if (transition.action == 'L') {
            text.Last();
            cursor = -1;
        } else if (transition.action == 'R') {
            text.Next();
            cursor = 1;
        }

        if (text.Empty()) {
            //Se termino la cadena...
            if (state.tp == 2 || state.tp == 3) {
                //Termino en un estado final...
                throw new TerminatingException(
                        config.getText("te2"),
                        3,
                        state.id,
                        clast,
                        getTuringString().getOriginal(),
                        getTuringString().getInput(),
                        getTuringString().getOutput(),langRecognition);
            } else {
                //Termino en un estado no final...
                throw new TerminatingException(
                        config.getText("te2"),
                        1,
                        state.id,
                        clast,
                        getTuringString().getOriginal(),
                        getTuringString().getInput(),
                        getTuringString().getOutput(), langRecognition);
            }
        }
        Move move = new Move(state, cursor, clast);
        moves.push(move);
        return move;
    }

    /**
     *
     * @return
     */
    public Move getPreviousMove() {

        Move move = moves.peek();
        if (moves.size() != 1) {
            moves.pop();
        }

        state = move.state;
        cursor = move.cursor;

        if (cursor == -1) {
            text.Next();
        }
        if (cursor == 1) {
            text.Last();
        }

        text.Set(move.ch);

        return move;
    }

    /**
     *
     */
    public void restart() {
        /*Modificar*/
    }

    /**
     *
     * @param from
     * @return
     * @throws Exception
     */
    public char[] posibleTransitions(int from) throws Exception {
        int ap = 0;
        char[] goods = new char[250];
        for (int i = 0; i < tape_alphabet.length; i++) {
            if (!isTransition(from, tape_alphabet[i])) {
                goods[ap++] = tape_alphabet[i];
            }
        }
        return goods;
    }

    /**
     *
     * @param from
     * @param letter
     * @return
     */
    public boolean isTransition(int from, char letter) {
        ArrayList<Link> ls = net.links.get(from);
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

    public TuringString getTuringString() {
        return text;
    }

    public void setTapeAlphabet(String alpha) {
        tape_alphabet = alpha.toCharArray();
    }
}
