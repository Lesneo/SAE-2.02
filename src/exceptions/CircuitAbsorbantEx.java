package exceptions;

public class CircuitAbsorbantEx extends IllegalArgumentException{
    public CircuitAbsorbantEx() {
        super("Ce graphe comprend un circuit");
    }
}
