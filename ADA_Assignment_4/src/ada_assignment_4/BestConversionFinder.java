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
public class BestConversionFinder<E> extends AdjacencyListGraph<String> {

    private static final int AUD = 0; // Australian Dollar  
    private static final int EUR = 1; // Euro
    private static final int MXN = 2; // Mexican Peso
    private static final int NZD = 3; // New Zealand Dollar
    private static final int USD = 4; // United States Dollar

    private double[][] table;
    private double[][] weights;

    public BestConversionFinder(double[][] table, HashMap<Integer, String> currencies) {
        super(GraphType.DIRECTED);
        this.table = table;
        convertToWeights();
        makeToGraph(currencies);
    }

    private void convertToWeights() {
        weights = new double[table.length][table.length];

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j] != 0) {
                    weights[i][j] = Math.log(1 / table[i][j]);
                }
                else {
                    weights[i][j] = 0;
                }
            }
        }
    }

    private boolean makeToGraph(HashMap<Integer, String> currencies) {
        for (int i = 0; i < table.length; i++) {
            if (table.length != table[i].length) {
                return false;
            }
        }

        ArrayList<Vertex<String>> list = new ArrayList();

        for (int i = 0; i < table.length; i++) {
            String currency = currencies.get(i);
            list.add(super.addVertex(currency));
        }

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j] != 0 && i != j) {
                    super.addEdge(list.get(i), list.get(j), weights[i][j]);
                }
            }
        }

        System.out.println(toString());

        return true;
    }

    public void bellmanFord(Vertex<String> source) {

        HashMap<Vertex<String>, Double> distances = new HashMap<>();
        HashMap<Vertex<String>, Edge<E>> leastEdge = new HashMap<>();
        
        for (Vertex<String> v : super.vertices) {
            distances.put(v, Double.MAX_VALUE);
        }

        HashSet set = (HashSet) super.edges;
        
        // Step 2: Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at-most |V| - 1 edges
        for (int i = 1; i < super.vertices.size(); ++i) {
            for (int j = 0; j < E; ++j) {
                int u = set.g;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (distances.get(v) != Double.MAX_VALUE
                        && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // Step 3: check for negative-weight cycles. The
        // above step guarantees shortest distances if graph
        // doesn't contain negative weight cycle. If we get
        // a shorter path, then there is a cycle.
        for (int j = 0; j < E; ++j) {
            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            int weight = graph.edge[j].weight;
            if (dist[u] != Integer.MAX_VALUE
                    && dist[u] + weight < dist[v]) {
                System.out.println(
                        "Graph contains negative weight cycle");
                return;
            }
        }
    }

    public static void main(String[] args) {
        HashMap<Integer, String> currencies = new HashMap<>();
        currencies.put(0, "AUD");
        currencies.put(1, "EUR");
        currencies.put(2, "MXN");
        currencies.put(3, "NZD");
        currencies.put(4, "USD");

        BestConversionFinder<String> test = new BestConversionFinder(new double[][]{
            //AUD EUR  MXN NZD   USD
            {1, 0.61, 0, 1.08, 0.72}, // AUD
            {1.64, 1, 0, 1.77, 1.18}, // EUR
            {0, 0, 1, 0, 0.047}, // MXN
            {0.92, 0.56, 0, 1, 0.67}, // NZD
            {1.39, 0.85, 21.19, 1.5, 1} // USD 
        }, currencies);

        //System.out.println(test);
    }
}
