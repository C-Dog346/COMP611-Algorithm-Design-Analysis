/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada_assignment_4;

import ada_assignment_4.GraphADT.GraphType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Callum extends a custom AdjacencyListGraph
 * @param <String>
 */
public class BestConversionFinder<String> extends AdjacencyListGraph<String> {

    private static final int AUD = 0; // Australian Dollar  
    private static final int EUR = 1; // Euro
    private static final int MXN = 2; // Mexican Peso
    private static final int NZD = 3; // New Zealand Dollar
    private static final int USD = 4; // United States Dollar

    private double[][] table;
    private double[][] weights;
    private HashMap<Integer, String> currencies;
    ArrayList<AdjacencyListEdge> currency1;
    ArrayList<AdjacencyListEdge> currency2;
    private HashMap<Integer, AdjacencyListVertex> currencyVertexes;

    public BestConversionFinder(double[][] table, HashMap<Integer, String> currencies) {
        super(GraphType.DIRECTED);
        this.table = table;
        this.currencies = currencies;
        currency1 = new ArrayList<>();
        currency2 = new ArrayList<>();
        currencyVertexes = new HashMap<>();
        convertToGraph();
    }

    private void convertToGraph() {
        weights = new double[table.length][table.length];

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j] != 0) {
                    weights[i][j] = Math.log(1 / table[i][j]);// / Math.log(2);
                }
                else if (i == j) {
                    weights[i][j] = 0;
                }
                else {
                    weights[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
    }

    public Edge<String> addWeightedEdge(Vertex<String> vertex0, Vertex<String> vertex1, double weight) {  // first add the end vertices if not already in graph
        if (!containsVertex(vertex0)) {
            addVertex(vertex0);
        }
        if (!containsVertex(vertex1)) {
            addVertex(vertex1);
        }
        // create the new edge
        Edge<String> edge = new WeightedAdjacencyListEdge(vertex0, vertex1, weight);
        edges.add(edge);
        // update the adjacency list for one or both end vertices
        adjacencyLists.get(vertex0).add(edge);
        if (type == GraphType.UNDIRECTED) // add the reverse edge 
        {
            adjacencyLists.get(vertex1).add(edge);
        }
        return edge;
    }

    // adds and returns a new isolated vertex to the graph
    @Override
    public Vertex<String> addVertex(String element) {
        AdjacencyListVertex vertex = new AdjacencyListVertex(element);
        addVertex(vertex);
        return vertex;
    }

    // helper method to add a Vertex object to the graph
    private void addVertex(Vertex<String> vertex) {
        vertices.add(vertex);
        adjacencyLists.put(vertex, new HashSet<>());
    }

    private class WeightedAdjacencyListEdge implements Edge<String> {

        private Vertex<String> vertex1, vertex2;
        private double weight;

        public WeightedAdjacencyListEdge(Vertex<String> vertex1, Vertex<String> vertex2,
                double weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }

        @Override
        public Vertex<String>[] endVertices() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Vertex<String> oppositeVertex(Vertex<String> vertex) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BestConversionFinder test = new BestConversionFinder(new double[][]{
            //  AUD   EUR   MXN   NZD  USD
            {1, 0.61, 0, 1.08, 0.72}, // AUD
            {1.64, 1, 0, 1.77, 1.18}, // EUR
            {0, 0, 1, 0, 0.047}, // MXN
            {0.92, 0.56, 0, 1, 0.67}, // NZD
            {1.39, 0.85, 21.19, 1.5, 1} // USD 
        });

        System.out.println(test.graph.toString());

    }

}
