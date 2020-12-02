package br.ufpa.icen.enzosantos;

/**
 * Representa um vértice com determinado peso.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nesse vértice.
 */
public class WeightedNode<V> extends Node<V> {
    /**
     * O peso desse vértice.
     */
    public final int weight;

    /**
     * Constrói um vértice com peso.
     *
     * @param value  o valor que esse vértice irá armazenar.
     * @param weight o peso que esse vértice terá.
     */
    public WeightedNode(final V value, final int weight) {
        super(value);
        this.weight = weight;
    }
}