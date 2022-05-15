package pcc;

import graphes.IGraphe;

import java.util.List;

/** Interface représentant les algorithmes de plus court chemin*/
public interface IPCC {
    /** valeur représentant l'infini */
    int INFINI = Integer.MAX_VALUE;

    /**
     * @brief Indique la distance la plus courte entre le début et la fin dans le graphe et modifie le chemin pour indiquer le chemin emprunté
     * @param g le graphe
     * @param debut le début du chemin
     * @param fin la fin du chemin
     * @param chemin le chemin emprunté par l'algorithme
     * @return la distance la plus courte entre le début et la fin
     */
    int pc(IGraphe g, int debut, int fin, List<Integer> chemin);
}
