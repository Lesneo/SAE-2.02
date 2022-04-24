package pcc;

import graphes.Sommet;

import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.HashMap;

public class PCCBellman {
    private static final int inf = Integer.MAX_VALUE;

    public static boolean NoPath(IGraph graphe, Sommet start, Sommet end) {
        if (graphe.dOut(start) == 0 || graphe.dIn(end) == 0)
            return false;
        int longueur = graphe.getNbNoeuds();
        ArrayList<Sommet> sommets = new ArrayList<>();
        ArrayList<Boolean> visitee = new ArrayList<>();
        for (int i = 0; i<longueur; ++i) {
            visitee.add(false);
        }
        sommets.add(start);
        while (!sommets.isEmpty()) {
            Sommet courant = sommets.remove(sommets.size()-1);
            visitee.set(courant.getValeur()-1, true);
            for (int i = 1; i<=longueur; ++i) {
                if (graphe.aArc(courant,Sommet.getSommet(i)) && visitee.get(i-1) == false) {
                    sommets.add(Sommet.getSommet(i));
                    visitee.set(i-1, true);
                }
                else if (graphe.aArc(courant, Sommet.getSommet(i)) && Sommet.getSommet(i) == end) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean estOk(IGraph graphe) {
        for(int i = 1; i <= graphe.getNbNoeuds(); i++)
            if (NoPath(graphe,Sommet.getSommet(i),Sommet.getSommet(i)))
                return false;
        return true;
    }

    public static ArrayList<Sommet> bellman(IGraph graphe, Sommet start, Sommet end) throws CircuitAbsorbantEx, NoPathEx {
        if (!NoPath(graphe, start, end))
            throw new NoPathEx();
        else if (!estOk(graphe))
            throw new CircuitAbsorbantEx();
        else {
            int debut = start.getValeur(), fin = end.getValeur();
            HashMap<Integer,Integer> chemin = new HashMap<Integer, Integer>();  //sommet clé prede valeur
            HashMap<Integer,Integer> tab = new HashMap<Integer,Integer>();

            ArrayList<Sommet> bellman = new ArrayList<Sommet>();

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
            bellman.add(bellman.size(),end);
            while (cmpt != debut) {
                bellman.add(bellman.size()-cmp, Sommet.getSommet(cmpt));
                cmpt = chemin.remove(cmpt);
                ++cmp;
                System.out.println(bellman);
            }
            bellman.add(0,start);

            return bellman;
        }
    }
}
