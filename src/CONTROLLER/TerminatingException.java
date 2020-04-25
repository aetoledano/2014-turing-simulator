package CONTROLLER;

import SUPPORT.config;

/**
 *
 * @author rsegui
 */
public class TerminatingException extends Exception {

    int TermState;
    Character c;
    int state;
    String original, initial, end;
    boolean isForLangRecognition;

    /**
     *
     * @param message
     * @param Term
     */
    public TerminatingException(String message, int Term,boolean typ) {
        super(message);
        TermState = Term;
        this.state = 0;
        c = null;
        c = '-';
        original = config.getText("cad.not.set");
        initial = "--";
        end = "--";
        isForLangRecognition = typ;
    }

    /**
     *
     * @param message
     * @param Term
     * @param state
     * @param c
     * @param original
     * @param initial
     * @param end
     */
    public TerminatingException(String message, int Term, int state, Character c, String original, String initial, String end,boolean typ) {
        super(message);
        TermState = Term;
        this.state = state;
        this.c = c;
        this.initial = initial;
        this.end = end;
        this.original = original;
        isForLangRecognition = typ;
    }

    /**
     *
     * @return
     */
    public int getTermState() {
        return TermState;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        int tam = (original.length() > 50) ? 50 : original.length();
        return config.getText("str")
                + original.substring(0, tam)
                + ((tam == original.length()) ? "" : "...");
    }

    /**
     *
     * @return
     */
    public String getResult() {
        String str = "";
        switch (TermState) {
            case 1:
                str = config.getText("cad.not.recognized");
                break;
            case 2:
                str = config.getText("cad.not.recognized");
                ;
                break;
            case 3:
                str = config.getText("cad.recognized");
                break;
            case 4:
                str = config.getText("cad.recognized");
                break;
            case 5:
                str = config.getText("no.final.st.found");
                break;
            case 6:
                str = config.getText("no.final.st.reached");
                break;
        }
        return str;
    }

    public boolean isIsForLangRecognition() {
        return isForLangRecognition;
    }

    /**
     *
     * @return
     */
    public boolean wasGoodEnd() {
        return TermState == 3 || TermState == 4;
    }

    /**
     *
     * @return
     */
    public boolean wasBadEnd() {
        return TermState == 1 || TermState == 2;
    }

    /**
     *
     * @return
     */
    public Character getChar() {
        return c;
    }

    /**
     *
     * @return
     */
    public int getState() {
        return state;
    }

    /**
     *
     * @return
     */
    public String getInitialSTR() {
        return initial;
    }

    /**
     *
     * @return
     */
    public String getEndingSTR() {
        return end;
    }

    /**
     *
     * @return
     */
    public String getReason() {
        return getMessage();
    }
}
