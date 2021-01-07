package br.ufpa.icen.enzosantos;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TransposedGraphTest {
    @Test
    void getEdges() {
        final Graph<String> graph = new DirectedGraph<>(new ListGraph<>(Set.of("a", "b", "c", "d")));
        graph.addEdge("a", "b", 1);
        graph.addEdge("a", "c", 1);
        graph.addEdge("b", "c", 1);

        Set<Edge<String>> actualEdges = new HashSet<>(graph.getEdges());
        Set<Edge<String>> expectedEdges = Set.of(
            new Edge<>("a", "b", 1),
            new Edge<>("a", "c", 1),
            new Edge<>("b", "c", 1)
        );
        assertEquals(expectedEdges, actualEdges);

        final Graph<String> transposedGraph = new TransposedGraph<>(graph);
        actualEdges = new HashSet<>(transposedGraph.getEdges());
        expectedEdges = Set.of(
            new Edge<>("b", "a", 1),
            new Edge<>("c", "a", 1),
            new Edge<>("c", "b", 1)
        );
        assertEquals(expectedEdges, actualEdges);

        graph.addEdge("a", "d", 1);
        actualEdges = new HashSet<>(transposedGraph.getEdges());
        expectedEdges = Set.of(
            new Edge<>("b", "a", 1),
            new Edge<>("c", "a", 1),
            new Edge<>("c", "b", 1),
            new Edge<>("d", "a", 1)
        );
        assertEquals(expectedEdges, actualEdges);

        transposedGraph.addEdge("c", "d", 1);
        actualEdges = new HashSet<>(transposedGraph.getEdges());
        expectedEdges = Set.of(
            new Edge<>("b", "a", 1),
            new Edge<>("c", "a", 1),
            new Edge<>("c", "b", 1),
            new Edge<>("d", "a", 1),
            new Edge<>("c", "d", 1)
        );
        assertEquals(expectedEdges, actualEdges);
    }

    @Test
    void getNeighbors() {
        final Graph<String> graph = new DirectedGraph<>(new ListGraph<>(Set.of("a", "b", "c", "d")));
        graph.addEdge("a", "b", 1);
        graph.addEdge("a", "c", 1);
        graph.addEdge("b", "c", 1);

        assertEquals(Map.of("b", 1, "c", 1), graph.getNeighbors("a"));
        assertEquals(Map.of("c", 1), graph.getNeighbors("b"));
        assertEquals(Map.of(), graph.getNeighbors("c"));

        final Graph<String> transposedGraph = new TransposedGraph<>(graph);
        assertEquals(Map.of(), transposedGraph.getNeighbors("a"));
        assertEquals(Map.of("a", 1), transposedGraph.getNeighbors("b"));
        assertEquals(Map.of("a", 1, "b", 1), transposedGraph.getNeighbors("c"));

        graph.addEdge("d", "a");
        assertEquals(Map.of("d", 1), transposedGraph.getNeighbors("a"));
        assertEquals(Map.of("a", 1), transposedGraph.getNeighbors("b"));
        assertEquals(Map.of("a", 1, "b", 1), transposedGraph.getNeighbors("c"));
    }

    @Test
    void copyDirection() {
        final AbstractGraph<String> lg = new ListGraph<>(Set.of("d", "e", "f"));

        final Graph<String> tg = new TransposedGraph<>(new ListGraph<>(Set.of("a", "b", "c")));
        assertTrue(tg.copyDirection(lg) instanceof TransposedGraph);
        assertNull(tg.copyDirection(null));
    }
}