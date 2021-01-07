# Graphs

Implementação de alguns algoritmos de teoria de grafos.


[![Build Status](https://travis-ci.org/enzo-santos/graphs.svg?branch=master)](https://travis-ci.org/enzo-santos/graphs) [![codecov](https://codecov.io/gh/enzo-santos/graphs/branch/master/graph/badge.svg?token=QARUGJ0L2M)](https://codecov.io/gh/enzo-santos/graphs)


## Implementações

### Grafos

- [x] Grafo orientado

- [x] Grafo não orientado

- [x] Grafo por matriz de adjacências

- [x] Grafo por lista de adjacências

- [x] Grafo transposto

### Algoritmos de busca

 - [x] Breadth-first search (Cormen et al.)
 
 - [x] Depth-first search (Cormen et al.)
 
 - [x] Depth-first search (Sedgewick)
 
 - [x] Strongly-connected-components search (Cormen et al.)
 
## Uso

Para criar um grafo, é possível utilizar as classes `ListGraph` ou `MatrixGraph`.

    final AbstractGraph<String> abstractGraph = new ListGraph<>(Set.of("a", "b", "c", "d"));
   
Para inserir arestas nesse grafo, podem-se usar as classes `DirectedGraph` ou `UndirectedGraph`.

    final Graph<String> graph = new DirectedGraph<>(abstractGraph);
    
Para inserir uma aresta, utiliza-se o método `addEdge`.

    graph.addEdge("a", "b");
    graph.addEdge("a", "c");
    graph.addEdge("b", "c", 2);
    graph.addEdge("c", "d");
    
Após a inserção de arestas, pode-se conferir propriedades dos grafos, como vértices e arestas, ou calcular vizinhos de determinado vértice.

    graph.getValues();          // ["a", "b", "c", "d"]
    graph.getEdges();           // [Edge("a", "b", 1), Edge("a", "c", 1), Edge("b", "c", 2), Edge("c", "d", 1)]
    graph.getNeighbors("a");    // {"b": 1, "c": 1}
    graph.getNeighbors("b");    // {"c": 2}

Os exemplos de utilização dos algoritmos de busca estão no diretório **src/test/java**.
