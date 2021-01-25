package br.ufpa.icen.enzosantos.algs.cormen;

import br.ufpa.icen.enzosantos.DirectedGraph;
import br.ufpa.icen.enzosantos.Graph;
import br.ufpa.icen.enzosantos.ListGraph;
import br.ufpa.icen.enzosantos.algs.cormen.TopologicalSortAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TopologicalSortAlgorithmTest {
    /**
     * Source: https://www.cs.fsu.edu/~burmeste/slideshow/images_content/figure23_7.gif
     */
    @Test
    public void run() {
        final Map<String, String> aliases = Map.of(
            "A", "undershorts",
            "B", "socks",
            "C", "watch",
            "D", "pants",
            "E", "shoes",
            "F", "shirt",
            "G", "belt",
            "H", "tie",
            "I", "jacket"
        );

        final Graph<String> graph = new DirectedGraph<>(
            new ListGraph<>(Set.of("A", "B", "C", "D", "E", "F", "G", "H", "I"))
        );
        graph.addEdge("A", "D");
        graph.addEdge("A", "E");
        graph.addEdge("B", "E");
        graph.addEdge("D", "E");
        graph.addEdge("D", "G");
        graph.addEdge("F", "G");
        graph.addEdge("F", "H");
        graph.addEdge("G", "I");
        graph.addEdge("H", "I");
        graph.addEdge("C", "C");

        final TopologicalSortAlgorithm<String> algorithm = new TopologicalSortAlgorithm<>(graph);
        assertThrows(IllegalStateException.class, algorithm::getSortedValues);

        algorithm.run("F");
        final List<String> sortedValues = algorithm.getSortedValues();
        final List<String> sortedAliases = sortedValues.stream().map(aliases::get).collect(Collectors.toList());
        assertEquals(List.of(
            "jacket",
            "belt",
            "tie",
            "shirt",
            "shoes",
            "pants",
            "undershorts",
            "socks",
            "watch"
            ),
            sortedAliases
        );
    }
}