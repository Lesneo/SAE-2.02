package graphes.types;

import java.util.ArrayList;
import java.util.List;

/** Type de données représentant un graphe avec une liste d'adjacence qui hérite d'un graphe */
public class GrapheLA extends Graphe {

    private static class Stub {
        public final int valuation, cible;

        public Stub(int valuation, int cible) {
            this.valuation = valuation;
            this.cible = cible;
        }
    }

    private final List<Stub>[] la;

    /**
     * @brief Constructeur d'un GrapheLA initialisant tous les arcs à null
     * @param nbNoeuds le nombre de noeuds du graphe souhaité
     */
    @SuppressWarnings("unchecked")
    public GrapheLA(int nbNoeuds) {
        super();
        la = new List[nbNoeuds]; // necessite le SuppressWarnings ci-dessus
        for (int i = 0; i < nbNoeuds; ++i)
            la[i] = new ArrayList<>();
    }

    /**
     * @brief Indique le nombre de sommets du graphe
     * @return retourne le nombre de sommets
     */
    @Override
    public int getNbSommets() {
        return la.length;
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
        List<Stub> stubs = la[a - 1];
        for (Stub s : stubs)
            if (s.cible == b)
                return s.valuation;
        return INFINI;
    }

    /**
     * @brief Ajoute un arc au graphe
     * @param a la source de l'arc
     * @param v la valuation de l'arc
     * @param b la destination de l'arc
     */
    public void ajouterArc(int a, int v, int b) {
        assert !aArc(a, b);
        la[a - 1].add(new Stub(v, b));
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < la.length; ++i) {
            str.append(i + 1).append(" =>");
            for (Stub s : la[i])
                str.append(" ").append(s.cible).append("(").append(s.valuation).append(")");
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
            if (chemin.indexOf(i) + 1 < chemin.size())
                distance += this.getValuation(i, chemin.get(chemin.indexOf(i) + 1));
        }
        return distance;
    }
}
