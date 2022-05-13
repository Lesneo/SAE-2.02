package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import graphes.ihm.GraphImporter;
import org.junit.jupiter.api.Test;

import pcc.CircuitAbsorbantEx;
import pcc.IPCC;
import pcc.NoPathEx;
import pcc.algo.PCCBellman;
import pcc.algo.PCCDijkstra;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

class PlusCourtTest {
	@Test
	void testDijkstra() throws NumberFormatException, FileNotFoundException, IOException {
		IPCC algo = new PCCDijkstra();
		assertTrue(GraphImporter.comparer("graphes\\sc\\g-10-1.txt", "reponses\\sc\\r-10-1.txt", algo));
	}

	@Test
	void testBellman() throws NumberFormatException, FileNotFoundException, IOException {
		IPCC algo = new PCCBellman();
		assertThrows(CircuitAbsorbantEx.class, () -> {GraphImporter.comparer("graphes\\ac\\g-10-2.txt", "reponses\\ac\\r-10-2.txt", algo);});
		assertTrue(GraphImporter.comparer("graphes\\sc\\g-10-1.txt", "reponses\\sc\\r-10-1.txt", algo));
	}
}
