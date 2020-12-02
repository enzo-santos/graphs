package br.ufpa.icen.enzosantos;

import java.util.Objects;

/**
 * Representa uma aresta.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices dessa aresta.
 */
public class Edge<V> {
    /**
     * O vértice de partida dessa aresta.
     */
    public final V fromValue;

    /**
     * O vértice de chegada dessa aresta.
     */
    public final V toValue;

    /**
     * O peso dessa aresta.
     */
    public final int weight;

    /**
     * Constrói uma aresta.
     *
     * @param fromValue o vértice de partida dessa aresta.
     * @param toValue   o vértice de chegada dessa aresta.
     * @param weight    o peso dessa aresta.
     */
    public Edge(final V fromValue, final V toValue, final int weight) {
        this.fromValue = fromValue;
        this.toValue = toValue;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return weight == edge.weight &&
            Objects.equals(fromValue, edge.fromValue) &&
            Objects.equals(toValue, edge.toValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromValue, toValue, weight);
    }
}
