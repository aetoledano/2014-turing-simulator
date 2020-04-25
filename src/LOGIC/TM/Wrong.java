
package LOGIC.TM;

/**
 *
 * @author rsegui
 */
public class Wrong {
    
    //Estado no determinista..
    //Caracter q lo hace no determinista...

    /**
     *
     */
        public int state;

    /**
     *
     */
    public char letter;
    
    /**
     *
     * @param state
     * @param letter
     */
    public Wrong( int state, char letter){
        this.state = state;
        this.letter = letter;
    }
}
