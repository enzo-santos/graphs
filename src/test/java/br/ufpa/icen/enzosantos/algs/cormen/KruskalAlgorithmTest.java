package br.ufpa.icen.enzosantos.algs.cormen;

import br.ufpa.icen.enzosantos.Edge;
import br.ufpa.icen.enzosantos.Graph;
import br.ufpa.icen.enzosantos.ListGraph;
import br.ufpa.icen.enzosantos.UndirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KruskalAlgorithmTest {
    /**
     * Source: https://www.oreilly.com/library/view/c-data-structures/9781788833738/assets/f5ee36e3-7ca4-425f-8907-e1830736b7bc.png
     */
    @Test
    void run() {
        final Graph<String> graph = new UndirectedGraph<>(
            new ListGraph<>(Set.of("A", "B", "C", "D", "E", "F", "G", "H"))
        );
        graph.addEdge("A", "B", 3);
        graph.addEdge("A", "C", 5);
        graph.addEdge("B", "D", 4);
        graph.addEdge("C", "D", 12);
        graph.addEdge("D", "E", 9);
        graph.addEdge("D", "H", 8);
        graph.addEdge("E", "F", 4);
        graph.addEdge("E", "G", 5);
        graph.addEdge("E", "H", 1);
        graph.addEdge("F", "G", 6);
        graph.addEdge("G", "H", 20);

        final KruskalAlgorithm<String> algorithm = new KruskalAlgorithm<>(graph);
        assertThrows(IllegalStateException.class, algorithm::getEdges);
        algorithm.run("A");

        final Set<Edge<String>> expected = Set.of(
            new Edge<>("A", "C", 5),
            new Edge<>("A", "B", 3),
            new Edge<>("B", "D", 4),
            new Edge<>("D", "H", 8),
            new Edge<>("E", "H", 1),
            new Edge<>("E", "F", 4),
            new Edge<>("E", "G", 5)
        );
        final Set<Edge<String>> actual = algorithm.getEdges();
        assertEquals(expected, actual);
    }

    /**
     * Source: https://www.baeldung.com/wp-content/uploads/2019/12/minimum_spanning_tree_alg.png
     */
    @Test
    void run_DifferentStartingNodesSameResult() {
        final Graph<String> graph = new UndirectedGraph<>(
            new ListGraph<>(Set.of("A", "B", "C", "D", "E"))
        );
        graph.addEdge("A", "B", 8);
        graph.addEdge("A", "C", 5);
        graph.addEdge("B", "C", 9);
        graph.addEdge("B", "D", 11);
        graph.addEdge("C", "D", 15);
        graph.addEdge("C", "E", 10);
        graph.addEdge("D", "E", 7);

        final KruskalAlgorithm<String> algorithm = new KruskalAlgorithm<>(graph);
        assertThrows(IllegalStateException.class, algorithm::getEdges);

        final Set<Edge<String>> expected = Set.of(
            new Edge<>("A", "B", 8),
            new Edge<>("A", "C", 5),
            new Edge<>("C", "E", 10),
            new Edge<>("D", "E", 7)
        );
        algorithm.run("A");
        assertEquals(expected, algorithm.getEdges());
        algorithm.run("F");
        assertEquals(expected, algorithm.getEdges());
    }

}