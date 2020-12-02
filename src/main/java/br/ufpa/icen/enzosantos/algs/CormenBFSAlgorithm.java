package br.ufpa.icen.enzosantos.algs;

import br.ufpa.icen.enzosantos.Graph;

import java.util.LinkedList;

/**
 * Representa um algoritmo Breadth-First-Search por Cormen et. al.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices a serem visitados.
 */
public class CormenBFSAlgorithm<V> extends NodeVisitorAlgorithm<V> {
    /**
     * Constrói um algoritmo BFS por Cormen et al.
     *
     * @param graph o grafo no qual esse algoritmo atuará.
     */
    public CormenBFSAlgorithm(final Graph<V> graph) {
        super(CormenGraph.transform(graph));
    }

    /**
     * A fila de nós a serem visitados.
     */
    private final LinkedList<CormenNode<V>> queue = new LinkedList<>();

    /**
     * @return o grafo no qual esse algoritmo será aplicado.
     */
    public Graph<V> getGraph() {
        return graph;
    }

    @Override
    public void run(final V startValue) {
        // Ao criar um vértice de Cormen, sua cor já é branca e seus valores de cinza e preto
        // são infinitos. Portanto, aqui só alteraremos as propriedades do vértice de partida.
        final CormenNode<V> node = (CormenNode<V>) graph.getNode(startValue);
        node.setGrayColor(0);
        queue.push(node);

        while (!queue.isEmpty()) {
            search(queue.pop().value);
        }
    }

    @Override
    public boolean shouldVisit(final V neighbor) {
        return ((CormenNode<V>) graph.getNode(neighbor)).isWhite();
    }

    @Override
    public void onVisit(final V parentValue, final V childValue) {
        super.onVisit(parentValue, childValue);
        final CormenNode<V> parentNode = ((CormenNode<V>) graph.getNode(parentValue));
        final CormenNode<V> childNode = ((CormenNode<V>) graph.getNode(childValue));
        childNode.setGrayColor(parentNode.getGrayValue() + 1);
        childNode.setPrevious(parentNode);
        queue.push(childNode);
    }

    @Override
    public void onPostVisit(final V value) {
        super.onPostVisit(value);
        ((CormenNode<V>) graph.getNode(value)).setBlackColor();
    }
}
