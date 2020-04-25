package LOGIC.AUTOMATA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author rsegui
 */
public class autoNetWork implements Serializable{

    //<editor-fold defaultstate="collapsed" desc="Variables Declaration">
    ArrayList<Node> nodes;
    ArrayList<ArrayList<Link>> adjLst;
    int n_count;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="GEtter Methods">

    /**
     *
     * @return
     */
        public int getN_count() {
        return n_count;
    }

    /**
     *
     * @return
     */
    public ArrayList<ArrayList<Link>> getAdjLst() {
        return adjLst;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Obtener el iterador de la lista de nodos">

    /**
     *
     * @return
     */
        public Iterator getNodesIterator() {
        return nodes.iterator();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">

    /**
     *
     */
        public autoNetWork() {
        nodes = new ArrayList<Node>();
        adjLst = new ArrayList<ArrayList<Link>>();
        n_count = 0;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Add a node">

    /**
     *
     * @param node
     * @return
     */
        public boolean AddNode(Node node) {
        boolean works = true;
        if (n_count < 1000) {
            nodes.add(node);
            adjLst.add(new ArrayList<Link>());
            n_count++;
        } else {
            works = false;
        }
        return works;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Del node">

    /**
     *
     * @param number
     * @return
     */
        public boolean DelNode(int number) {
        boolean works = true;
        if (number > 0 && number < nodes.size()) {
            if (nodes.remove(number) != null) {
                removeDependentLinksOf(number);
                decRestNodes(number);
                n_count--;
            } else {
                works = false;
            }
        } else {
            works = false;
        }
        return works;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="aux: decrementar los nodos restantes after del">
    private void decRestNodes(int number) {
        int len = adjLst.size();
        //if (number < len)
        for (int i = number ; i < len; i++){
            nodes.get(i).decNumber();
        }
    }
    //</editor-fold>
        
    //<editor-fold defaultstate="collapsed" desc="Eliminar los enlaces de los nodos eliminados">
    private void removeDependentLinksOf(int number) {
        adjLst.remove(number);
        int len = adjLst.size();
        ArrayList<Link> lst;
        for (int  i = 0 ;i<len ; i++){
            lst = adjLst.get(i);
            for (int j = 0 ;j<lst.size() ; j++){
                if (lst.get(j).init.getNumber()==number || lst.get(j).end.getNumber()==number){
                    lst.remove(j);
                    i--;
                }
            }
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Add a new link">

    /**
     *
     * @param from
     * @param to
     * @param ribbon
     * @return
     * @throws Exception
     */
        public boolean AddLink(Node from, Node to, char[] ribbon) throws Exception {
        boolean works = true;
     
        int orign = nodes.indexOf(from);
        int dest = nodes.indexOf(to);
        if (orign == -1 || dest == -1) {
            throw new Exception("One of the nodes aren't in the list...");
        }
        boolean puede = true;
        ArrayList<Link> lst = adjLst.get(orign);
        int len = lst.size();
        for (int i = 0; i < len && puede; i++) {
            if (lst.get(i).end.getNumber() == dest) {
                puede = false;
            }
        }
        if (puede) {
            adjLst.get(orign).add(new Link(nodes.get(orign), nodes.get(dest), ribbon));         
        } else {
            JOptionPane.showMessageDialog(null, "The link already exists!");
            works = false;
        }
        return works;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Remover link">
    private void removeLink(int from, int to) {
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Imprimir nodos">

    /**
     *
     */
        public void PrintNodes() {
        Iterator<Node> it = this.getNodesIterator();
        Node k;
        while (it.hasNext()) {
            k = it.next();
            System.out.println("Node -> " + k + " type -> "+ k.getType());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Imprimir lista de adyacencia">

    /**
     *
     */
        public void PrintAdjLst() {
        System.out.println("->Printing Adjacency List:");
        int adj_len = adjLst.size();
        int int_len;
        ArrayList<Link> lst;
        System.out.println("Node count -> " + adj_len);

        for (int i = 0; i < adj_len; i++) {
            lst = adjLst.get(i);
            int_len = lst.size();

            System.out.print(nodes.get(i) + " -> {");
            for (int j = 0; j < int_len; j++) {
                System.out.print(lst.get(j) + "("+lst.get(j).getListAsString()+") , ");
            }
            System.out.print("}");
            System.out.println();
        }
    }
    //</editor-fold>
    
    /**
     *
     * @param number
     * @param type
     */
    public void ChangeNodeType(int number, char type){
        nodes.get(number).setType(type);
    }
}
