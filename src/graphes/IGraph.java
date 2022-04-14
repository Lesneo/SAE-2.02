package graphes;

public interface IGraph {
    static final int inf = Integer.MAX_VALUE;


    void ajouterArc(Sommet n1, Sommet n2, int poids);

    int getNbNoeuds();

    boolean aArc(Sommet n1, Sommet n2);

    int dOut(Sommet n);

    int dIn(Sommet n);

    int getValeur(Sommet i, Sommet j);

    String toString();



}