package test.graphes;

import static org.junit.jupiter.api.Assertions.*;

import graphes.Sommet;
import org.junit.jupiter.api.Test;

import graphes.GrapheLA;
import graphes.GrapheMA;
import graphes.IGraph;

class GrapheTest {

    // Graphe de l'exercice 3.1 du poly de maths
    @Test
    void test() {
        Sommet[] noeuds = {Sommet.A, Sommet.B, Sommet.C, Sommet.D, Sommet.E, Sommet.F, Sommet.G, Sommet.H, Sommet.I};
        IGraph g = new GrapheMA(noeuds);
        tester(g);
        g = new GrapheLA(noeuds);
        tester(g);
    }

    void tester(IGraph g) {
        assertEquals(9, g.getNbNoeuds());
        g.ajouterArc(Sommet.A,Sommet.C,2);
        g.ajouterArc(Sommet.A,Sommet.D,1);
        g.ajouterArc(Sommet.B,Sommet.G,3);
        g.ajouterArc(Sommet.C,Sommet.H,2);
        g.ajouterArc(Sommet.D,Sommet.B,3);
        g.ajouterArc(Sommet.D,Sommet.C,5);
        g.ajouterArc(Sommet.D,Sommet.E,3);
        g.ajouterArc(Sommet.E,Sommet.C,1);
        g.ajouterArc(Sommet.E,Sommet.G,3);
        g.ajouterArc(Sommet.E,Sommet.H,7);
        g.ajouterArc(Sommet.G,Sommet.B,2);
        g.ajouterArc(Sommet.G,Sommet.F,1);
        g.ajouterArc(Sommet.H,Sommet.F,4);
        g.ajouterArc(Sommet.H,Sommet.G,2);
        g.ajouterArc(Sommet.I,Sommet.H,10);

        assertTrue(g.aArc(Sommet.A,Sommet.D));
        assertTrue(g.aArc(Sommet.B,Sommet.G));
        assertTrue(g.aArc(Sommet.E,Sommet.G));
        assertTrue(g.aArc(Sommet.E,Sommet.H));
        assertTrue(g.aArc(Sommet.H,Sommet.F));

        assertFalse(g.aArc(Sommet.D,Sommet.A));
        assertFalse(g.aArc(Sommet.H,Sommet.C));
        assertFalse(g.aArc(Sommet.I,Sommet.I));

        assertEquals(2,g.dOut(Sommet.A));
        assertEquals(1,g.dOut(Sommet.B));
        assertEquals(1,g.dOut(Sommet.C));
        assertEquals(3,g.dOut(Sommet.D));
        assertEquals(0,g.dOut(Sommet.F));
        assertEquals(1,g.dOut(Sommet.I));

        assertEquals(0,g.dIn(Sommet.A));
        assertEquals(2,g.dIn(Sommet.B));
        assertEquals(2,g.dIn(Sommet.F));
        assertEquals(0,g.dIn(Sommet.I));

        assertEquals(3,g.getValeur(Sommet.D, Sommet.E));
        assertEquals(7,g.getValeur(Sommet.E, Sommet.H));
        assertEquals(10,g.getValeur(Sommet.I, Sommet.H));
        assertEquals(5,g.getValeur(Sommet.D, Sommet.C));

        System.out.println(g);
    }


}
