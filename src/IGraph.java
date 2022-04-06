public interface IGraph {
    static final int inf = Integer.MAX_VALUE;

    void ajouterArc(int n1, int n2, int poids);

    int getNbNoeuds();

    boolean aArc(int n1, int n2);

    int dOut(int n);

    int dIn(int n);

    String toString();



}