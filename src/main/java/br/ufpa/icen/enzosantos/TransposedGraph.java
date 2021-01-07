package br.ufpa.icen.enzosantos;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Representa um grafo que transpõe o grafo que está sendo estendido.
 * <p>
 * Ao adicionar uma nova aresta nesse grafo, ela não será transposta.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices desse grafo.
 */
public class TransposedGraph<V> extends Graph<V> {
    public TransposedGraph(AbstractGraph<V> graph) {
        super(graph);
    }

    @Override
    public boolean addEdge(V fromValue, V toValue, int weight) {
        return onEdgeAdded(toValue, fromValue, weight);
    }

    @Override
    public Graph<V> copyDirection(AbstractGraph<V> to) {
        return to == null ? null : new TransposedGraph<>(to);
    }

    @Override
    public List<Edge<V>> getEdges() {
        return super.getEdges().stream()
            .map(edge -> new Edge<>(edge.toValue, edge.fromValue, edge.weight))
            .collect(Collectors.toList());
    }

    @Override
    public Map<V, Integer> getNeighbors(V value) {
        return super.getValues().stream()
            // Mapeia cada valor aos seus vizinhos originais
            .map(v -> Map.entry(v, super.getNeighbors(v)))
            // Filtra os vizinhos que possuem o valor [value]
            .filter(entry -> entry.getValue().get(value) != null)
            // Adiciona um novo vizinho a ser retornado com o peso do seu vizinho antigo
            .map(entry -> Map.entry(entry.getKey(), entry.getValue().get(value)))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
