package LOGIC.TM;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import java.util.Stack;

/**
 *
 * @author rsegui
 */
public class Validator {

    private ArrayList<ArrayList<Integer>> graph;
    private ArrayList<ArrayList<Integer>> tree;
    private tmNetWork net;
    private char[] alphabet;
    private int MAXN;

    /**
     *
     * @param net
     * @param alphabet
     */
    public Validator(tmNetWork net, char[] alphabet) {
        this.graph = new ArrayList<ArrayList<Integer>>();
        this.tree = new ArrayList<ArrayList<Integer>>();
        this.alphabet = alphabet;
        this.net = net;

        //<editor-fold defaultstate="collapsed" desc=" Create a Graph">
        ArrayList<Link> links;
        ArrayList<Integer> sons;

        MAXN = net.states.size();

        for (int i = 0; i < net.states.size(); i++) {

            sons = new ArrayList<Integer>();
            links = net.links.get(i);

            for (int j = 0; j < links.size(); j++) {
                sons.add(links.get(j).destin.id);
            }
            graph.add(sons);
        }
        //</editor-fold>
    }
    //<editor-fold defaultstate="collapsed" desc="Declaracion de variables scc-dfs">
    private int ndfs, nscc;
    private int[] dfsnum;
    private int[] lowlink;
    private int[] sccnum;
    private boolean[] marked;
    private Stack<Integer> st;
    //</editor-fold>

    /**
     *
     * @return
     */
    public boolean ConnectedFirstWithEnd() {

        boolean ok = false;
        marked = new boolean[MAXN];
        for (int i = 0; i < MAXN; i++) {
            marked[i] = false;
        }
        Deque<Integer> q = new ArrayDeque<Integer>();
        q.add(0);
        marked[0] = true;

        while (!q.isEmpty()) {

            int x = q.pop();
            int tp = net.states.get(x).tp;
            ok |= (tp == 2 || tp == 3);

            ArrayList<Integer> tmp = graph.get(x);
            for (int i = 0; i < tmp.size(); i++) {
                int v = tmp.get(i);
                if (!marked[v]) {
                    marked[v] = true;
                    q.add(v);
                }
            }
        }
        return !ok;
    }

    /**
     *
     * @return
     */
    public boolean haveEndStates() {
        for (int i = 0; i < net.states.size(); i++) {
            if (net.states.get(i).tp == 2
                    || net.states.get(i).tp == 3) {
                return true;
            }
        }
        return false;
    }

    //<editor-fold defaultstate="collapsed" desc="Validate Graph">

    /**
     *
     * @return
     */
        public ArrayList<Integer> ValidateGraph() {

        ndfs = 0;
        nscc = 0;

        dfsnum = lowlink = sccnum = new int[MAXN];
        marked = new boolean[MAXN];
        st = new Stack<Integer>();

        for (int i = 0; i < MAXN; i++) {
            dfsnum[i] = lowlink[i] = sccnum[i] = 0;
            marked[i] = false;
        }

        for (int i = 0; i < MAXN; i++) {
            if (!marked[i]) {
                scc(i);
            }
        }

        for (int i = 0; i < nscc; i++) {
            tree.add(new ArrayList<Integer>());
        }

        ArrayList<Integer> ls, tt;
        for (int i = 0; i < MAXN; i++) {

            tt = new ArrayList<Integer>();
            ls = graph.get(i);
            for (int j = 0; j < ls.size(); j++) {
                tt.add(sccnum[ls.get(j)] - 1);
            }
            tree.get(sccnum[i] - 1).addAll(tt);
        }

        for (int i = 0; i < MAXN; i++) {
            marked[i] = false;
        }

        dfs(0);

        ArrayList<Integer> orphans = new ArrayList<Integer>();
        boolean good = true;
        for (int i = 0; i < nscc; i++) {
            if (!marked[i]) {
                for (int j = 0; j < MAXN; j++) {
                    if (sccnum[j] == i) {
                        orphans.add(j + 1);
                    }
                }
            }

        }

        return orphans;
    }

    private void scc(int u) {

        marked[u] = true;
        dfsnum[u] = lowlink[u] = ++ndfs;
        st.push(u);

        ArrayList<Integer> links = graph.get(u);
        for (int i = 0; i < links.size(); i++) {
            int v = links.get(i);
            if (!marked[v]) {
                scc(v);
                lowlink[u] = Math.min(lowlink[u], lowlink[v]);
            } else if (sccnum[v] == 0) {
                lowlink[u] = Math.min(lowlink[u], dfsnum[v]);
            }
        }

        if (dfsnum[u] == lowlink[u]) {
            while (sccnum[u] == 0) {
                int v = st.pop();
                sccnum[v] = nscc + 1;
            }
            nscc++;
        }
    }

    private void dfs(int u) {

        marked[u] = true;
        ArrayList<Integer> links = graph.get(u);
        for (int i = 0; i < links.size(); i++) {
            int v = links.get(i);
            if (!marked[v]) {
                dfs(v);
            }
        }
    }
    //</editor-fold>
}
