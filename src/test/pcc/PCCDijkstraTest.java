package test.pcc;

import static java.lang.invoke.MethodHandles.catchException;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import pcc.ArcNegatifEx;
import pcc.NoPathEx;
import pcc.PCCDijkstra;
import graphes.GrapheMA;
import pcc.IGraph;
import graphes.Sommet;
import org.junit.Test;

import java.util.ArrayList;


public class PCCDijkstraTest {
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

        assertTrue(PCCDijkstra.dijkstra(g,Sommet.A,Sommet.B).toString().equals("[A, C, E, B]")); // 1, 3, 5, 2



        IGraph g2 = new graphes.GrapheLA(sommets1);
        g2.ajouterArc(Sommet.A,Sommet.C,7);
        g2.ajouterArc(Sommet.A,Sommet.D,15);
        g2.ajouterArc(Sommet.B,Sommet.D,21);
        g2.ajouterArc(Sommet.C,Sommet.B,13);
        g2.ajouterArc(Sommet.C,Sommet.E,3);
        g2.ajouterArc(Sommet.E,Sommet.A,1);
        g2.ajouterArc(Sommet.E,Sommet.B,9);
        g2.ajouterArc(Sommet.E,Sommet.D,17);

        assertTrue(PCCDijkstra.dijkstra(g2,Sommet.A,Sommet.B).toString().equals("[A, C, E, B]")); // 1, 3, 5, 2

        Sommet[] sommets = {Sommet.A, Sommet.B, Sommet.C, Sommet.D, Sommet.E, Sommet.F, Sommet.G, Sommet.H, Sommet.I};
        IGraph g3 = new GrapheMA(sommets);
        g3.ajouterArc(Sommet.A, Sommet.D, 1);
        g3.ajouterArc(Sommet.A, Sommet.C, 2);
        g3.ajouterArc(Sommet.B, Sommet.G, 3);
        g3.ajouterArc(Sommet.C, Sommet.H, 2);
        g3.ajouterArc(Sommet.D, Sommet.B, 3);
        g3.ajouterArc(Sommet.D, Sommet.E, 3);
        g3.ajouterArc(Sommet.E, Sommet.C, 1);
        g3.ajouterArc(Sommet.E, Sommet.G, 3);
        g3.ajouterArc(Sommet.E, Sommet.H, 7);
        g3.ajouterArc(Sommet.G, Sommet.B, 2);
        g3.ajouterArc(Sommet.G, Sommet.F, 1);
        g3.ajouterArc(Sommet.H, Sommet.F, 4);
        g3.ajouterArc(Sommet.H, Sommet.G, 2);
        g3.ajouterArc(Sommet.I, Sommet.H, 10);

        assertTrue(PCCDijkstra.dijkstra(g3, Sommet.A, Sommet.F).toString().equals("[A, C, H, G, F]")); // 1, 3, 8, 7, 6

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
        g.ajouterArc(Sommet.C,Sommet.E,3);
        g.ajouterArc(Sommet.E,Sommet.A,1);
        g.ajouterArc(Sommet.E,Sommet.B,9);
        g.ajouterArc(Sommet.E,Sommet.D,17);
        g.ajouterArc(Sommet.A,Sommet.B, -5);

        thrownException.expect(IllegalArgumentException.class);
        thrownException.expectMessage("Le graphe comprend un arc négatif"); //Tests that the message contains -400.
        PCCDijkstra.dijkstra(g,Sommet.A,Sommet.B);

    }

    @Test
    public void pasDeCheminTest() throws NoPathEx {
        Sommet[] sommets1 = {Sommet.A, Sommet.B, Sommet.C, Sommet.D, Sommet.E};
        IGraph g = new graphes.GrapheMA(sommets1);

        thrownException.expect(IllegalArgumentException.class);
        thrownException.expectMessage("Ce graphe ne comprend pas ce chemin"); //Tests that the message contains -400.
        PCCDijkstra.dijkstra(g,Sommet.A,Sommet.B);
    }
}