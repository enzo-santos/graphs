package br.ufpa.icen.enzosantos.algs;

import br.ufpa.icen.enzosantos.DirectedGraph;
import br.ufpa.icen.enzosantos.Graph;
import br.ufpa.icen.enzosantos.ListGraph;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CormenConnectedComponentsFinderTest {
    /**
     * Source: http://www2.hawaii.edu/~suthers/courses/ics311f20/Notes/Topic-14/Fig-22-9-SCC-by-DFS-a.jpg
     */
    @Test
    void run() {
        final Graph<String> graph = new DirectedGraph<>(
            new ListGraph<>(Set.of("a", "b", "c", "d", "e", "f", "g", "h"))
        );
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("b", "e");
        graph.addEdge("b", "f");
        graph.addEdge("c", "d");
        graph.addEdge("c", "g");
        graph.addEdge("d", "c");
        graph.addEdge("d", "h");
        graph.addEdge("e", "a");
        graph.addEdge("e", "f");
        graph.addEdge("f", "g");
        graph.addEdge("g", "f");
        graph.addEdge("g", "h");
        graph.addEdge("h", "h");

        final CormenConnectedComponentsFinder<String> finder;
        finder = new CormenConnectedComponentsFinder<>(graph);
        assertThrows(IllegalStateException.class, finder::getConnectedComponents);
        finder.run("c");
        final List<Set<String>> actualList = finder.getConnectedComponents();
        final List<Set<String>> expectedList = List.of(
            Set.of("a", "b", "e"),
            Set.of("c", "d"),
            Set.of("f", "g"),
            Set.of("h")
        );
        assertEquals(expectedList, actualList);
    }

    /**
     * Source: https://stackoverflow.com/questions/33590974/how-to-find-strongly-connected-components-in-a-graph
     */
    @Test
    void run_DifferentStartingNodesSameResult() {
        final Graph<String> graph = new DirectedGraph<>(
            new ListGraph<>(Set.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"))
        );
        graph.addEdge("a", "c");
        graph.addEdge("a", "h");
        graph.addEdge("b", "a");
        graph.addEdge("b", "g");
        graph.addEdge("c", "d");
        graph.addEdge("d", "f");
        graph.addEdge("e", "a");
        graph.addEdge("e", "i");
        graph.addEdge("f", "j");
        graph.addEdge("g", "i");
        graph.addEdge("h", "f");
        graph.addEdge("h", "g");
        graph.addEdge("i", "h");
        graph.addEdge("j", "c");

        final CormenConnectedComponentsFinder<String> finder;
        finder = new CormenConnectedComponentsFinder<>(graph);
        finder.run("a");

        List<Set<String>> actualList = finder.getConnectedComponents();
        final List<Set<String>> expectedList = List.of(
            Set.of("e"),
            Set.of("b"),
            Set.of("a"),
            Set.of("h", "i", "g"),
            Set.of("c", "j", "f", "d")
        );
        assertEquals(expectedList, actualList);

        finder.run("c");
        actualList = finder.getConnectedComponents();
        assertEquals(expectedList, actualList);
    }
}