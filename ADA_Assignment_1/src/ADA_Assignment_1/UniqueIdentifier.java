/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADA_Assignment_1;

import java.util.UUID;

/**
 *
 * @author Callum
 */
public class UniqueIdentifier {

    private static UniqueIdentifier instance = new UniqueIdentifier();

    private UniqueIdentifier() {
    }

    public static UniqueIdentifier getInstance() {
        return instance;
    }

    /**
     * @return the COUNTER
     */
    protected static String getId() {
        return UUID.randomUUID().toString();
    }
}
