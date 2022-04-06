import org.junit.Test;

import static org.junit.Assert.*;

public class GrapheLATest {
    private final static int NB_NOEUDS = 6;

    /**
     * Test global des différentes méthodes liées à
     * la manipulation d'un graphe sous forme de liste d'adjacence
     */
    @Test
    public void test() {
        GrapheLA g = new GrapheLA(NB_NOEUDS);
        /**
         * On vérifie l'égalité entre le nombre de noeuds
         * et la méthode correspondante
         */
        assertEquals(NB_NOEUDS, g.getNbNoeuds());

        // On ajoute des arcs à notre graphe
        g.ajouterArc(1, 2, 5);
        g.ajouterArc(1, 3, 7);
        g.ajouterArc(1, 4, 9);
        g.ajouterArc(1, 5, 12);
        g.ajouterArc(2, 5, 3);
        g.ajouterArc(4, 4, 13);
        g.ajouterArc(5, 1, 17);

        /**
         * On vérifie que la méthode aArc renvoie bien
         * true quand on lui sommet des arcs existants
         */
        assertTrue(g.aArc(1, 5));
        assertTrue(g.aArc(4, 4));
        assertTrue(g.aArc(5, 1));

        /**
         * On vérifie que la méthode aArc renvoie bien
         * false quand on lui sommet des arcs inexistants
         */
        assertFalse(g.aArc(4, 1));
        assertFalse(g.aArc(6, 6));

        /**
         * On vérifie que les degrés sortants de chaque 
         * sommet correspondent aux résultats attendus
         */
        assertEquals(4, g.dOut(1));
        assertEquals(1, g.dOut(2));
        assertEquals(0, g.dOut(3));
        assertEquals(1, g.dOut(5));
        assertEquals(0, g.dOut(6));

        /**
         * On vérifie que les degrés entrants de chaque 
         * sommet correspondent aux résultats attendus
         */
        assertEquals(1, g.dIn(1));
        assertEquals(2, g.dIn(4));
        assertEquals(2, g.dIn(5));
        assertEquals(0, g.dIn(6));

        /**
         * On vérifie que la chaine de caractère obtenu
         * pour la représentation du graphe correspond
         * bien à celle voulu
         */
        assertTrue(g.toString().contentEquals(
                     "1 -> 2 3 4 5 \n"
                      + "2 -> 5 \n"
                      + "3 -> \n"
                      + "4 -> 4 \n"
                      + "5 -> 1 \n"
                      + "6 -> \n"
                      ));
    }
}