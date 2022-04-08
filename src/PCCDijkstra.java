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
                    if (!D.contains(l) && graphe.dOut(l) > 0) {
                        System.out.println("valuation entre " + k + " et " + l + " est de " + c);
                        System.out.println("la distance avec " + l + " est de " + d.get(l));
                        d.put(l, Integer.min(d.get(l), d.get(k)+c));
                    }
                }
            }
            System.out.println(d.toString());

            d.remove(k);
            if (graphe.dOut(k) > 0) {
                D.add(k);
                System.out.println(D);
            }


        }
        return D;
    }

    public static void main(String[] args) {
        IGraph g = new GrapheMA(5);
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



    }
}
