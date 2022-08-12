/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADA_Assignment_1;

import java.util.ArrayList;

/**
 *
 * @author Callum
 * @param <E>
 * @param <F>
 */
public abstract class Task<E, F> implements Runnable {

    private final ArrayList<TaskObserver> listeners;
    private Object param;

    public Task(E param) {
        listeners = new ArrayList<>();
        this.param = param;
    }

    public int getId() {
        return UniqueIdentifier.getCOUNTER();
    }

    public void addListener(TaskObserver<F> o) {
        listeners.add(o);
    }

    public void removeListener(TaskObserver<F> o) {
        listeners.remove(o);
    }

    protected void notifyAll(F progress) {
        for (TaskObserver o : listeners) {
            synchronized (o) {
                o.update(this, param);
            }
        }
    }
}
