package LOGIC.TM;

import java.util.ArrayList;

/**
 *
 * @author rsegui
 */
public class Generador {

    private int MAXN;
    private tmNetWork net;
    private String lexeme;
    private ArrayList<String> ls;
    private int END;

    /**
     *
     * @param net
     */
    public Generador(tmNetWork net) {
        this.net = net;
        this.lexeme = "";
        this.ls = new ArrayList<String>();
        this.MAXN = net.states.size();
    }

    private ArrayList<Integer> EndEstates() {
        ArrayList<Integer> A = new ArrayList<Integer>();
        for (int i = 0; i < net.states.size(); i++) {
            int tp = net.states.get(i).tp;
            if (tp == 2 || tp == 3) {
                A.add(i);
            }
        }
        return A;
    }

    private boolean[] marked;
    
    /**
     *
     * @return
     */
    public ArrayList<String> Gen(){
        
        lexeme += "";
        ArrayList<Integer> ends = EndEstates();
        for ( int i = 0 ; i < MAXN ; i++ ){
              END = i;
              marked = new boolean[MAXN];
              for ( int j = 0 ; j < MAXN ; j++ )
                    marked[j] = false;
              marked[0] = true;
              Paths(0);
        }
        return ls;
    }
    private void Paths(int u ) {
        
        if ( u == END ){
             ls.add(lexeme);
             return;
        }
        ArrayList<Transition> trans;
        ArrayList<Link> links = net.links.get(u);
        for ( int i = 0 ; i < links.size() ; i++ ){
              int v = links.get(i).destin.id;
              if ( marked[v] ) continue;
              
              marked[v] = true;
              trans = links.get(i).transitions;
              for ( int j = 0 ; j < trans.size() ; j++ ){
                    String letter = String.valueOf(trans.get(j).letter);
                    lexeme += letter ;
                    Paths(v);
                    lexeme  = lexeme.substring(0, lexeme.length()-1);
              }
              marked[v] = false;
        }
    }
}
