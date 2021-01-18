package br.ufpa.icen.enzosantos;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Um grafo representado por uma lista de adjacências.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices desse grafo.
 */
public class ListGraph<V> extends AbstractGraph<V> {
    protected final List<Node<V>> nodes;
    protected final HashMap<Node<V>, Set<Node<V>>> map;

    /**
     * Constrói um grafo sem nenhuma aresta.
     *
     * @param values os valores dos vértices desse grafo.
     */
    public ListGraph(final Set<V> values) {
        this.nodes = values.stream().map(Node::of).collect(Collectors.toList());
        this.map = new HashMap<>();
    }

    protected ListGraph(final List<Node<V>> nodes) {
        this.nodes = nodes;
        this.map = new HashMap<>();
    }

    @Override
    public Node<V> getNode(final V value) {
        return nodes.stream().filter(node -> node.value.equals(value)).findFirst().orElseThrow();
    }

    @Override
    public List<V> getValues() {
        return nodes.stream().map(node -> node.value).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Edge<V>> getEdges() {
        final List<Edge<V>> edges = new ArrayList<>();
        for (var mapEntry : this.map.entrySet()) {
            final Node<V> fromNode = mapEntry.getKey();
            final Set<Node<V>> neighbors = mapEntry.getValue();
            for (final Node<V> neighbor : neighbors) {
                final WeightedNode<V> weightedNode = (WeightedNode<V>) neighbor;
                edges.add(new Edge<>(fromNode.value, weightedNode.value, weightedNode.weight));
            }
        }
        return edges;
    }

    @Override
    public Map<V, Integer> getNeighbors(final V value) {
        final Node<V> node = getNode(value);
        return map.computeIfAbsent(node, k -> new LinkedHashSet<>())
            .stream()
            .map(neighbor -> (WeightedNode<V>) neighbor)
            .collect(Collectors.toMap(e -> e.value, e -> e.weight));
    }

    @Override
    public boolean onEdgeAdded(final V fromValue, final V toValue, final int weight) {
        if (!contains(fromValue)) return false;
        if (!contains(toValue)) return false;
        if (weight == 0) return false;

        final Node<V> fromNode = getNode(fromValue);
        final Set<Node<V>> nodes = map.computeIfAbsent(fromNode, k -> new LinkedHashSet<>());
        nodes.add(new WeightedNode<>(toValue, weight));
        return true;
    }

    protected boolean contains(final V value) {
        return nodes.stream().anyMatch(node -> node.value == value);
    }
}
