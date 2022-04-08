import graphes.IGraph;
import graphes.Sommet;

import java.util.ArrayList;
import java.util.HashMap;

public class PCCDijkstra {
    private static final int inf = Integer.MAX_VALUE;
    public boolean estOk(IGraph graphe) {
        for (int i = 1; i < graphe.getNbNoeuds(); ++i) {
            for (int j = 1; j < graphe.getNbNoeuds(); ++j) {
                if (graphe.getValeur(Sommet.getSommet(i), Sommet.getSommet(j)) < 0)
                    return false;
            }
        }
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


    public static ArrayList<Sommet> Dijkstra(IGraph graphe, Sommet start, Sommet end) {
        int debut = start.getValeur(), fin = end.getValeur();
        ArrayList<Sommet> result = new ArrayList<Sommet>();
        HashMap<Integer,Integer> dijkstra = new HashMap<Integer, Integer>();  //sommet clé prede valeur
        HashMap<Integer,Integer> tab = new HashMap<Integer,Integer>();

        for (int i = 1; i <= graphe.getNbNoeuds(); ++i) {
            tab.put(i, inf);
        }
        tab.put(debut, 0);
        int k = debut;
        while (k != fin) {
            for (int key : tab.keySet()) {
                if (graphe.aArc(Sommet.getSommet(k), Sommet.getSommet(key))) {
                    if (tab.get(key) > tab.get(k) + graphe.getValeur(Sommet.getSommet(k), Sommet.getSommet(key)))
                        dijkstra.put(key,k);
                    tab.put(key, Integer.min(tab.get(key), tab.get(k) + graphe.getValeur(Sommet.getSommet(k), Sommet.getSommet(key))));
                }
            }
            if (k == fin) {
                dijkstra.put(fin, k);
            }
            tab.remove(k);
            //dijkstra.put(k,prede);
            k = minimum(tab);
        }
        System.out.println(dijkstra);
        result.add(result.size(),end);
        int cmpt = dijkstra.remove(fin);
        int cmp = 1;
        while (cmpt != debut) {
            result.add(result.size()-cmp, Sommet.getSommet(cmpt));
            cmpt = dijkstra.remove(cmpt);
            ++cmp;
        }
        result.add(0,start);
        return result;
    }
}

