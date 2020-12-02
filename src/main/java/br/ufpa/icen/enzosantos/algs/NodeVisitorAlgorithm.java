package br.ufpa.icen.enzosantos.algs;

import br.ufpa.icen.enzosantos.Graph;
import br.ufpa.icen.enzosantos.NodeVisitor;

/**
 * Representa um algoritmo que visita vértices de um grafo.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices a serem visitados.
 */
public abstract class NodeVisitorAlgorithm<V> implements NodeVisitor<V>, Algorithm<V> {
    /**
     * O grafo cujos vértices serão visitados.
     */
    protected final Graph<V> graph;

    /**
     * Constrói um algoritmo visitador de vértices.
     *
     * @param graph o grafo a ser visitado.
     */
    protected NodeVisitorAlgorithm(final Graph<V> graph) {
        this.graph = graph;
    }

    @Override
    public boolean shouldVisit(final V neighbor) {
        return true;
    }

    /**
     * Percorre o grafo começando em um determinado vértice.
     *
     * @param value o vértice inicial a ser percorrido.
     */
    protected final void search(final V value) {
        onPreVisit(value);

        for (final V neighbor : graph.getNeighbors(value).keySet()) {
            if (shouldVisit(neighbor)) {
                onVisit(value, neighbor);
            }
        }

        onPostVisit(value);
    }
}
