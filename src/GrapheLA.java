import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class GrapheLA implements IGraph{
    private ArrayList<HashMap<Integer,Integer>> matrice;

    /**
     * @brief génère un graphe orienté représenté par une liste d'adjacence
     * @param i : la taille de la matrice
     */
    public GrapheLA(int i) {
        matrice = new ArrayList<>();
        while (i>0){
            matrice.add(new HashMap<>());
            --i;
        }
    }


    /**
     * @brief Ajoute un arc au graphe
     * @param i : Prédécesseur de j
     * @param j : Successeur de i
     */
    public void ajouterArc(int i, int j, int valeur) {
        matrice.get(i-1).put(j-1, valeur);
        //sort la hashmap
    }

    /**
     * @brief Indique si l'arc existe dans le graphe
     * @param i : Prédécesseur de j
     * @param j : Successeur de i
     * @return valeur booléenne
     */
    public boolean aArc(int i, int j) {
        return matrice.get(i-1).containsKey(j-1);
    }

    /**
     * @brief Renvoie le degré sortant du sommet
     * @param i : le sommet
     * @return un int : le degré sortant
     */
    public int dOut(int i) {
        AtomicInteger cmpt = new AtomicInteger();
        matrice.get(i-1).forEach((k,l) -> cmpt.getAndIncrement());
        return cmpt.get();
    }

    /**
     * @brief Renvoie le degré entrant du sommet
     * @param i : le sommet
     * @return un int : le degré entrant
     */
    public int dIn(int i) {
        int cmpt = 0;
        for (HashMap<Integer, Integer> l : matrice) {
            if (l.containsKey(i-1))
                ++cmpt;
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
            System.out.println(matrice.get(i-1));
            System.out.println(matrice.get(i-1).size());
            for (int j : matrice.get(i-1).keySet()) {
                chaine += (j+1) + " ";
            }
            chaine += "\n";
        }
        return chaine;
    }

    public int getValeur(int i, int j){
        return matrice.get(i-1).get(j-1);
    }
}