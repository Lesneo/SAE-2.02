package pcc.exceptions;

/** Type de données représentant l'exception d'un circuit dans un graphe */
public class CircuitAbsorbantEx extends IllegalArgumentException {
    public CircuitAbsorbantEx() {
        super("Ce graphe comprend un circuit");
    }
}
