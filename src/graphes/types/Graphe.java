package graphes.types;

import graphes.IGraphe;

/** Type de données abstraites représentant un graphe */
public abstract class Graphe implements IGraphe {
    /** valeur représentant l'infini */
    protected final static int INFINI = Integer.MAX_VALUE;

    /**
     * @brief Indique si le nœud existe dans le graphe
     * @param n le nœud
     * @return vrai si le nœud existe
     */
    public boolean estNoeudOK(int n) {
        return n >= 1 && n <= getNbSommets();
    }

    /**
     * @brief Indique si un Arc peut exister entre les deux nœuds mis en paramètre
     * @param a le nœud source
     * @param b le nœud destination
     * @return vrai si le nœud peut exister
     */
    public boolean estArcOK(int a, int b) {
        return estNoeudOK(a) && estNoeudOK(b);
    }

    /**
     * @brief Indique si un Arc existe dans le graphe
     * @param a le nœud source
     * @param b le nœud destination
     * @return vrai si le nœud existe
     */
    @Override
    public boolean aArc(int a, int b) {
        return getValuation(a, b) != INFINI;
    }

}