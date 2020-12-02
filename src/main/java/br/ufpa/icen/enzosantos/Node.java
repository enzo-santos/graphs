package br.ufpa.icen.enzosantos;

import java.util.Objects;

/**
 * Representa um vértice.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nesse vértice.
 */
public class Node<V> {
    /**
     * O conteúdo desse vértice.
     */
    public final V value;

    protected Node(final V value) {
        this.value = value;
    }

    /**
     * Constrói um vértice com dado valor.
     *
     * @param value o valor que esse vértice irá armazenar.
     * @param <T>   o tipo do conteúdo a ser armazenado nesse vértice.
     * @return o vértice com o valor armazenado.
     */
    public static <T> Node<T> of(final T value) {
        return new Node<>(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}