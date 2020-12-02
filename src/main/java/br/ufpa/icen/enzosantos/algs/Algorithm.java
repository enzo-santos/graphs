package br.ufpa.icen.enzosantos.algs;

/**
 * Representa um algoritmo que começa em uma determinada atesta,
 *
 * @param <V> o tipo do conteúdo a ser armazenado no vértice inicial.
 */
public interface Algorithm<V> {
    /**
     * Executa o algoritmo.
     *
     * @param startValue a aresta cujo algoritmo começará.
     */
    void run(final V startValue);
}
