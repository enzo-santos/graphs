package br.ufpa.icen.enzosantos.algs;

import br.ufpa.icen.enzosantos.Graph;
import br.ufpa.icen.enzosantos.TransposedGraph;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Representa um algoritmo que encontra os componentes conectados de um grafo.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices desse grafo.
 */
public class CormenConnectedComponentsFinder<V> implements Algorithm<V> {
    private final Graph<V> graph;

    /**
     * Constrói um algoritmo de encontrar componentes conectados.
     *
     * @param graph o grafo no qual esse algoritmo será executado.
     */
    public CormenConnectedComponentsFinder(final Graph<V> graph) {
        this.graph = graph;
    }

    private List<Set<V>> connectedComponents;

    @Override
    public void run(V startValue) {
        // Executa DFS(G) para calcular os tempos finais de cada vértice no grafo
        final CormenDFSAlgorithm<V> dfsAlgorithm = new CormenDFSAlgorithm<>(graph);
        dfsAlgorithm.run(startValue);
        final Graph<V> dfsGraph = dfsAlgorithm.getGraph();

        // Calcula G^T (transposta do grafo)
        final Graph<V> transposedGraph = new TransposedGraph<>(dfsGraph);

        // Executa DFS(G^T) considerando os vértices em ordem decrescente por tempos finais do grafo original
        final CormenConnectedComponentsAlgorithm<V> ccAlgorithm = new CormenConnectedComponentsAlgorithm<>(transposedGraph);
        final V higherBlackValue = dfsGraph.getValues().stream()
            .map(v -> ((CormenNode<V>) dfsGraph.getNode(v)))
            .max(Comparator.comparingInt(CormenNode::getBlackValue))
            .orElseThrow().value;
        ccAlgorithm.run(higherBlackValue);

        connectedComponents = ccAlgorithm.getConnectedComponents();
    }

    /**
     * @return os componentes conectados do grafo desse algoritmo.
     * @throws IllegalStateException se essa propriedade for chamada antes do algoritmo ser executado.
     */
    public List<Set<V>> getConnectedComponents() {
        if (connectedComponents == null) {
            throw new IllegalStateException("`run` deve ser chamado antes de obter os componentes conectados");
        }

        return connectedComponents;
    }
}
