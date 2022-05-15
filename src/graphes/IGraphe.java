package graphes;

import java.util.ArrayList;

/** Interface représentant un graphe */
public interface IGraphe {
    /**
     * @brief Indique le nombre de sommets du graphe
     * @return retourne le nombre de sommets
     */
    int getNbSommets();

    /**
     * @brief Ajoute un arc au graphe
     * @param a la source de l'arc
     * @param v la valuation de l'arc
     * @param b la destination de l'arc
     */
    void ajouterArc(int a, int v, int b);

    /**
     * @brief Indique la valuation de l'arc entre les deux nœuds en paramètres
     * @param a la source de l'arc
     * @param b la destination de l'arc
     * @return la valuation de l'arc
     */
    int getValuation(int a, int b);

    /**
     * @brief Indique si un Arc existe dans le graphe
     * @param a le nœud source
     * @param b le nœud destination
     * @return vrai si le nœud existe
     */
    boolean aArc(int a, int b);

    /**
     * @brief Indique la distance du chemin rentré
     * @param chemin le chemin
     * @return la distance entre le premier nœud du chemin et le dernier
     */
    int distance(ArrayList<Integer> chemin);
}
