package br.ufpa.icen.enzosantos;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GraphTest {
    @Test
    void buildListGraph() {
        final Graph<String> graph = new DirectedGraph<>(
            new ListGraph<>(Set.of("a", "b", "c", "d", "e"))
        );
        graph.addEdge("a", "b");
        graph.addEdge("a", "c");
        graph.addEdge("b", "d");
        graph.addEdge("b", "e");
        graph.addEdge("c", "d");
        graph.addEdge("d", "a");
        graph.addEdge("d", "d");
        graph.addEdge("d", "e");

        assertEquals(Set.of("a", "b", "c", "d", "e"), new HashSet<>(graph.getValues()));
        final List<Edge<String>> edges = graph.getEdges();
        assertEdgeEquals(new Edge<>("a", "b", 1), edges.get(0));
        assertEdgeEquals(new Edge<>("a", "c", 1), edges.get(1));
        assertEdgeEquals(new Edge<>("b", "d", 1), edges.get(2));
        assertEdgeEquals(new Edge<>("b", "e", 1), edges.get(3));
        assertEdgeEquals(new Edge<>("c", "d", 1), edges.get(4));
        assertEdgeEquals(new Edge<>("d", "a", 1), edges.get(5));
        assertEdgeEquals(new Edge<>("d", "d", 1), edges.get(6));
        assertEdgeEquals(new Edge<>("d", "e", 1), edges.get(7));

        assertEquals(Map.of("b", 1, "c", 1), graph.getNeighbors("a"));
        assertEquals(Map.of("d", 1, "e", 1), graph.getNeighbors("b"));
        assertEquals(Map.of("d", 1), graph.getNeighbors("c"));
        assertEquals(Map.of("a", 1, "d", 1, "e", 1), graph.getNeighbors("d"));
        assertEquals(Map.of(), graph.getNeighbors("e"));
    }

    @Test
    void buildMatrixGraph() {
        final Graph<String> graph = new DirectedGraph<>(
            new MatrixGraph<>(Set.of("a", "b", "c", "d", "e"))
        );
        graph.addEdge("a", "b");
        graph.addEdge("a", "c");
        graph.addEdge("b", "d");
        graph.addEdge("b", "e");
        graph.addEdge("c", "d");
        graph.addEdge("d", "a");
        graph.addEdge("d", "d");
        graph.addEdge("d", "e");

        assertEquals(Set.of("a", "b", "c", "d", "e"), new HashSet<>(graph.getValues()));
        final Set<Edge<String>> edges = new HashSet<>(graph.getEdges());
        assertEquals(Set.of(
            new Edge<>("a", "b", 1),
            new Edge<>("a", "c", 1),
            new Edge<>("b", "d", 1),
            new Edge<>("b", "e", 1),
            new Edge<>("c", "d", 1),
            new Edge<>("d", "a", 1),
            new Edge<>("d", "d", 1),
            new Edge<>("d", "e", 1)
        ), edges);

        assertEquals(Map.of("b", 1, "c", 1), graph.getNeighbors("a"));
        assertEquals(Map.of("d", 1, "e", 1), graph.getNeighbors("b"));
        assertEquals(Map.of("d", 1), graph.getNeighbors("c"));
        assertEquals(Map.of("a", 1, "d", 1, "e", 1), graph.getNeighbors("d"));
        assertEquals(Map.of(), graph.getNeighbors("e"));
    }

    private static void assertEdgeEquals(final Edge<?> e1, final Edge<?> e2) {
        assertEquals(e1.fromValue, e2.fromValue);
        assertEquals(e1.toValue, e2.toValue);
        assertEquals(e1.weight, e2.weight);
    }
}