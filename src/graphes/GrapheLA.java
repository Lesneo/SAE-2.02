package graphes;

import pcc.IGraph;

import java.util.ArrayList;
import java.util.HashMap;

public class GrapheLA implements IGraph {
    private ArrayList<HashMap<Integer,Integer>> matrice;

    /**
     * @brief génère un graphe orienté représenté par une liste d'adjacence
     * @param i : la taille de la matrice
     */
    public GrapheLA(Sommet[] sommets) {
        matrice = new ArrayList<>();
        int i = sommets.length;
        while (i >0){
            matrice.add(new HashMap<>());
            --i;
        }
    }


    /**
     * @brief Ajoute un arc au graphe
     * @param i : Prédécesseur de j
     * @param j : Successeur de i
     * @param valeur : valuation de l'arc
     */
    public void ajouterArc(Sommet i, Sommet j, int valeur) {
        assert(0 <= i.getValeur() && matrice.size() > i.getValeur()-1 && 0 <= j.getValeur() && matrice.size() > j.getValeur()-1);
        matrice.get(i.getValeur()-1).put(j.getValeur()-1, valeur);
    }

    /**
     * @brief Indique si l'arc existe dans le graphe
     * @param i : Prédécesseur de j
     * @param j : Successeur de i
     * @return valeur booléenne
     */
    public boolean aArc(Sommet i, Sommet j) {
        assert(0 <= i.getValeur()-1 && matrice.size() > i.getValeur()-1 && 0 <= j.getValeur()-1 && matrice.size() > j.getValeur()-1);
        return matrice.get(i.getValeur()-1).containsKey(j.getValeur()-1);
    }

    /**
     * @brief Renvoie le degré sortant du sommet
     * @param i : le sommet
     * @return un int : le degré sortant
     */
    public int dOut(Sommet i) {
        assert(0 <= i.getValeur()-1 && matrice.size() > i.getValeur()-1);
        return matrice.get(i.getValeur()-1).size();
        //AtomicInteger cmpt = new AtomicInteger();
        //matrice.get(i.getValeur()-1).forEach((k, l) -> cmpt.getAndIncrement());
        //return cmpt.get();
    }

    /**
     * @brief Renvoie le degré entrant du sommet
     * @param i : le sommet
     * @return un int : le degré entrant
     */
    public int dIn(Sommet i) {
        assert(0 <= i.getValeur()-1 && matrice.size() > i.getValeur()-1);
        int cmpt = 0;
        for (HashMap<Integer, Integer> l : matrice) {
            if (l.containsKey(i.getValeur()-1))
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
        StringBuilder chaine = new StringBuilder();
        for (int i = 1; i <= matrice.size(); ++i) {
            chaine.append(Sommet.getSommet(i) + " -> ");
            for (int j : matrice.get(i-1).keySet()) {
                chaine.append(Sommet.getSommet(j+1) + " ");
            }
            chaine.append("\n");
        }
        return chaine.toString();
    }

    public int getValeur(Sommet i, Sommet j) {
        assert(0 <= i.getValeur()-1 && matrice.size() > i.getValeur()-1 && 0 <= j.getValeur()-1 && matrice.size() > j.getValeur()-1);
        if (matrice.get(i.getValeur() - 1).containsKey(j.getValeur()-1))
            return matrice.get(i.getValeur()-1).get(j.getValeur()-1);
        return 0;
    }
}