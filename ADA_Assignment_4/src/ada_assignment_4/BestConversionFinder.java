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
        convertToWeights();
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
    
    

    public static void main(String[] args) {
        HashMap<Integer, String> currencies = new HashMap<>();
        currencies.put(0, "NZD");
        currencies.put(1, "EUR");
        currencies.put(2, "AUD");
        currencies.put(3, "USD");
        currencies.put(4, "MXN");

        BestConversionFinder<String> test = new BestConversionFinder(new double[][]{
            //  AUD   EUR   MXN   NZD  USD
            {1, 0.61, 0, 1.08, 0.72}, // AUD
            {1.64, 1, 0, 1.77, 1.18}, // EUR
            {0, 0, 1, 0, 0.047}, // MXN
            {0.92, 0.56, 0, 1, 0.67}, // NZD
            {1.39, 0.85, 21.19, 1.5, 1} // USD 
        }, currencies);
        test
    }
}
