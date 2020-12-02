package br.ufpa.icen.enzosantos.algs;

import br.ufpa.icen.enzosantos.Graph;

import java.util.Optional;

/**
 * Representa um algoritmo Depth-First-Search por Cormen et. al.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices a serem visitados.
 */
public class CormenDFSAlgorithm<V> extends NodeVisitorAlgorithm<V> {
    /**
     * O tempo atual no contexto do algoritmo.
     */
    private int time = 0;

    /**
     * Constrói um algoritmo DFS por Cormen et. al. baseado em um grafo.
     *
     * @param graph o grafo no qual esse algoritmo será aplicado.
     */
    public CormenDFSAlgorithm(final Graph<V> graph) {
        super(CormenGraph.transform(graph));
    }

    /**
     * @return o grafo no qual esse algoritmo será aplicado.
     */
    public Graph<V> getGraph() {
        return graph;
    }

    @Override
    public void run(final V startValue) {
        time = 0;
        search(startValue);

        for (; ; ) {
            final Optional<CormenNode<V>> optionalNode = graph.getValues().stream()
                .sorted()
                .map(v -> ((CormenNode<V>) graph.getNode(v)))
                .filter(CormenNode::isWhite)
                .findFirst();

            if (optionalNode.isPresent()) {
                search(optionalNode.get().value);
                continue;
            }

            return;
        }
    }

    @Override
    public boolean shouldVisit(final V neighbor) {
        return ((CormenNode<V>) graph.getNode(neighbor)).isWhite();
    }

    @Override
    public void onPreVisit(final V value) {
        final CormenNode<V> cormenNode = (CormenNode<V>) graph.getNode(value);
        cormenNode.setGrayColor(++time);
    }

    @Override
    public void onVisit(final V parentValue, final V childValue) {
        super.onVisit(parentValue, childValue);
        final CormenNode<V> parentNode = (CormenNode<V>) graph.getNode(parentValue);
        final CormenNode<V> childNode = (CormenNode<V>) graph.getNode(childValue);
        childNode.setPrevious(parentNode);
        search(childValue);
    }

    @Override
    public void onPostVisit(final V value) {
        super.onPostVisit(value);
        final CormenNode<V> cormenNode = (CormenNode<V>) graph.getNode(value);
        cormenNode.setBlackColor(++time);
    }
}
