package br.ufpa.icen.enzosantos.algs.cormen;

import br.ufpa.icen.enzosantos.DirectedGraph;
import br.ufpa.icen.enzosantos.Graph;
import br.ufpa.icen.enzosantos.ListGraph;
import br.ufpa.icen.enzosantos.algs.cormen.DFSAlgorithm;
import br.ufpa.icen.enzosantos.algs.cormen.CormenNode;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DFSAlgorithmTest {
    /**
     * Source: https://slideplayer.com/slide/12424750/74/images/16/Depth+first+search+%E2%80%93+example.jpg
     */
    @Test
    void run() {
        final Graph<String> graph = new DirectedGraph<>(
            new ListGraph<>(Set.of("u", "v", "w", "x", "y", "z"))
        );

        graph.addEdge("u", "v");
        graph.addEdge("u", "x");
        graph.addEdge("v", "y");
        graph.addEdge("w", "y");
        graph.addEdge("w", "z");
        graph.addEdge("x", "v");
        graph.addEdge("y", "x");
        graph.addEdge("z", "z");

        final DFSAlgorithm<String> dfs = new DFSAlgorithm<>(graph);
        dfs.run("u");
        final Graph<String> dfsGraph = dfs.getGraph();
        final Map<String, CormenNode<String>> map = dfsGraph.getValues().stream()
            .map(value -> ((CormenNode<String>) dfsGraph.getNode(value)))
            .collect(Collectors.toMap(node -> node.value, node -> node));

        assertNode(map.get("u"), 1, 8);
        assertNode(map.get("v"), 2, 7);
        assertNode(map.get("w"), 9, 12);
        assertNode(map.get("x"), 4, 5);
        assertNode(map.get("y"), 3, 6);
        assertNode(map.get("z"), 10, 11);
    }

    private static void assertNode(final CormenNode<?> node, final int grayValue, final int blackValue) {
        assertEquals(grayValue, node.getGrayValue());
        assertEquals(blackValue, node.getBlackValue());
    }
}