package br.ufpa.icen.enzosantos.algs.cormen;

import br.ufpa.icen.enzosantos.Graph;
import br.ufpa.icen.enzosantos.ListGraph;
import br.ufpa.icen.enzosantos.UndirectedGraph;
import br.ufpa.icen.enzosantos.algs.cormen.BFSAlgorithm;
import br.ufpa.icen.enzosantos.algs.cormen.CormenNode;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BFSAlgorithmTest {
    /**
     * Source: https://rusyasoft.github.io/assets/images/graphs/BFS_cormen_stepbystep_process.png
     */
    @Test
    void run() {
        final Graph<String> graph = new UndirectedGraph<>(
            new ListGraph<>(Set.of("r", "s", "t", "u", "v", "w", "x", "y"))
        );

        graph.addEdge("r", "s");
        graph.addEdge("r", "v");
        graph.addEdge("s", "w");
        graph.addEdge("t", "w");
        graph.addEdge("t", "x");
        graph.addEdge("t", "u");
        graph.addEdge("u", "x");
        graph.addEdge("u", "y");
        graph.addEdge("w", "x");
        graph.addEdge("x", "y");

        final BFSAlgorithm<String> bfs = new BFSAlgorithm<>(graph);
        bfs.run("s");

        final Graph<String> bfsGraph = bfs.getGraph();
        final Map<String, CormenNode<String>> map = bfsGraph.getValues().stream()
            .map(value -> ((CormenNode<String>) bfsGraph.getNode(value)))
            .collect(Collectors.toMap(node -> node.value, node -> node));

        assertEquals(1, map.get("r").getGrayValue());
        assertEquals(0, map.get("s").getGrayValue());
        assertEquals(2, map.get("t").getGrayValue());
        assertEquals(3, map.get("u").getGrayValue());
        assertEquals(2, map.get("v").getGrayValue());
        assertEquals(1, map.get("w").getGrayValue());
        assertEquals(2, map.get("x").getGrayValue());
        assertEquals(3, map.get("y").getGrayValue());
    }
}