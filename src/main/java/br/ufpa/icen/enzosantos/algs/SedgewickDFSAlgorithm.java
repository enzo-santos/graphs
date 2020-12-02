package br.ufpa.icen.enzosantos.algs;

import br.ufpa.icen.enzosantos.Graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Representa um algoritmo Depth-First-Search por Segdewick.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices a serem visitados.
 */
public class SedgewickDFSAlgorithm<V> extends NodeVisitorAlgorithm<V> {
    /**
     * Monitora os vértices que já foram visitados.
     */
    private final Map<V, Boolean> visited = new HashMap<>();

    /**
     * O contador desse algoritmo.
     */
    private Integer count = null;

    /**
     * Inicializa um algoritmo DFS por Sedgewick baseado em um grafo.
     *
     * @param graph o grafo no qual o algoritmo será aplicado.
     */
    public SedgewickDFSAlgorithm(final Graph<V> graph) {
        super(graph);
    }

    @Override
    public void run(final V startValue) {
        count = 0;
        search(startValue);
    }

    @Override
    public void onPreVisit(final V value) {
        visited.put(value, true);
        ++count;
    }

    @Override
    public void onVisit(V parentValue, V childValue) {
        search(childValue);
    }

    @Override
    public boolean shouldVisit(final V neighbor) {
        if (!super.shouldVisit(neighbor)) {
            return false;
        }

        return !visited.getOrDefault(neighbor, false);
    }

    /**
     * @return o contador desse algoritmo.
     * @throws IllegalStateException se essa propriedade for chamada antes do algoritmo ser executado.
     */
    public int getCount() {
        if (count == null) {
            throw new IllegalStateException("`run` deve ser chamado antes de obter o contador do grafo");
        }

        return count;
    }
}
