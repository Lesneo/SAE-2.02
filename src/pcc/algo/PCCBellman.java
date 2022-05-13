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
            if (rang.get(k).isEmpty()) {
                rang.remove(k);
            }
            System.out.println(rang);

            HashMap<Integer,Integer> tab = new HashMap<Integer,Integer>();
            HashMap<Integer,Integer> bellman = new HashMap<Integer, Integer>();  //sommet clé prede valeur

            /*for (int i = 1; i <= g.getNbSommets(); ++i) {
                for (int j : rang.keySet()) {
                    if (!tab.containsKey(i)) {
                        tab.put(i, INFINI);
                    }
                    if ((rang.get(j).contains(i) && j == 0)) {
                        tab.put(i, 0);
                    }
                }
            }*/
            for (int i = 1; i <= g.getNbSommets(); ++i) {
                tab.put(i, INFINI);
            }
            tab.put(debut,0);


            /*for (int i : rang.keySet()) {
                if (!rang.get(i).contains(debut)){
                    rang.remove(i);
                }
                else {
                    for (int j : rang.get(i)) {
                        if (j != debut) {
                            rang.get(i).remove(j);
                        }
                    }
                    break;
                }
            }*/
            System.out.println("init tab :" + tab);

            for (int i : rang.keySet()) {
                if (i != 0) {
                    for (int j : rang.get(i)) {
                        for (int rangPrede = i-1; rangPrede >= 0; rangPrede--) {
                            //System.out.println(rangPrede);
                            for (int prede : rang.get(rangPrede)) {
                                //System.out.println(j);
                                if (g.aArc(prede, j) && tab.get(prede) != INFINI) {
                                    //System.out.println("salut");
                                    tab.put(j, Integer.min(tab.get(j), g.getValuation(prede, j) + tab.get(prede)));
                                    bellman.put(j, prede);
                                }
                            }
                        }
                    }
                }
                if (bellman.containsKey(fin))
                    break;
            }
            System.out.println("final tab" + tab);
            System.out.println(bellman);

            k = fin;
            int cmpt = 0;

            while (k != debut) {
                chemin.add(chemin.size()-cmpt, bellman.get(k));
                k = bellman.remove(k);
                cmpt++;
            }
            chemin.add(fin);


            /*int valuation = INFINI;
            int rangSommet = 0;


            chemin.add(debut);
            while (k != fin) {
                //System.out.println(k);
                for (int i : rang.keySet()) {
                    if (rang.get(i).contains(k)) {
                        rangSommet = i;
                    }
                }


                for (int i = rangSommet + 1; i < rang.size(); ++i) {
                    for (int j : rang.get(i)) {
                        if (g.aArc(k, j)) {
                            tab.put(j, Integer.min(tab.get(j), g.getValuation(k, j) + tab.get(k)));
                            bellman.put(j, k);
                        }
                    }
                }


                k = chemin.get(chemin.size()-1);
                for (int i : bellman.keySet()) {
                    if (bellman.get(i) == k) {
                        //System.out.println("sommet " + i + " valuation " + tab.get(i));
                        if (tab.get(i) < valuation) {
                            valuation = tab.get(i);

                        }
                        else
                            tab.remove(i);
                    }
                }
                for (int i : tab.keySet()) {
                    if (tab.get(i) == valuation)
                        k = i;
                }

                chemin.add(k);
                valuation = INFINI;
                System.out.println(bellman);
                //System.out.println(tab);
                //System.out.println(chemin);
            }



            System.out.println(chemin);*/
            return tab.get(fin);




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
