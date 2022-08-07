/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADA_Assignment_1;

/**
 *
 * @author Callum
 * @param <E>
 * @param <F>
 */
public abstract class Task<E,F> implements Runnable {

    public Task(E param) {
    }
    
    public int getId() {
        return 0;
    }
    
    @Override
    public void run() {
    }
    
    public void addListener(TaskObserver<F> o) {
        
    }
    
    public void removeListener(TaskObserver<F> o) {
        
    }
    
    protected void notifyAll(F progress) {
        
    }
}
