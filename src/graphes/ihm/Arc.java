package graphes.ihm;

/** Type de données représentant un arc */
public class Arc {
    private int source, valuation, destination;

    /**
     * @brief Initialise un Arc avec 0 en valeur, en source et en destination
     */
    public Arc() {
        this(0, 0, 0);
    }

    /**
     * @brief Initialise un Arc avec les valeurs en paramètres
     * @param source la source de l'arc
     * @param valeur la valeur de l'arc
     * @param cible la destination de l'arc
     */
    public Arc(int source, int valeur, int cible) {
        this.source = source;
        this.valuation = valeur;
        this.destination = cible;
    }

    /**
     * @brief Indique la source de l'arc
     * @return la source de l'arc
     */
    public int getSource() {
        return source;
    }

    /**
     * @brief Indique la valuation de l'arc
     * @return la valuation de l'arc
     */
    public int getValuation() {
        return valuation;
    }

    /**
     * Indique la destination de l'arc
     * @return la destination de l'arc
     */
    public int getDestination() {
        return destination;
    }

    /**
     * @brief
     * @return
     */
    @Override
    public String toString() {
        return source + " ==" + valuation + "==> " + destination;
    }

    /**
     * @brief Duplique l'arc mis en paramètre
     * @param a l'arc à cloner
     */
    public void set(Arc a) {
        this.source = a.source;
        this.valuation = a.valuation;
        this.destination = a.destination;
    }
}
