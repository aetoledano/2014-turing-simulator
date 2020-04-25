
package LOGIC.AUTOMATA;

import java.io.Serializable;

/**
 *
 * @author rsegui
 */
public class Link implements Serializable{

    Node init;
    Node end;
    char list[];

    /**
     *
     * @param init
     * @param end
     * @param list
     */
    public Link(Node init, Node end, char[] list) {
        this.init = init;
        this.end = end;
        this.list = list;
    }

    /**
     *
     */
    public void decDest(){
        end.decNumber();
    }

    /**
     *
     * @return
     */
    public Node getEnd() {
        return end;
    }

    /**
     *
     * @return
     */
    public Node getInit() {
        return init;
    }

    /**
     *
     * @return
     */
    public char[] getList() {
        return list;
    }

    /**
     *
     * @return
     */
    public String getListAsString(){
        String str = "";
        for (int  i = 0 ;i<list.length ; i++){
            str += list[i];
        }
        return str;
    }
    
    /**
     *
     */
    public void decOrign(){
        init.decNumber();
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return init+"->"+end;
    }

}
