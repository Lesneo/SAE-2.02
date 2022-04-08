import java.util.ArrayList;
import java.util.HashMap;

public class PCCDijkstra {
    private static final int inf = Integer.MAX_VALUE;

    public boolean estOk (IGraph graphe) {
        int cmpt = 0;
        for (int i = 1; i < graphe.getNbNoeuds(); ++i) {
            for (int j = 1; j < graphe.getNbNoeuds(); ++j) {
                if (graphe.getValeur(i,j) < 0)
                    return false;
            }
        }
        return true;
    }

    private static int minimum(HashMap<Integer,Integer> d) {
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


    public static ArrayList<Integer> Dijkstra (IGraph graphe, int debut, int fin) {
        ArrayList<Integer> D = new ArrayList<Integer>();
        HashMap<Integer,Integer> d = new HashMap<Integer,Integer>();
        for (int i = 1; i <= graphe.getNbNoeuds();++i){
            d.put(i, inf);
        }
        d.put(debut, 0);
        int k = minimum(d); // début 1
        while(k != fin) {
            k = minimum(d);
            System.out.println("sommet k : " + k);
            for (int l = 1; l <= graphe.getNbNoeuds();++l){
                if (graphe.aArc(k,l)) {
                    int c = graphe.getValeur(k,l);
                    if (!D.contains(l)) {
                        System.out.println("valuation entre " + k + " et " + l + " est de " + c);
                        System.out.println("la distance avec " + l + " est de " + d.get(l));
                        d.put(l, Integer.min(d.get(l), d.get(k)+c));
                    }
                }
            }
            System.out.println(d.toString());
            d.remove(k);
            D.add(k);
            System.out.println(D);
        }


        ArrayList<Integer> temp = D;
        temp.remove(temp.size()-2);
        int valeur = 0;
        int valeurTemp = 0;
        boolean estPlusCourt = false;

        //trie du Dijkstra
        while (estPlusCourt == true) {
            for (int sommet = D.size()-1; sommet > 0; --sommet) {
                System.out.println("on vérifie entre " + D.get(sommet) + " et le sommet " +D.get(sommet-1));
                if (!graphe.aArc(D.get(sommet-1),D.get(sommet))) {
                    System.out.println("l'arc n'existe pas");
                    D.remove(sommet-1);
                }
                valeur += graphe.getValeur(D.get(sommet-1),D.get(sommet));
            }
            for (int sommet2 = temp.size()-1; sommet2 > 0; --sommet2) {
                System.out.println("on vérifie entre " + temp.get(sommet2) + " et le sommet " +D.get(sommet-1));
                if (!graphe.aArc(temp.get(sommet2-1),temp.get(sommet2))) {
                    System.out.println("l'arc n'existe pas");
                    temp.remove(sommet2-1);
                }
                valeurTemp += graphe.getValeur(D.get(sommet2-1),D.get(sommet2));
            }
            if (D.get(0) == debut && D.get(D.size()-1) == fin && valeur > valeurTemp) {
                temp = D;

            }
        }


        return D;
    }

    public static void main(String[] args) {
        /*IGraph g = new GrapheMA(5);
        g.ajouterArc(1,3,7);
        g.ajouterArc(1,4,15);
        g.ajouterArc(2,4,21);
        g.ajouterArc(3,2,13);
        g.ajouterArc(3,5,3);
        g.ajouterArc(5,1,1);
        g.ajouterArc(5,2,9);
        g.ajouterArc(5,4,17);

        System.out.println(g.dOut(4));
        System.out.println(Dijkstra(g,1,2)); // 1, 3, 5, 2

        IGraph g2 = new GrapheLA(5);
        g2.ajouterArc(1,3,7);
        g2.ajouterArc(1,4,15);
        g2.ajouterArc(2,4,21);
        g2.ajouterArc(3,2,13);
        g2.ajouterArc(3,5,3);
        g2.ajouterArc(5,1,1);
        g2.ajouterArc(5,2,9);
        g2.ajouterArc(5,4,17);

        System.out.println(Dijkstra(g2,1,2)); // 1, 3, 5, 2*/

        IGraph g3 = new GrapheMA(9);
        g3.ajouterArc(1,4,1);
        g3.ajouterArc(1,3,2);
        g3.ajouterArc(2,7,3);
        g3.ajouterArc(3,8,2);
        g3.ajouterArc(4,2,3);
        g3.ajouterArc(4,5,3);
        g3.ajouterArc(5,3,1);
        g3.ajouterArc(5,7,3);
        g3.ajouterArc(5,8,7);
        g3.ajouterArc(7,2,2);
        g3.ajouterArc(7,6,1);
        g3.ajouterArc(8,6,4);
        g3.ajouterArc(8,7,2);
        g3.ajouterArc(9,8,10);

        System.out.println(Dijkstra(g3,1, 6)); //1, 3, 8, 7, 6
    }
