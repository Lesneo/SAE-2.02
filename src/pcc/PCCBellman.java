package pcc;

import graphes.IGraphe;
import pcc.exceptions.CircuitAbsorbantEx;
import pcc.exceptions.NoPathEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Type de données représentant l'algorithme de Bellman héritant de l'interface IPCC */
public class PCCBellman implements IPCC {

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
     * Indique si le graphe ne comporte pas un cycle
     * @param graphe le graphe
     * @return vrai si le graphe ne comporte pas de cycle
     */
    private static boolean estOk(IGraphe graphe) {
        for (int i = 1; i <= graphe.getNbSommets(); i++)
            if (NoPath(graphe, i, i))
                return false;
        return true;
    }

    /**
     * @brief Indique la hashmap des nœuds par rang du graphe
     * @param g le graphe
     * @return la hashmap des noeuds triés par rang
     */
    private static HashMap<Integer, ArrayList<Integer>> trieTopologique(IGraphe g) {
        HashMap<Integer, ArrayList<Integer>> rang = new HashMap<>();
        HashMap<Integer, Integer> degre = new HashMap<>();
        int k = 0;
        rang.put(0, new ArrayList<>());
        for (int i = 1; i <= g.getNbSommets(); i++)
            degre.put(i, 0);

        for (int i = 1; i <= g.getNbSommets(); i++)
            for (int j = 1; j <= g.getNbSommets(); j++)
                if (g.aArc(i, j))
                    degre.put(j, degre.get(j) + 1);



        for (int i = 1; i <= g.getNbSommets(); i++)
            if (degre.get(i) == 0)
                rang.get(0).add(i);


        while (!rang.get(k).isEmpty()) {
            rang.put(k + 1, new ArrayList<>());
            for (int i : rang.get(k)) {
                for (int j = 1; j <= g.getNbSommets(); j++) {
                    if (g.aArc(i, j)) {
                        degre.put(j, degre.get(j) - 1);
                        if (degre.get(j) == 0)
                            rang.get(k + 1).add(j);
                    }
                }
            }
            k++;
        }
        if (rang.get(k).isEmpty())
            rang.remove(k);
        return rang;
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
    public int pc(IGraphe graphe, int debut, int fin, List<Integer> chemin) throws CircuitAbsorbantEx, NoPathEx {
        if (debut == fin) {
            chemin.add(debut);
            return 0;
        }
        if (NoPath(graphe, debut, fin))
            throw new NoPathEx();
        else if (!estOk(graphe))
            throw new CircuitAbsorbantEx();
        else {
            HashMap<Integer, ArrayList<Integer>> rang = trieTopologique(graphe);
            //System.out.println(rang);

            HashMap<Integer, Integer> tab = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> bellman = new HashMap<Integer, Integer>();  //sommet clé prede valeur


            for (int i = 1; i <= graphe.getNbSommets(); ++i)
                tab.put(i, INFINI);

            tab.put(debut, 0);

            for (int i : rang.keySet()) {
                if (i != 0) {
                    for (int j : rang.get(i)) {
                        for (int rangPrede = i - 1; rangPrede >= 0; rangPrede--) {
                            //System.out.println(rangPrede);
                            for (int prede : rang.get(rangPrede)) {
                                //System.out.println(j);
                                if (graphe.aArc(prede, j) && tab.get(prede) != INFINI) {
                                    //System.out.println("j " + j + " val " + tab.get(j) + ", prede "+ prede + " val prede j " + g.getValuation(prede, j) + " prede " + tab.get(prede));
                                    //System.out.println(Integer.min(tab.get(j), g.getValuation(prede, j) + tab.get(prede)));
                                    if (tab.get(j) > graphe.getValuation(prede, j) + tab.get(prede))
                                        bellman.put(j, prede);
                                    tab.put(j, Integer.min(tab.get(j), graphe.getValuation(prede, j) + tab.get(prede)));
                                }
                            }
                        }
                    }
                }
                if (bellman.containsKey(fin))
                    break;
            }
            //System.out.println("final tab" + tab);
            //System.out.println(bellman);

            int k = fin;
            int cmpt = 0;

            while (k != debut) {
                chemin.add(chemin.size() - cmpt, bellman.get(k));
                k = bellman.remove(k);
                cmpt++;
            }
            chemin.add(fin);
            return tab.get(fin);
        }
    }
}
