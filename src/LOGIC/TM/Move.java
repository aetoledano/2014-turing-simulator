
package LOGIC.TM;

/**
 *
 * @author rsegui
 */
public class Move {
    
    /**
     *
     */
    public State state;

    /**
     *
     */
    public int cursor;

    /**
     *
     */
    public char ch;

    /**
     *
     * @param state
     * @param cursor
     * @param ch
     */
    public Move( State state, int cursor,char ch ) {
        this.state = state;
        this.cursor = cursor;
        this.ch = ch ;
    }
}