import java.util.ArrayList;
import java.util.Collections;

public class GrapheLA{
    private ArrayList <ArrayList<Integer>> matrice;

    /**
     * @brief génère un graphe orienté représenté par une liste d'adjacence
     * @param i : la taille de la matrice
     */
    public GrapheLA(int i) {
        matrice = new ArrayList<>();
        while (i>0){
            matrice.add(new ArrayList<>());
            --i;
        }
    }


    /**
     * @brief Ajoute un arc au graphe
     * @param i : Prédécesseur de j
     * @param j : Successeur de i
     */
    public void ajouterArc(int i, int j) {
        matrice.get(i-1).add(j);
        Collections.sort(matrice.get(i-1));
    }

    /**
     * @brief Indique si l'arc existe dans le graphe
     * @param i : Prédécesseur de j
     * @param j : Successeur de i
     * @return valeur booléenne
     */
    public boolean aArc(int i, int j) {
        for (int k : matrice.get(i-1)){
            if (k == j) return true;
        }
        return false;
    }

    /**
     * @brief Renvoie le degré sortant du sommet
     * @param i : le sommet
     * @return un int : le degré sortant
     */
    public int dOut(int i) {
        int cmpt = 0;
        for (int k : matrice.get(i-1)){
            ++cmpt;
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
        Integer j = i;
        for (ArrayList l : matrice) {
            for (Object k: l) {
                if(k == j)
                    ++cmpt;
            }
        }
        return cmpt;
    }

    /**
     * @brief Renvoie le nombre de noeuds du graphe
     * @return un int : le nombre de noeuds
     */
    public int getNbNoeuds() {
        return matrice.size();
    }

    /**
     * @brief Renvoie une chaine de caractère représentant le graphe
     * @return un string : le graphe
     */
    public String toString() {
        String chaine = "";
        for (int i = 1; i <= matrice.size(); ++i) {
            chaine += i + " -> ";
            for (int j : matrice.get(i-1)) {
                 chaine += j + " ";
            }
            chaine += "\n";
        }
        return chaine;
    }
}