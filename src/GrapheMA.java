public class GrapheMA implements IGraph{
    private int [][] matrice;

    /**
     * @brief génère un graphe orienté représenté par une matrice de booléens
     * @param i : la taille de la matrice
     */
    public GrapheMA(int i) {
        matrice = new int[i][i];
    }

    /**
     * @brief Ajoute un arc au graphe
     * @param i : Prédécesseur de j
     * @param j : Successeur de i
     */
    public void ajouterArc(int i, int j, int valeur) {
        matrice[i-1][j-1] = valeur;
    }

    /**
     * @brief Indique si l'arc existe dans le graphe
     * @param i : Prédécesseur de j
     * @param j : Successeur de i
     * @return valeur booléenne
     */
    public boolean aArc(int i, int j) {
        return (matrice[i-1][j-1] != inf);
    }

    /**
     * @brief Renvoie le degré sortant du sommet
     * @param i : le sommet
     * @return un int : le degré sortant
     */
    public int dOut(int i) {
        int cmpt = 0;
        for (int s: matrice[i-1]) {
            if(s != inf){
                cmpt += 1;
            }
        }
        return cmpt;
    }

    /**
     * @brief Renvoie le degré entrant du sommet
     * @param i : le sommet
     * @return un int : le degré entrant
     */
    public int dIn(int i) {
        int cmpt = 0;
        for (int[] s: matrice) {
            if(s[i-1] != inf)
                cmpt += 1;

        }
        return cmpt;
    }

    /**
     * @brief Renvoie le nombre de noeuds du graphe
     * @return un int : le nombre de noeuds
     */
    public int getNbNoeuds() {
        return matrice.length;
    }

    /**
     * @brief Renvoie une chaine de caractère représentant le graphe
     * @return un string : le graphe
     */
    public String toString() {
        String chaine = "";
        for (int[] tab: matrice) {
            for (int s: tab) {
                if (s != inf)
                    chaine += 1 + " ";
                else
                    chaine += 0 + " ";
            }
            chaine += "\n";
        }
        return chaine;
    }
}