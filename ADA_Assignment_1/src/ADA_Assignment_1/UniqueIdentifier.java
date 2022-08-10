/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADA_Assignment_1;

/**
 *
 * @author Callum
 */
public class UniqueIdentifier {

    private int counter;
    private static UniqueIdentifier instance = new UniqueIdentifier();

    
    private UniqueIdentifier() {
        counter = 0;
    }

    /**
     * @return the COUNTER
     */
    
    public static UniqueIdentifier getInstance() {
        return instance;
    }
    
    public int getCounter() {
        return ++counter;
    }
}
