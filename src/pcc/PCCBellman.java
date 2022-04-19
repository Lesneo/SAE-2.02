package pcc;

import graphes.IGraph;
import graphes.Sommet;

import java.util.ArrayList;
import java.util.HashMap;

public class PCCBellman {
    private static final int inf = Integer.MAX_VALUE;

    public static ArrayList<Sommet> bellman(IGraph graphe, Sommet start, Sommet end) {
        int debut = start.getValeur(), fin = end.getValeur();
        HashMap<Integer,Integer> chemin = new HashMap<Integer, Integer>();  //sommet clé prede valeur
        HashMap<Integer,Integer> tab = new HashMap<Integer,Integer>();

        ArrayList<Sommet> dijkstra = new ArrayList<Sommet>();

        for (int i = 1; i <= graphe.getNbNoeuds(); ++i) {
            tab.put(i, inf);
        }
        tab.put(debut, 0);

        for (int i = 2; i <= graphe.getNbNoeuds(); i++) {
            for (int j = 1; j <= graphe.getNbNoeuds(); ++j) {
                if (graphe.aArc(Sommet.getSommet(j),Sommet.getSommet(i))) {
                    tab.put(i,Integer.min(graphe.getValeur(Sommet.getSommet(j),Sommet.getSommet(i)) + tab.get(j), tab.get(i)));
                    chemin.put(i,j);
                }

            }

        }
        int cmpt = chemin.remove(fin);
        int cmp = 1;
        dijkstra.add(dijkstra.size(),end);
        while (cmpt != debut) {
            dijkstra.add(dijkstra.size()-cmp, Sommet.getSommet(cmpt));
            cmpt = chemin.remove(cmpt);
            ++cmp;
            System.out.println(dijkstra);
        }
        dijkstra.add(0,start);

        return dijkstra;
    }
}
