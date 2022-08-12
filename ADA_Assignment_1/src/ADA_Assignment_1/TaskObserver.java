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
public interface TaskObserver<F> {

    public void update(Task o, Object arg);

}
