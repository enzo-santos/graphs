package br.ufpa.icen.enzosantos.algs.cormen;

import br.ufpa.icen.enzosantos.Graph;

import java.util.*;

/**
 * Representa um algoritmo DFS por Cormen et al. que suporta a descoberta de componentes conectados.
 * <p>
 * Um novo grafo será criado para a DFS. Ao percorrer cada vértice do novo grafo,
 * serão considerados os tempos finais de forma decrescente dos vértices do grafo original.
 *
 * @param <V> o tipo do conteúdo a ser armazenado nos vértices a serem visitados.
 */
class ConnectedComponentsAlgorithm<V> extends DFSAlgorithm<V> {
    private LinkedList<Set<V>> connectedComponents = new LinkedList<>();

    private final Graph<V> originalGraph;

    /**
     * Constrói um algoritmo DFS por Cormen et al. que encontra componentes conectados.
     *
     * @param graph o grafo no qual esse algoritmo será executado. Os vértices desse grafo devem
     *              ser do tipo {@link CormenNode}, visto que é preciso conhecer os tempos finais
     *              de cada vértice para percorrê-los.
     */
    public ConnectedComponentsAlgorithm(final Graph<V> graph) {
        super(graph);
        originalGraph = graph;
    }

    /**
     * Cria um novo componente conectado com um valor inicial.
     *
     * @param startValue o valor inicial a ser adicionado nesse componente.
     */
    private void createComponentsWith(final V startValue) {
        final Set<V> components = new HashSet<>();
        components.add(startValue);
        connectedComponents.add(components);
    }

    /**
     * Adiciona um valor ao último componente conectado adicionado.
     *
     * @param value o valor a ser adicionado.
     */
    private void addToLastAddedComponents(final V value) {
        final Set<V> components = connectedComponents.getLast();
        components.add(value);
    }

    /**
     * @return os componentes conectados do grafo desse algoritmo.
     * <p>
     * Caso essa propriedade seja chamada antes do algoritmo ser executado, é
     * retornada uma lista vazia.
     */
    public List<Set<V>> getConnectedComponents() {
        return connectedComponents;
    }

    @Override
    public void onVisit(V parentValue, V childValue) {
        super.onVisit(parentValue, childValue);
        addToLastAddedComponents(childValue);
    }

    @Override
    public void run(final V startValue) {
        time = 0;
        connectedComponents = new LinkedList<>();

        createComponentsWith(startValue);
        search(startValue);

        for (; ; ) {
            final Optional<CormenNode<V>> optionalNode = graph.getValues().stream()
                // Considera os vértices em ordem decrescente por tempos finais do grafo original
                .sorted((v0, v1) -> {
                    final CormenNode<V> n0 = (CormenNode<V>) originalGraph.getNode(v0);
                    final CormenNode<V> n1 = (CormenNode<V>) originalGraph.getNode(v1);
                    return Integer.compare(n1.getBlackValue(), n0.getBlackValue());
                })
                .map(v -> ((CormenNode<V>) graph.getNode(v)))
                .filter(CormenNode::isWhite)
                .findFirst();

            if (optionalNode.isPresent()) {
                final V value = optionalNode.get().value;
                createComponentsWith(value);
                search(value);
                continue;
            }

            return;
        }
    }
}
