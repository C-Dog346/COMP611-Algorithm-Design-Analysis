package ADA_Assignment_1;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coola
 */
public class MockTask<E, F> extends Task<E, F> {

    private final ArrayList<TaskObserver> listeners;

    public MockTask(E param) {
        super(param);
        listeners = new ArrayList<>();
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
                o.notify();
            }
        }
    }

    @Override
    public void run() {        
        System.out.println("Running task -> sleeping");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MockTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        
}
