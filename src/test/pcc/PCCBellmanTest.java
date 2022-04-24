package test.pcc;

import graphes.GrapheMA;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import pcc.ArcNegatifEx;
import pcc.CircuitAbsorbantEx;
import pcc.IGraph;
import graphes.Sommet;
import org.junit.Test;
import pcc.PCCBellman;

import static org.junit.Assert.assertTrue;

public class PCCBellmanTest {
    private final static int NB_NOEUDS = 6;

    /**
     * Test global des différentes méthodes liées à
     * la manipulation d'un graphe sous forme de matrice
     * d'adjacence
     */
    @Test
    public void test() {
        Sommet[] sommets1 = {Sommet.A, Sommet.B, Sommet.C, Sommet.D, Sommet.E};
        IGraph g = new graphes.GrapheMA(sommets1);
        g.ajouterArc(Sommet.A,Sommet.C,7);
        g.ajouterArc(Sommet.A,Sommet.D,15);
        g.ajouterArc(Sommet.B,Sommet.D,21);
        g.ajouterArc(Sommet.C,Sommet.B,13);
        g.ajouterArc(Sommet.C,Sommet.E,3);
        g.ajouterArc(Sommet.E,Sommet.A,1);
        g.ajouterArc(Sommet.E,Sommet.B,9);
        g.ajouterArc(Sommet.E,Sommet.D,17);


        assertTrue(PCCBellman.bellman(g,Sommet.A,Sommet.B).toString().equals("[A, C, E, B]")); // 1, 3, 5, 2



        IGraph g2 = new graphes.GrapheLA(sommets1);
        g2.ajouterArc(Sommet.A,Sommet.C,7);
        g2.ajouterArc(Sommet.A,Sommet.D,15);
        g2.ajouterArc(Sommet.B,Sommet.D,21);
        g2.ajouterArc(Sommet.C,Sommet.B,13);
        g2.ajouterArc(Sommet.C,Sommet.E,3);
        g2.ajouterArc(Sommet.E,Sommet.A,1);
        g2.ajouterArc(Sommet.E,Sommet.B,9);
        g2.ajouterArc(Sommet.E,Sommet.D,17);

        assertTrue(PCCBellman.bellman(g2,Sommet.A,Sommet.B).toString().equals("[A, C, E, B]")); // 1, 3, 5, 2
    }

    @Rule
    public ExpectedException thrownException = ExpectedException.none();

    @Test
    public void arcNegatifTest() throws ArcNegatifEx {
        Sommet[] sommets1 = {Sommet.A, Sommet.B, Sommet.C, Sommet.D, Sommet.E};
        IGraph g = new graphes.GrapheMA(sommets1);
        g.ajouterArc(Sommet.A,Sommet.C,7);
        g.ajouterArc(Sommet.A,Sommet.D,15);
        g.ajouterArc(Sommet.B,Sommet.D,21);
        g.ajouterArc(Sommet.C,Sommet.B,13);
        g.ajouterArc(Sommet.C,Sommet.A,3);
        g.ajouterArc(Sommet.E,Sommet.A,1);
        g.ajouterArc(Sommet.E,Sommet.B,9);
        g.ajouterArc(Sommet.E,Sommet.D,17);
        g.ajouterArc(Sommet.A,Sommet.B, -5);

        thrownException.expect(CircuitAbsorbantEx.class);
        thrownException.expectMessage("Ce graphe comprend un circuit"); //T
        PCCBellman.bellman(g,Sommet.A,Sommet.B);

    }
}
