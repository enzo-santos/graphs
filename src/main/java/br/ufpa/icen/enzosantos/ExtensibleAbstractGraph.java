package br.ufpa.icen.enzosantos;

import java.util.List;
import java.util.Map;

/**
 * Representa um grafo abstrato extensível.
 * <p>
 * Essa classe pode adicionar novas funcionalidades a um grafo abstrato já existente sem alterar sua estrutura.
 * <p>
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices desse grafo.
 * @see <a href="Decorator pattern">https://en.wikipedia.org/wiki/Decorator_pattern</a>
 */
public abstract class ExtensibleAbstractGraph<V> extends AbstractGraph<V> {
    /**
     * O grafo abstrato cujas novas funcionalidades serão adicionadas por meio desse extensor.
     */
    protected final AbstractGraph<V> graph;

    /**
     * Constrói um extensor sobre um grafo abstrato.
     *
     * @param graph o grafo abstrato a ser estendido.
     */
    public ExtensibleAbstractGraph(final AbstractGraph<V> graph) {
        this.graph = graph;
    }

    @Override
    public Node<V> getNode(final V value) {
        return graph.getNode(value);
    }

    @Override
    public List<V> getValues() {
        return graph.getValues();
    }

    @Override
    public List<Edge<V>> getEdges() {
        return graph.getEdges();
    }

    @Override
    public Map<V, Integer> getNeighbors(final V value) {
        return graph.getNeighbors(value);
    }

    @Override
    protected boolean onEdgeAdded(final V fromValue, final V toValue, final int weight) {
        return graph.onEdgeAdded(fromValue, toValue, weight);
    }
}
