package graphes.types;

import java.util.ArrayList;

/** Type de données représentant un graphe avec une matrice d'adjacence qui hérite d'un graphe */
public class GrapheMA extends Graphe {
    /** matrice représentant le graphe */
    private final int[][] ma;

    /**
     * @brief Constructeur d'un GrapheMA initialisant tous les arcs à distance infini
     * @param nbNoeuds le nombre de noeuds du graphe souhaité
     */
    public GrapheMA(int nbNoeuds) {
        ma = new int[nbNoeuds][nbNoeuds];
        for (int a = 0; a < nbNoeuds; ++a)
            for (int b = 0; b < nbNoeuds; ++b)
                ma[a][b] = INFINI;
    }

    /**
     * @brief Indique le nombre de sommets du graphe
     * @return retourne le nombre de sommets
     */
    @Override
    public int getNbSommets() {
        return ma.length;
    }

    /**
     * @brief Indique la valuation de l'arc entre les deux nœuds en paramètres
     * @param a la source de l'arc
     * @param b la destination de l'arc
     * @return la valuation de l'arc
     */
    @Override
    public int getValuation(int a, int b) {
        assert estArcOK(a, b);
        return ma[a - 1][b - 1];
    }

    /**
     * @brief Ajoute un arc au graphe
     * @param a la source de l'arc
     * @param v la valuation de l'arc
     * @param b la destination de l'arc
     */
    @Override
    public void ajouterArc(int a, int v, int b) {
        assert !aArc(a, b);
        ma[a - 1][b - 1] = v;
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int v;
        for (int i = 1; i <= getNbSommets(); ++i) {
            str.append(i).append(" =>");
            for (int j = 1; j <= getNbSommets(); ++j)
                if ((v = getValuation(i, j)) != INFINI)
                    str.append(" ").append(j).append("(").append(v).append(")");
            str.append("\n");
        }
        return str.toString();
    }

    /**
     * @brief Indique la distance du chemin rentré
     * @param chemin le chemin
     * @return la distance entre le premier nœud du chemin et le dernier
     */
    @Override
    public int distance(ArrayList<Integer> chemin) {
        int distance = 0;
        for (int i : chemin) {
            if (chemin.indexOf(i) + 1 != chemin.size())
                distance += this.getValuation(i, chemin.get(chemin.indexOf(i) + 1));
        }
        return distance;
    }
}
