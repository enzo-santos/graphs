package br.ufpa.icen.enzosantos;

/**
 * Representa um grafo direcionado.
 * <p>
 * Nesse grafo, vértices ligam duas arestas simetricamente.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices desse grafo.
 */
public class DirectedGraph<V> extends Graph<V> {
    public DirectedGraph(AbstractGraph<V> graph) {
        super(graph);
    }

    @Override
    public boolean addEdge(final V fromValue, final V toValue, final int weight) {
        return onEdgeAdded(fromValue, toValue, weight);
    }
}
