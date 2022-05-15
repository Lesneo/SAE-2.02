package pcc.exceptions;

/** Type de données représentant l'exception d'un arc négatif dans un graphe */
public class ArcNegatifEx extends IllegalArgumentException {
    public ArcNegatifEx() {
        super("Le graphe comprend un arc négatif");
    }
}
