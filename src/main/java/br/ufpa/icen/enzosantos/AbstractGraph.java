package br.ufpa.icen.enzosantos;

import java.util.List;
import java.util.Map;

/**
 * Representa um grafo abstrato.
 * <p>
 * Não é possível inserir arestas diretamente nesse tipo de grafo visto que não se pode definir se suas arestas
 * são direcionadas ou não. Para isso, é possível utilizar as classes {@link DirectedGraph} e {@link UndirectedGraph}.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices desse grafo.
 */
public abstract class AbstractGraph<V> {
    /**
     * @param value o valor cujo nó correspondente deve ser retornado.
     * @return o nó respectivo.
     */
    public abstract Node<V> getNode(final V value);

    /**
     * @return os vértices desse grafo.
     */
    public abstract List<V> getValues();

    /**
     * @return as arestas desse grafo.
     */
    public abstract List<Edge<V>> getEdges();

    /**
     * Calcula os vizinhos de um vértice.
     *
     * @param value o vértice cujos vizinhos serão calculados.
     * @return um mapa onde, para cada aresta desse grafo onde esse vértice é um vértice de partida, sua chave é o
     * vértice de chegada dessa aresta e seu valor é o peso dessa aresta.
     */
    public abstract Map<V, Integer> getNeighbors(final V value);

    /**
     * Representa uma ação a ser realizada quando uma aresta é adicionada a esse grafo.
     *
     * @param fromValue o vértice de partida da aresta.
     * @param toValue   o vértice de chegada da aresta.
     * @param weight    o peso da aresta.
     * @return true se a aresta foi adicionada com sucesso (isto é, se ambos os vértices de partida e de chegada estão
     * presentes nesse grafo e se o peso da aresta é diferente de zero).
     */
    protected abstract boolean onEdgeAdded(final V fromValue, final V toValue, final int weight);

}
