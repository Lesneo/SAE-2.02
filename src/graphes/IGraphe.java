package graphes;

import java.util.ArrayList;

public interface IGraphe {
	int getNbSommets();
	void ajouterArc(int source, int valuation, int destination);
	int getValuation(int i, int j);
	boolean aArc(int i, int j);
	int distance(ArrayList<Integer> chemin);
}
