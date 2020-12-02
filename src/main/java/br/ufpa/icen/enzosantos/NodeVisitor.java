package br.ufpa.icen.enzosantos;

/**
 * Representa uma estrutura que visita vértices.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices a serem visitados.
 */
public interface NodeVisitor<V> {
    /**
     * Define se um vizinho de um vértice deve ser visitado.
     *
     * @param neighbor o vizinho.
     * @return se ele deve ser visitado por essa estrutura.
     */
    boolean shouldVisit(final V neighbor);

    /**
     * Representa uma ação a ser realizada antes de visitar os filhos de um vértice.
     *
     * @param value o vértice cujos filhos serão visitados.
     */
    default void onPreVisit(final V value) {

    }

    /**
     * Representa uma ação a ser realizada ao visitar os filhos de um vértice.
     *
     * @param parentValue o vértice pai.
     * @param childValue  o vértice filho.
     */
    default void onVisit(final V parentValue, final V childValue) {

    }

    /**
     * Representa uma ação a ser realizada após visitar os filhos de um vértice.
     *
     * @param value o vértice cujos filhos foram visitados.
     */
    default void onPostVisit(final V value) {

    }
}
