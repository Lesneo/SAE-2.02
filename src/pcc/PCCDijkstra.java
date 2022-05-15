package pcc;

import graphes.IGraphe;
import pcc.exceptions.ArcNegatifEx;
import pcc.exceptions.NoPathEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Type de données représentant l'algorithme de Dijkstra héritant de l'interface IPCC */
public class PCCDijkstra implements IPCC {

    /**
     * @brief Indique si un chemin existe entre le début et la fin dans le graphe
     * @param graphe le graphe
     * @param start le début
     * @param end la fin
     * @return vrai si aucun chemin n'existe
     */
    private static boolean NoPath(IGraphe graphe, int start, int end) {
        if (start == end || graphe.aArc(start, end))
            return false;
        int longueur = graphe.getNbSommets();
        ArrayList<Integer> sommets = new ArrayList<>();
        ArrayList<Boolean> visitee = new ArrayList<>();

        for (int i = 0; i < longueur; ++i)
            visitee.add(false);

        sommets.add(start);

        while (!sommets.isEmpty()) {
            int courant = sommets.remove(sommets.size() - 1);
            visitee.set(courant - 1, true);
            for (int i = 1; i <= longueur; ++i) {
                if (graphe.aArc(courant, i) && i == end)
                    return false;
                else if (graphe.aArc(courant, i) && !visitee.get(i - 1)) {
                    sommets.add(i);
                    visitee.set(i - 1, true);
                }
            }
        }
        return true;

    }

    /**
     * Indique si le graphe ne comporte pas une valuation négative
     * @param graphe le graphe
     * @return vrai si le graphe ne comporte pas de valuation négative
     */
    private static boolean estOk(IGraphe graphe) {
        for (int i = 1; i < graphe.getNbSommets(); ++i)
            for (int j = 1; j < graphe.getNbSommets(); ++j)
                if (graphe.getValuation(i, j) < 0)
                    return false;
        return true;
    }

    /**
     * @brief Indique la clé de la valeur minimum dans les valeurs de la hashmap
     * @param d la hashmap
     * @return la clé de la valeur minimum
     */
    private static int minimum(HashMap<Integer, Integer> d) {
        int mini = INFINI;
        int s = 1;
        for (int clef : d.keySet()) {
            if (d.get(clef) < mini) {
                s = clef;
                mini = d.get(clef);
            }
        }
        return s;
    }

    /**
     * @brief Indique la distance la plus courte entre le début et la fin dans le graphe et modifie le chemin pour indiquer le chemin emprunté
     * @param graphe le graphe
     * @param debut le début du chemin
     * @param fin la fin du chemin
     * @param chemin le chemin emprunté par l'algorithme
     * @return la distance la plus courte entre le début et la fin
     */
    @Override
    public int pc(IGraphe graphe, int debut, int fin, List<Integer> chemin) throws ArcNegatifEx, NoPathEx {
        if (debut == fin) {
            chemin.add(debut);
            return 0;
        } else if (PCCDijkstra.NoPath(graphe, debut, fin))
            throw new NoPathEx();
        else if (!PCCDijkstra.estOk(graphe))
            throw new ArcNegatifEx();
        else {
            HashMap<Integer, Integer> dijkstra = new HashMap<Integer, Integer>();  //sommet clé prede valeur
            HashMap<Integer, Integer> tab = new HashMap<Integer, Integer>();

            for (int i = 1; i <= graphe.getNbSommets(); ++i) {
                tab.put(i, INFINI);
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
                if (k == fin)
                    dijkstra.put(fin, k);

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

