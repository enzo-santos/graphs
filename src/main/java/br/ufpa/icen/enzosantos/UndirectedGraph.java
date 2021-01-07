package br.ufpa.icen.enzosantos;

/**
 * Representa um grafo não-direcionado.
 * <p>
 * Nesse grafo, vértices ligam duas arestas simetricamente.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices desse grafo.
 */
public class UndirectedGraph<V> extends Graph<V> {
    public UndirectedGraph(AbstractGraph<V> graph) {
        super(graph);
    }

    @Override
    public boolean addEdge(final V fromValue, final V toValue, final int weight) {
        return onEdgeAdded(fromValue, toValue, weight) && onEdgeAdded(toValue, fromValue, weight);
    }

    @Override
    public Graph<V> copyDirection(AbstractGraph<V> to) {
        return to == null ? null : new UndirectedGraph<>(to);
    }
}
