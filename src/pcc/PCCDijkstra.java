package pcc;

import exceptions.ArcNegatifEx;
import exceptions.NoPathEx;
import graphes.IGraphe;
import graphes.IPCC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PCCDijkstra implements IPCC {
    private static final int inf = Integer.MAX_VALUE;

    /**
     * @return true si un chemin existe
     * */
    private static boolean NoPath(IGraphe graphe, int start, int end) {
        if (start == end || graphe.aArc(start,end))
            return true;
        int longueur = graphe.getNbSommets();
        ArrayList<Integer> sommets = new ArrayList<>();
        ArrayList<Boolean> visitee = new ArrayList<>();

        for (int i = 0; i < longueur; ++i) {
            visitee.add(false);
        }

        sommets.add(start);

        while (!sommets.isEmpty()) {
            int courant = sommets.remove(sommets.size()-1);
            visitee.set(courant-1, true);
            for (int i = 1; i <= longueur; ++i) {
                if (graphe.aArc(courant,i) && i == end) {
                    return true;
                }
                else if (graphe.aArc(courant, i) && visitee.get(i-1) == false) {
                    sommets.add(i);
                    visitee.set(i-1, true);
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

    @Override
    public int pc(IGraphe graphe, int debut, int fin, List<Integer> chemin) throws ArcNegatifEx, NoPathEx {
        if (debut == fin) {
            chemin.add(debut);
            return 0;
        }
        else if (!PCCDijkstra.NoPath(graphe, debut, fin))
            throw new NoPathEx();
        else if (!PCCDijkstra.estOk(graphe))
            throw new ArcNegatifEx();
        else {
            HashMap<Integer, Integer> dijkstra = new HashMap<Integer, Integer>();  //sommet clé prede valeur
            HashMap<Integer, Integer> tab = new HashMap<Integer, Integer>();

            for (int i = 1; i <= graphe.getNbSommets(); ++i) {
                tab.put(i, inf);
            }
            tab.put(debut, 0);
            int k = debut;
            while (k != fin) {
                for (int key : tab.keySet()) {
                    if (graphe.aArc(k, key)) {
                        if (tab.get(key) > tab.get(k) + graphe.getValuation(k, key))
                            dijkstra.put(key, k);
                        tab.put(key, Integer.min(tab.get(key), tab.get(k) + graphe.getValuation(k, key)));
                    }
                }
                if (k == fin) {
                    dijkstra.put(fin, k);
                }
                tab.remove(k);
                //dijkstra.put(k,prede);
                k = minimum(tab);
            }
            //System.out.println(dijkstra);
            chemin.add(chemin.size(), fin);
            int cmpt = dijkstra.remove(fin);
            int cmp = 1;
            while (cmpt != debut) {
                chemin.add(chemin.size() - cmp, cmpt);
                cmpt = dijkstra.remove(cmpt);
                ++cmp;
            }
            chemin.add(0, debut);
            //System.out.println(chemin);


            return tab.get(fin);
        }
    }
}

