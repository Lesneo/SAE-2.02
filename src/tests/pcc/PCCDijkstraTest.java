package tests.pcc;

import static org.junit.Assert.*;

import graphes.IGraphe;
import graphes.types.GrapheMA;
import pcc.IPCC;
import pcc.algo.PCCDijkstra;
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
        IGraphe g = new GrapheMA(9);
        g.ajouterArc(1,2,3);
        g.ajouterArc(1,1,4);
        g.ajouterArc(2,3,7);
        g.ajouterArc(3,2,8);
        g.ajouterArc(4,3,2);
        g.ajouterArc(4,5,3);
        g.ajouterArc(4,3,5);
        g.ajouterArc(5,1,3);
        g.ajouterArc(5,3,7);
        g.ajouterArc(5,7,8);
        g.ajouterArc(7,2,2);
        g.ajouterArc(7,1,6);
        g.ajouterArc(8,4,6);
        g.ajouterArc(8,2,7);
        g.ajouterArc(9,10,8);


        PCCDijkstra pc = new PCCDijkstra();
        ArrayList<Integer> chemin = new ArrayList<>();
        pc.pc(g,1,2,chemin);
        assertTrue(chemin.toString().equals("[1, 4, 2]")); // 1, 3, 5, 2




        /*Sommet[] sommets1 = {Sommet.A, Sommet.B, Sommet.C, Sommet.D, Sommet.E};
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

        thrownException.expect(ArcNegatifEx.class);
        thrownException.expectMessage("Le graphe comprend un arc négatif"); //Tests that the message contains -400.
        PCCDijkstra.dijkstra(g,Sommet.A,Sommet.B);

    }

    @Test
    public void pasDeCheminTest() throws NoPathEx {
        Sommet[] sommets1 = {Sommet.A, Sommet.B, Sommet.C, Sommet.D, Sommet.E};
        IGraph g = new graphes.GrapheMA(sommets1);
        g.ajouterArc(Sommet.A,Sommet.C,7);
        g.ajouterArc(Sommet.A,Sommet.D,15);
        g.ajouterArc(Sommet.B,Sommet.D,21);

        thrownException.expect(NoPathEx.class);
        thrownException.expectMessage("Ce graphe ne comprend pas ce chemin"); //Tests that the message contains -400.
        PCCDijkstra.dijkstra(g,Sommet.A,Sommet.B);*/
    }
}