/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada_assignment_4;

import java.util.Set;

/**
 *
 * @author Callum
 * @param <E>
 */
public interface GraphADT<E> {
    // enumeration of the types of graph

    public static enum GraphType {
        UNDIRECTED, DIRECTED
    };
    // returns the type of the graph

    public GraphType getType();
    // removes all vertices and edges from the graph

    public void clear();
    // returns true if the graph has no vertices nor edges

    public boolean isEmpty();
    // returns a view of the vertices as a Set

    public Set<Vertex<E>> vertexSet();
    // returns a view of the edges as a Set

    public Set<Edge<E>> edgeSet();
    // method which adds the graph as a subgraph into this graph

    public <F extends E> void addGraph(GraphADT<F> graph);
    // adds and returns a new isolated vertex to the graph

    public Vertex<E> addVertex(E element);
    // adds and returns a new undirected edge between two vertices

    public Edge<E> addEdge(Vertex<E> vertex0, Vertex<E> vertex1);
    // removes the specified vertex from the graph

    public <F> boolean removeVertex(Vertex<F> vertex);
    // removes the specified undirected edge from the graph

    public <F> boolean removeEdge(Edge<F> edge);
    // returns whether the specified vertex is in the graph

    public boolean containsVertex(Vertex<?> vertex);
    // returns whether the specified edge is in the graph

    public boolean containsEdge(Edge<?> edge);
}
