package br.ufpa.icen.enzosantos;

/**
 * Representa um grafo.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices desse grafo.
 */
public abstract class Graph<V> extends ExtensibleAbstractGraph<V> {
    /**
     * Constrói um grafo baseado em um grafo abstrato.
     *
     * @param graph o grafo abstrato.
     */
    public Graph(AbstractGraph<V> graph) {
        super(graph);
    }

    /**
     * Adiciona uma aresta a esse grafo.
     *
     * @param fromValue o vértice de partida da aresta.
     * @param toValue   o vértice de chegada da aresta.
     * @param weight    o peso da aresta.
     * @return se a aresta foi adicionada com sucesso (isto é, se ambos os vértices de partida e de chegada estão
     * presentes nesse grafo e se o peso da aresta é diferente de zero).
     */
    public abstract boolean addEdge(final V fromValue, final V toValue, final int weight);

    public boolean addEdge(final Edge<V> edge) {
        return addEdge(edge.fromValue, edge.toValue, edge.weight);
    }

    /**
     * Adiciona uma aresta de peso 1 a esse grafo.
     *
     * @param fromValue o vértice de partida da aresta.
     * @param toValue   o vértice de chegada da aresta.
     * @return se a aresta foi adicionada com sucesso (isto é, se ambos os vértices de partida e de chegada estão
     * presentes nesse grafo).
     */
    public boolean addEdge(final V fromValue, final V toValue) {
        return addEdge(fromValue, toValue, 1);
    }

    /**
     * Copia o direcionamento desse grafo para um grafo abstrato.
     *
     * @param to o grafo abstrato cuja direcionamento das arestas será a mesma desse grafo.
     * @return o grafo abstrato com o novo direcionamento.
     */
    public Graph<V> copyDirection(final AbstractGraph<V> to) {
        if (to == null) {
            return null;
        }

        if (this instanceof DirectedGraph) {
            return new DirectedGraph<>(to);
        }

        if (this instanceof UndirectedGraph) {
            return new UndirectedGraph<>(to);
        }

        throw new IllegalStateException("copyDirection has an invalid receiver");
    }
}
