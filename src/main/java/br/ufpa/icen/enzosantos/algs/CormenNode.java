package br.ufpa.icen.enzosantos.algs;

import br.ufpa.icen.enzosantos.Node;

/**
 * Representa um vértice a ser utilizado por algoritmos baseados em Cormen et. al.
 * <p>
 * Esse vértice contém informações como a cor do vértice, o vértice anterior, o tempo de quando o vértice foi descoberta
 * e colorida como cinza e o tempo de quando o vértice é colorida como preta e a busca é finalizada.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nesse vértice.
 */
class CormenNode<V> extends Node<V> {
    private enum Color {black, white, gray}

    /**
     * A cor da vértice, podendo ser preto, branco ou cinza.
     */
    private Color color = Color.white;

    /**
     * O vértice anterior. Caso ainda não tenha, seu valor é `null`.
     */
    private Node<V> previous;

    /**
     * O tempo de quando esse vértice foi descoberto e colorido como cinza. Caso esse vértice ainda não tenha sido
     * descoberto, seu valor é "infinito" (o maior valor a ser armazenado por uma variável inteira).
     */
    private int grayValue = Integer.MAX_VALUE;

    /**
     * O tempo de quando a busca finaliza o exame da lista de adjacências desse vértice e é colorido como preto. Caso
     * esse vértice ainda não tenha sido descoberto e finalizado, seu valor é "infinito" (o maior valor a ser
     * armazenado por uma variável inteira).
     */
    private int blackValue = Integer.MAX_VALUE;

    /**
     * Inicializa um vértice por Cormen.
     *
     * @param value o valor do vértice.
     */
    CormenNode(final V value) {
        super(value);
    }

    /**
     * @return se a cor desse vértice é branco (isto é, se ainda não foi visitado).
     */
    public boolean isWhite() {
        return color == Color.white;
    }

    /**
     * @return o tempo que esse vértice foi colorido como cinza.
     */
    public int getGrayValue() {
        return grayValue;
    }

    /**
     * @return o tempo que esse vértice foi colorido como preto.
     */
    public int getBlackValue() {
        return blackValue;
    }

    /**
     * Define o tempo que esse vértice foi colorido como cinza.
     *
     * @param time o tempo a ser definido.
     */
    public void setGrayColor(final int time) {
        this.color = Color.gray;
        this.grayValue = time;
    }

    /**
     * Define o tempo que esse vértice foi colorido como preto.
     *
     * @param time o tempo a ser definido.
     */
    public void setBlackColor(final int time) {
        this.color = Color.black;
        this.blackValue = time;
    }

    /**
     * Define a cor desse vértice como preta.
     */
    public void setBlackColor() {
        this.color = Color.black;
    }

    /**
     * Define o último vértice a ser visitado anterior a esse.
     *
     * @param previous o último vértice.
     */
    public void setPrevious(final Node<V> previous) {
        this.previous = previous;
    }
}
