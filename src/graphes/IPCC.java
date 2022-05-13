package graphes;

import graphes.IGraphe;
import java.util.List;

public interface IPCC {
    final int INFINI = Integer.MAX_VALUE;
    int pc(IGraphe g, int debut, int fin, List<Integer> chemin);
}
