package pcc;

public class ArcNegatifEx extends IllegalArgumentException {
    public ArcNegatifEx() {
        super("Le graphe comprend un arc négatif");
    }
}
