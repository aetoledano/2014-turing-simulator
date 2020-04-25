
package LOGIC.TM;

import java.io.Serializable;

/**
 *
 * @author rsegui
 */
public class Transition implements Serializable {

    /**
     *
     */
    public char letter;

    /**
     *
     */
    public char change;

    /**
     *
     */
    public char action;

    /**
     *
     * @param letter
     * @param change
     * @param action
     */
    public Transition(char letter, char change, char action) {
        this.letter = letter;
        this.change = change;
        this.action = action;
    }

    /**
     *
     * @return
     */
    public String toString() {
        char[] str = new char[5];
        str[0] = letter;
        str[1] = ':';
        str[2] = change;
        str[3] = ',';
        str[4] = action;
        return (String.copyValueOf(str));
    }

}
