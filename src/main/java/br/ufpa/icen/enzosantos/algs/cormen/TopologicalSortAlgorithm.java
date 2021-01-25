package br.ufpa.icen.enzosantos.algs.cormen;

import br.ufpa.icen.enzosantos.Graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Representa um algoritmo DFS por Cormen et al. que suporta a ordenação topológica.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices a serem visitados.
 */
public class TopologicalSortAlgorithm<V> extends DFSAlgorithm<V> {
    private List<V> sortedValues;

    /**
     * Constrói um algoritmo DFS por Cormen et al. que ordena os vértices de um grafo.
     *
     * @param graph o grafo no qual esse algoritmo será executado.
     */
    public TopologicalSortAlgorithm(Graph<V> graph) {
        super(graph);
    }

    @Override
    public void run(V startValue) {
        sortedValues = new LinkedList<>();
        super.run(startValue);
    }

    @Override
    public void onPostVisit(V value) {
        super.onPostVisit(value);
        sortedValues.add(value);
    }

    /**
     * @return os vértices ordenados do grafo desse algoritmo.
     * @throws IllegalStateException se essa propriedade for chamada antes do algoritmo ser executado.
     */
    public List<V> getSortedValues() {
        if (sortedValues == null) {
            throw new IllegalStateException("`run` deve ser chamado antes de obter os vértices ordenados");
        }

        return sortedValues;
    }
}
