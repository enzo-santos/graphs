package br.ufpa.icen.enzosantos.algs.cormen;

import br.ufpa.icen.enzosantos.Edge;
import br.ufpa.icen.enzosantos.Graph;
import br.ufpa.icen.enzosantos.algs.Algorithm;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Representa um algoritmo de Kruskal por Cormen et al.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices a serem visitados.
 */
public class KruskalAlgorithm<V> implements Algorithm<V> {
    private final Graph<V> graph;

    private Set<Edge<V>> edges;

    /**
     * Constrói um algoritmo de Kruskal por Cormen et al. baseado em um grafo.
     *
     * @param graph o grafo no qual esse algoritmo será aplicado.
     */
    public KruskalAlgorithm(Graph<V> graph) {
        this.graph = graph;
    }

    @Override
    public void run(V startValue) {
        final Set<Edge<V>> edges = new HashSet<>();
        final List<Set<V>> sets = graph.getValues().stream().map(e1 -> {
            final Set<V> set = new HashSet<>();
            set.add(e1);
            return set;
        }).collect(Collectors.toList());

        graph.getEdges()
            .stream().sorted(Comparator.comparingInt(edge -> edge.weight))
            .forEach(edge -> {
                final Set<V> fromSet = sets.stream().filter(set -> set.contains(edge.fromValue)).findFirst().orElseThrow();
                final Set<V> toSet = sets.stream().filter(set -> set.contains(edge.toValue)).findFirst().orElseThrow();
                if (fromSet != toSet) {
                    edges.add(edge);

                    fromSet.addAll(toSet);
                    sets.remove(toSet);
                }
            });

        this.edges = edges;
    }

    /**
     * @return os vértices encontrados por esse algoritmo.
     * @throws IllegalStateException se essa propriedade for chamada antes do algoritmo ser executado.
     */
    public Set<Edge<V>> getEdges() {
        if (edges == null) {
            throw new IllegalStateException("`run` deve ser chamado antes de obter as arestas do algoritmo");
        }

        return edges;
    }
}
