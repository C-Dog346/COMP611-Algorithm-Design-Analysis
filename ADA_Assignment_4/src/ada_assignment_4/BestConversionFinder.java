/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada_assignment_4;

/**
 *
 * @author Callum
 */
public class BestConversionFinder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AdjacencyListGraph<Integer> testGraph = new AdjacencyListGraph();
        Vertex AUD = testGraph.addVertex(1);
        Vertex EUR = testGraph.addVertex(2);
        Vertex MXN = testGraph.addVertex(3);
        Vertex NZD = testGraph.addVertex(4);
        Vertex USD = testGraph.addVertex(5);
        
        testGraph.addEdge(AUD, USD);
        testGraph.addEdge(AUD, NZD);
        testGraph.addEdge(AUD, EUR);
        testGraph.addEdge(USD, AUD);
        testGraph.addEdge(USD, NZD);
        testGraph.addEdge(USD, EUR);
        testGraph.addEdge(USD, MXN);
        testGraph.addEdge(MXN, USD);
        testGraph.addEdge(EUR, AUD);
        testGraph.addEdge(EUR, NZD);
        testGraph.addEdge(EUR, USD);
        testGraph.addEdge(NZD, USD);
        testGraph.addEdge(NZD, EUR);
        testGraph.addEdge(NZD, AUD);
        
        System.out.println(testGraph.toString());
        
    }

}
