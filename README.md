# Graphs

Implementação de alguns algoritmos de teoria de grafos.

## Implementações

### Grafos

- [x] Grafo orientado

- [x] Grafo não orientado

- [x] Grafo por matriz de adjacências

- [x] Grafo por lista de adjacências

### Algoritmos de busca

 - [x] Breadth-first search (Cormen et al.)
 
 - [x] Depth-first search (Cormen et al.)
 
 - [x] Depth-first search (Sedgewick)
 
## Uso

Para criar um grafo, é possível utilizar as classes `ListGraph` ou `MatrixGraph`.

    final AbstractGraph<String> abstractGraph = new ListGraph<>(Set.of("a", "b", "c", "d"));
   
Para inserir arestas nesse grafo, podem-se usar as classes `DirectedGraph` ou `UndirectedGraph`.

    final Graph<String> graph = new UndirectedGraph<>(abstractGraph);
    
Para inserir uma aresta, utiliza-se o método `addEdge`.

    graph.addEdge("a", "b");
    graph.addEdge("a", "c");
    graph.addEdge("b", "c", 2);
    graph.addEdge("c", "d");
    
Após a inserção de arestas, pode-se conferir propriedades dos grafos, como vértices e arestas, ou calcular vizinhos de determinado vértice.

    graph.getValues();          // ["a", "b", "c", "d"]
    graph.getEdges();           // [Edge("a", "b", 1), Edge("a", "c", 1), Edge("b", "c", 2), Edge("c", "d", 1)]
    graph.getNeighbors("b");    // {"a": 1, "c": 2}

Os exemplos de utilização dos algoritmos de busca estão no diretório **/src/test/java**.