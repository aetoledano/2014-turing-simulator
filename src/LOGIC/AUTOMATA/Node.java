
package LOGIC.AUTOMATA;

import java.io.Serializable;

/**
 *
 * @author rsegui
 */
public class Node implements Serializable{
    int number;
    char type;
    //los tipos de nodos pueden ser
    //i = initial, n = normal, f = final, e=especial
    //el estado especial es usado para determinar
    //cuando un nodo inicial es al mismo tiempo final
        
    /**
     *
     * @return
     */
    public char getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(char type) {
        this.type = type;
    }
        
    /**
     *
     * @param number
     */
    public Node(int number) {
        this.number = number;      
    }

    /**
     *
     * @return
     */
    public int getNumber() {
        return number;
    }

    /**
     *
     */
    public void decNumber(){
        number--;
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node) )
            return false;
        else
            return ((Node)obj).getNumber() == number;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "q"+Integer.toString(number);
    }
    
}
