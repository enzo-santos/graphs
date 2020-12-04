package br.ufpa.icen.enzosantos;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Um grafo representado por uma matriz de adjacências.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices desse grafo.
 */
public class MatrixGraph<V> extends AbstractGraph<V> {
    protected final List<Node<V>> nodes;
    protected final int[][] matrix;

    /**
     * Constrói um grafo sem nenhuma aresta.
     *
     * @param values os valores dos vértices desse grafo.
     */
    public MatrixGraph(final Set<V> values) {
        this.nodes = values.stream().map(Node::of).collect(Collectors.toList());
        this.matrix = new int[values.size()][values.size()];
    }

    @Override
    public Node<V> getNode(final V value) {
        return nodes.stream().filter(node -> node.value == value).findFirst().orElseThrow();
    }

    /**
     * Procura por um vértice na matriz de adjacências.
     *
     * @param node o vértice a ser pesquisado.
     * @return a linha/coluna desse vértice na matriz de adjacências.
     * @throws IllegalArgumentException se o vértice não está presente nesse grafo.
     */
    protected int indexOf(final Node<V> node) {
        return nodes.indexOf(node);
    }

    /**
     * Procura os valores que estão na linha da matriz de adjacências respectiva a um vértice.
     *
     * @param node o vértice cujas adjacências serão procuradas.
     * @return os valores da linha respectiva a esse vértice.
     */
    protected int[] getOutgoingValues(final Node<V> node) {
        final int index = indexOf(node);
        return matrix[index];
    }

    @Override
    public List<V> getValues() {
        return nodes.stream().map(node -> node.value).collect(Collectors.toList());
    }

    @Override
    public List<Edge<V>> getEdges() {
        final List<Edge<V>> edges = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            final Node<V> fromNode = nodes.get(i);
            final int[] row = matrix[i];
            for (int j = 0; j < nodes.size(); j++) {
                final Node<V> toNode = nodes.get(j);
                final int value = row[j];
                if (value == 0) continue;
                edges.add(new Edge<>(fromNode.value, toNode.value, value));
            }
        }
        return edges;
    }

    @Override
    public Map<V, Integer> getNeighbors(final V value) {
        final Node<V> node = getNode(value);
        final int[] values = getOutgoingValues(node);
        final Map<V, Integer> neighbors = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            final int neighborValue = values[i];
            if (neighborValue == 0) continue;
            neighbors.put(nodes.get(i).value, neighborValue);
        }
        return neighbors;
    }

    @Override
    public boolean onEdgeAdded(final V fromValue, final V toValue, int weight) {
        if (!contains(fromValue)) return false;
        if (!contains(toValue)) return false;
        if (weight == 0) return false;

        final int i = indexOf(getNode(fromValue));
        final int j = indexOf(getNode(toValue));
        matrix[i][j] = weight;
        return true;
    }

    protected boolean contains(final V value) {
        return nodes.stream().anyMatch(node -> node.value == value);
    }
}
