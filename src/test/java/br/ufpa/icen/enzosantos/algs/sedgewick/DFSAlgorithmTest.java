package br.ufpa.icen.enzosantos.algs.sedgewick;

import br.ufpa.icen.enzosantos.Graph;
import br.ufpa.icen.enzosantos.ListGraph;
import br.ufpa.icen.enzosantos.UndirectedGraph;
import br.ufpa.icen.enzosantos.algs.sedgewick.DFSAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DFSAlgorithmTest {
    @Test
    void run() {
        final Graph<String> graph = new UndirectedGraph<>(
            new ListGraph<>(Set.of("a", "b", "c", "d", "e", "f"))
        );

        graph.addEdge("a", "b");
        graph.addEdge("a", "c");
        graph.addEdge("a", "f");
        graph.addEdge("b", "c");
        graph.addEdge("c", "d");
        graph.addEdge("c", "e");
        graph.addEdge("d", "e");
        graph.addEdge("d", "f");

        final DFSAlgorithm<String> dfs = new DFSAlgorithm<>(graph);
        assertThrows(IllegalStateException.class, dfs::getCount);
        dfs.run("a");
        assertEquals(6, dfs.getCount());
    }
}