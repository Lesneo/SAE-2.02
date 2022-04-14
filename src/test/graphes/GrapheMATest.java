package test.graphes;

import static org.junit.Assert.*;

import graphes.GrapheMA;
import graphes.Sommet;
import org.junit.Test;

public class GrapheMATest {
    private final static int NB_NOEUDS = 6;
    Sommet[] sommets = {Sommet.A,Sommet.B,Sommet.C,Sommet.D,Sommet.E,Sommet.F};
    
    /**
     * Test global des différentes méthodes liées à
     * la manipulation d'un graphe sous forme de matrice
     * d'adjacence
     */
    @Test
    public void test() {
        GrapheMA g = new GrapheMA(sommets);
        /**
         * On vérifie l'égalité entre le nombre de noeuds
         * et la méthode correspondante
         */
        assertEquals(NB_NOEUDS, g.getNbNoeuds());

        // On ajoute des arcs à notre graphe
        g.ajouterArc(Sommet.A, Sommet.B, 5);
        g.ajouterArc(Sommet.A, Sommet.C, 7);
        g.ajouterArc(Sommet.A, Sommet.D, 9);
        g.ajouterArc(Sommet.A, Sommet.E, 12);
        g.ajouterArc(Sommet.B, Sommet.E, 3);
        g.ajouterArc(Sommet.D, Sommet.D, 13);
        g.ajouterArc(Sommet.E, Sommet.A, 17);

        /**
         * On vérifie que la méthode aArc renvoie bien
         * true quand on lui sommet des arcs existants
         */
        assertTrue(g.aArc(Sommet.A, Sommet.E));
        assertTrue(g.aArc(Sommet.D, Sommet.D));
        assertTrue(g.aArc(Sommet.E, Sommet.A));

        /**
         * On vérifie que la méthode aArc renvoie bien
         * false quand on lui sommet des arcs inexistants
         */
        assertFalse(g.aArc(Sommet.D, Sommet.A));
        assertFalse(g.aArc(Sommet.F, Sommet.F));

        /**
         * On vérifie que les degrés sortants de chaque 
         * sommet correspondent aux résultats attendus
         */
        assertEquals(4, g.dOut(Sommet.A));
        assertEquals(1, g.dOut(Sommet.B));
        assertEquals(0, g.dOut(Sommet.C));
        assertEquals(1, g.dOut(Sommet.E));
        assertEquals(0, g.dOut(Sommet.F));

        /**
         * On vérifie que la chaine de caractère obtenu
         * pour la représentation du graphe correspond
         * bien à celle voulu
         */
        assertEquals(1, g.dIn(Sommet.A));
        assertEquals(2, g.dIn(Sommet.D));
        assertEquals(2, g.dIn(Sommet.E));
        assertEquals(0, g.dIn(Sommet.F));

        assertTrue(g.toString().contentEquals(
                "0 5 7 9 12 0 \n" +
                "0 0 0 0 3 0 \n" +
                "0 0 0 0 0 0 \n" +
                "0 0 0 13 0 0 \n" +
                "17 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 \n"));
    }
}
