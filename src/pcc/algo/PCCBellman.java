package pcc.algo;

import graphes.IGraphe;
import pcc.CircuitAbsorbantEx;
import pcc.IPCC;
import pcc.NoPathEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PCCBellman implements IPCC {

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
        for(int i = 1; i <= graphe.getNbSommets(); i++)
            if (NoPath(graphe,i,i))
                return false;
        return true;
    }

    @Override
    public int pc(IGraphe g, int debut, int fin, List<Integer> chemin) throws CircuitAbsorbantEx, NoPathEx {
        if (!estOk(g))
            throw new CircuitAbsorbantEx();
        else if (NoPath(g, debut, fin))
            throw new NoPathEx();
        else {
            HashMap<Integer,ArrayList<Integer>> rang = new HashMap<>();
            HashMap<Integer,Integer> degre = new HashMap<>();
            int k = 0;
            rang.put(0,new ArrayList<>());

            for (int i = 1; i <= g.getNbSommets(); i++) {
                degre.put(i,0);
            }

            for (int i = 1; i <= g.getNbSommets(); i++) {
                for (int j = 1; j <= g.getNbSommets(); j++) {
                    if (g.aArc(i,j)) {
                        degre.put(j,degre.get(j)+1);
                    }
                }
            }

            for (int i = 1; i <= g.getNbSommets(); i++) {
                if (degre.get(i) == 0) {
                    rang.get(0).add(i);
                }
            }

            while (!rang.get(k).isEmpty()) {
                rang.put(k + 1, new ArrayList<>());
                for (int i : rang.get(k)) {
                    for (int j = 1; j <= g.getNbSommets(); j++) {
                        if (g.aArc(i, j)) {
                            degre.put(j, degre.get(j) - 1);
                            if (degre.get(j) == 0) {
                                rang.get(k + 1).add(j);
                            }
                        }
                    }
                }
                k++;
            }
            System.out.println(rang);

            HashMap<Integer,Integer> tab = new HashMap<Integer,Integer>();

            for (int i = 1; i <= g.getNbSommets(); ++i) {
                tab.put(i, INFINI);
            }
            tab.put(debut, 0);

            k = debut;
            
            rang.remove(k);

            while (k != fin) {
                k= fin;
            }
            System.out.println(rang);
            return 0;



            /*HashMap<Integer,Integer> bellman = new HashMap<Integer, Integer>();  //sommet clé prede valeur
            HashMap<Integer,Integer> tab = new HashMap<Integer,Integer>();


            for (int i = 1; i <= g.getNbSommets(); ++i) {
                tab.put(i, INFINI);
            }

            tab.put(debut, 0);
            //for (int i : tab.keySet()) {
            for (int i = 2; i <= g.getNbSommets(); i++) {
                for (int j : tab.keySet()) {
                //for (int j = 1; j <= graphe.getNbSommets(); ++j) {
                    if (g.aArc(j,i)) {
                        if (tab.get(j) > tab.get(i) + g.getValuation(j,i))
                            bellman.put(j,i);
                        tab.put(i,Integer.min(g.getValuation(j,i) + tab.get(j), tab.get(i)));
                    }
                }
            }

            System.out.println(bellman);
            chemin.add(chemin.size(),fin);
            int cmpt = bellman.remove(fin);
            int cmp = 1;
            while (cmpt != debut) {
                chemin.add(chemin.size()-cmp, cmpt);
                cmpt = bellman.remove(cmpt);
                ++cmp;
                System.out.println(chemin);
            }
            chemin.add(0,debut);

            return tab.get(fin);*/
        }
    }
}
