package pcc;

import graphes.types.Graphe;
import graphes.IGraphe;

import java.util.ArrayList;
import java.util.HashMap;

public class PCCDijkstra {
    private static final int inf = Integer.MAX_VALUE;

    private static boolean NoPath(IGraphe graphe, int start, int end) {
        int longueur = graphe.getNbSommets();
        ArrayList<Integer> sommets = new ArrayList<>();
        ArrayList<Boolean> visitee = new ArrayList<>();
        for (int i = 0; i<longueur; ++i) {
            visitee.add(false);
        }
        sommets.add(start);
        while (!sommets.isEmpty()) {
            int courant = sommets.remove(sommets.size()-1);
            visitee.set(courant-1, true);
            for (int i = 1; i<=longueur; ++i) {
                if (graphe.aArc(courant,i) && visitee.get(i-1) == false) {
                    sommets.add(i);
                    visitee.set(i-1, true);
                }
                else if (graphe.aArc(courant, i) && i == end) {
                    return true;
                }
            }
        }
        return false;

    }

    private static boolean estOk(IGraphe graphe) {
        for (int i = 1; i < graphe.getNbSommets(); ++i)
            for (int j = 1; j < graphe.getNbSommets(); ++j)
                if (graphe.getValuation(i, j) < 0)
                    return false;
        return true;
    }

    private static int minimum(HashMap<Integer, Integer> d) {
        int mini = inf;
        int s = 1;
        for (int clef : d.keySet()) {
            if (d.get(clef) < mini) {
                s = clef;
                mini = d.get(clef);
            }
        }
        return s;
    }


    public static ArrayList<Integer> dijkstra(IGraphe graphe, int start, int end) throws ArcNegatifEx, NoPathEx{
        if (!PCCDijkstra.NoPath(graphe, start, end))
            throw new NoPathEx();
        else if (!PCCDijkstra.estOk(graphe))
            throw new ArcNegatifEx();
        else {
            ArrayList<Integer> dijkstra = new ArrayList<Integer>();
            HashMap<Integer, Integer> chemin = new HashMap<Integer, Integer>();  //sommet clé prede valeur
            HashMap<Integer, Integer> tab = new HashMap<Integer, Integer>();

            for (int i = 1; i <= graphe.getNbSommets(); ++i) {
                tab.put(i, inf);
            }
            tab.put(start, 0);
            int k = start;
            while (k != end) {
                for (int key : tab.keySet()) {
                    if (graphe.aArc(k, key)) {
                        if (tab.get(key) > tab.get(k) + graphe.getValuation(k, key))
                            chemin.put(key, k);
                        tab.put(key, Integer.min(tab.get(key), tab.get(k) + graphe.getValuation(k, key)));
                    }
                }
                if (k == end) {
                    chemin.put(end, k);
                }
                tab.remove(k);
                //dijkstra.put(k,prede);
                k = minimum(tab);
            }
            System.out.println(chemin);
            dijkstra.add(dijkstra.size(), end);
            int cmpt = chemin.remove(end);
            int cmp = 1;
            while (cmpt != start) {
                dijkstra.add(dijkstra.size() - cmp, cmpt);
                cmpt = chemin.remove(cmpt);
                ++cmp;
            }
            dijkstra.add(0, start);
            System.out.println(dijkstra);
            return dijkstra;
        }
    }
}

