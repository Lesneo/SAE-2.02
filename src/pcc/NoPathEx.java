package pcc;

public class NoPathEx extends IllegalArgumentException {
    public NoPathEx() {
        super("Ce graphe ne comprend pas ce chemin");
    }
}
