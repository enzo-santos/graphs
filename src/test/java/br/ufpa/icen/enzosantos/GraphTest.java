package br.ufpa.icen.enzosantos;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    @Test
    void buildGraphs() {
        final List<Function<Set<String>, AbstractGraph<String>>> builders;
        builders = List.of(ListGraph::new, MatrixGraph::new);
        builders.forEach(builder ->
            testBuild(new DirectedGraph<>(builder.apply(Set.of("a", "b", "c", "d", "e"))))
        );
    }

    @Test
    void copyDirection() {
        final AbstractGraph<String> lg = new ListGraph<>(Set.of("d", "e", "f"));

        final Graph<String> dg = new DirectedGraph<>(new ListGraph<>(Set.of("a", "b", "c")));
        assertTrue(dg.copyDirection(lg) instanceof DirectedGraph);
        assertNull(dg.copyDirection(null));

        final Graph<String> ug = new UndirectedGraph<>(new ListGraph<>(Set.of("a", "b", "c")));
        assertTrue(ug.copyDirection(lg) instanceof UndirectedGraph);
        assertNull(ug.copyDirection(null));
    }

    void testBuild(final Graph<String> graph) {
        assertTrue(graph.addEdge("a", "b"));
        assertTrue(graph.addEdge("a", "c"));
        assertTrue(graph.addEdge("b", "d"));
        assertTrue(graph.addEdge("b", "e"));
        assertTrue(graph.addEdge("c", "d"));
        assertTrue(graph.addEdge("d", "a"));
        assertTrue(graph.addEdge("d", "d"));
        assertTrue(graph.addEdge("d", "e"));

        assertFalse(graph.addEdge("a", "f"));
        assertFalse(graph.addEdge("f", "b"));
        assertFalse(graph.addEdge("a", "b", 0));

        assertEquals(Set.of("a", "b", "c", "d", "e"), new HashSet<>(graph.getValues()));
        final Set<Edge<String>> actualEdges = new HashSet<>(graph.getEdges());
        final Set<Edge<String>> expectedEdges = Set.of(
            new Edge<>("a", "b", 1),
            new Edge<>("a", "c", 1),
            new Edge<>("b", "d", 1),
            new Edge<>("b", "e", 1),
            new Edge<>("c", "d", 1),
            new Edge<>("d", "a", 1),
            new Edge<>("d", "d", 1),
            new Edge<>("d", "e", 1)
        );
        assertEquals(expectedEdges, actualEdges);

        assertEquals(Map.of("b", 1, "c", 1), graph.getNeighbors("a"));
        assertEquals(Map.of("d", 1, "e", 1), graph.getNeighbors("b"));
        assertEquals(Map.of("d", 1), graph.getNeighbors("c"));
        assertEquals(Map.of("a", 1, "d", 1, "e", 1), graph.getNeighbors("d"));
        assertEquals(Map.of(), graph.getNeighbors("e"));
    }
}