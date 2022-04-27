package pcc;

import graphes.IGraphe;

import java.util.ArrayList;
import java.util.HashMap;

public class PCCBellman {
    private static final int inf = Integer.MAX_VALUE;

    public static boolean NoPath(IGraphe graphe, int start, int end) {
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

    public static boolean estOk(IGraphe graphe) {
        for(int i = 1; i <= graphe.getNbSommets(); i++)
            if (NoPath(graphe,i,i))
                return false;
        return true;
    }

    public static ArrayList<Integer> bellman(IGraphe graphe, int start, int end) throws CircuitAbsorbantEx, NoPathEx {
        if (!NoPath(graphe, start, end))
            throw new NoPathEx();
        else if (!estOk(graphe))
            throw new CircuitAbsorbantEx();
        else {
            HashMap<Integer,Integer> chemin = new HashMap<Integer, Integer>();  //sommet clé prede valeur
            HashMap<Integer,Integer> tab = new HashMap<Integer,Integer>();

            ArrayList<Integer> bellman = new ArrayList<Integer>();

            for (int i = 1; i <= graphe.getNbSommets(); ++i) {
                tab.put(i, inf);
            }
            tab.put(start, 0);

            for (int i = 2; i <= graphe.getNbSommets(); i++) {
                for (int j = 1; j <= graphe.getNbSommets(); ++j) {
                    if (graphe.aArc(j,i)) {
                        tab.put(i,Integer.min(graphe.getValuation(j,i) + tab.get(j), tab.get(i)));
                        chemin.put(i,j);
                    }

                }

            }
            int cmpt = chemin.remove(end);
            int cmp = 1;
            bellman.add(bellman.size(),end);
            while (cmpt != start) {
                bellman.add(bellman.size()-cmp, cmpt);
                cmpt = chemin.remove(cmpt);
                ++cmp;
                System.out.println(bellman);
            }
            bellman.add(0,start);

            return bellman;
        }
    }
}
