package pcc.exceptions;

/** Type de données représentant l'exception de l'absence du chemin donné dans le grpahe */
public class NoPathEx extends IllegalArgumentException {
    public NoPathEx() {
        super("Ce graphe ne comprend pas ce chemin");
    }
}
