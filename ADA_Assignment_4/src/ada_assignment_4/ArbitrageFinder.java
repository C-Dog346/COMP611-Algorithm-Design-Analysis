/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada_assignment_4;

import ada_assignment_4.GraphADT.GraphType;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Callum extends a custom AdjacencyListGraph
 * @param <E>
 */
public class ArbitrageFinder<E> extends AdjacencyListGraph<String> {

    private static final int AUD = 0; // Australian Dollar  
    private static final int EUR = 1; // Euro
    private static final int MXN = 2; // Mexican Peso
    private static final int NZD = 3; // New Zealand Dollar
    private static final int USD = 4; // United States Dollar

    private final double INFINITE = Double.MAX_VALUE;

    private double[][] table;
    private double[][] weights;

    public ArbitrageFinder(double[][] table, HashMap<Integer, String> currencies) {
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
        for (double[] table1 : table) {
            if (table.length != table1.length) {
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

    public boolean bellmanFord(Vertex<String> source, Vertex<String> end) {

        HashMap<Vertex<String>, Double> distances = new HashMap<>();
        HashMap<Vertex<String>, Edge<String>> leastEdge = new HashMap<>();

        for (Vertex<String> v : super.vertices) {
            distances.put(v, INFINITE);
            leastEdge.put(v, null);
        }
        distances.put(source, 0.0);

        for (Vertex<String> v : super.vertices) {
            for (Edge<String> e : super.edges) {
                Vertex<String> src = e.endVertices()[0];
                Vertex<String> dst = e.endVertices()[1];
                if (distances.get(src) != INFINITE && distances.get(src)
                        + e.getWeight() < distances.get(dst)) {
                    distances.put(dst, distances.get(src) + e.getWeight());
                    leastEdge.put(dst, e);
                }
            }
        }

        for (Edge<String> e : super.edges) {
            Vertex<String> src = e.endVertices()[0];
            Vertex<String> dst = e.endVertices()[1];
            if (distances.get(src) != INFINITE && distances.get(src)
                    + e.getWeight() < distances.get(dst)) {
                System.out.println("There is arbitrage within this graph");
                return true;
            }
        }
        System.out.println("There is no arbitrage within this graph");
        return false;
    }

    public static void main(String[] args) {
        HashMap<Integer, String> currencies = new HashMap<>();
        currencies.put(0, "AUD");
        currencies.put(1, "EUR");
        currencies.put(2, "MXN");
        currencies.put(3, "NZD");
        currencies.put(4, "USD");

//        ArbitrageFinder<String> testFail = new ArbitrageFinder(new double[][]{
//            //AUD EUR  MXN NZD   USD
//            {1, 0.61, 0, 1.08, 0.72}, // AUD
//            {1.64, 1, 0, 1.77, 1.18}, // EUR
//            {0, 0, 1, 0, 0.047}, // MXN
//            {0.92, 0.56, 0, 1, 0.67}, // NZD
//            {1.39, 0.85, 21.19, 1.5, 1} // USD 
//        }, currencies);
//
//        ArrayList<Vertex<String>> failList = new ArrayList<>();
//
//        for (Vertex<String> v : testFail.vertices) {
//            failList.add(v);
//        }
//
//        testFail.bellmanFord(failList.get(0), failList.get(3));
        ArbitrageFinder<String> testPass = new ArbitrageFinder(new double[][]{
            {1, 0, 0.4, 0.4},
            {0, 1, 1.6, 1.5},
            {0, 0, 1, 1.1},
            {1.3, 0, 0.4, 1}},
                currencies);

        ArrayList<Vertex<String>> passList = new ArrayList<>();

        for (Vertex<String> v : testPass.vertices) {
            passList.add(v);
        }

        testPass.bellmanFord(passList.get(2), passList.get(3));

    }
}
