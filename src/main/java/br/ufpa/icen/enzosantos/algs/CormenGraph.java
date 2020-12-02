package br.ufpa.icen.enzosantos.algs;

import br.ufpa.icen.enzosantos.Graph;
import br.ufpa.icen.enzosantos.ListGraph;
import br.ufpa.icen.enzosantos.Node;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Representa um grafo a ser utilizado em algoritmos baseados em Cormen et al.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices a serem visitados.
 */
public class CormenGraph<V> extends ListGraph<V> {
    /**
     * Transforma os vértices de um grafo em um vértice de Corman, mantendo suas arestas.
     *
     * @param graph o grafo a ser transformado.
     * @param <V>   o tipo do conteúdo a ser armazenado nos vértices a serem visitados.
     * @return o grafo com preenchido com vértices de Corman.
     */
    public static <V> Graph<V> transform(final Graph<V> graph) {
        final List<Node<V>> nodes = graph.getValues().stream().map(CormenNode::new).collect(Collectors.toList());
        final Graph<V> cormenGraph = graph.copyDirection(new CormenGraph<>(nodes));
        graph.getEdges().forEach(cormenGraph::addEdge);
        return cormenGraph;
    }

    /**
     * Constrói um grafo com vértices pré-determinados.
     *
     * @param nodes os vértices desse grafo.
     */
    protected CormenGraph(final List<Node<V>> nodes) {
        super(nodes);
    }
}
