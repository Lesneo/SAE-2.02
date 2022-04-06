import org.junit.Test;

import static org.junit.Assert.*;

public class PCCDijkstraTest {
    private final static int NB_NOEUDS = 6;
    
    /**
     * Test global des différentes méthodes liées à
     * la manipulation d'un graphe sous forme de matrice
     * d'adjacence
     */
    @Test
    public void test() {
        GrapheMA g = new GrapheMA(NB_NOEUDS);
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

    }
}
