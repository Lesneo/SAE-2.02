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
        d.put(1, inf);
        int k = debut;
        while(k != fin) {
            k = minimum(d);
            System.out.println(k);
            for (int j = 1; j <= graphe.dOut(k); ++j) {
                for (int l = 1; l <= graphe.getNbNoeuds();++l){
                    if (graphe.aArc(k,l)) {
                        int c = graphe.getValeur(k,l);
                        if (!D.contains(l)){
                            System.out.println(c);
                            System.out.println(d.get(l));
                            d.put(l, Integer.min(d.get(l), d.get(k)+c));
                        }
                    }
                }
                System.out.println(d.toString());
            }
            d.remove(k);
            System.out.println(minimum(d));
            D.add(k);
            System.out.println(D);
            if (minimum(d) == fin) {
                D.add(fin);
                System.out.println(D);
                break;
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


        System.out.println(Dijkstra(g,1,2)); // 1, 3, 5, 2

    }
}
