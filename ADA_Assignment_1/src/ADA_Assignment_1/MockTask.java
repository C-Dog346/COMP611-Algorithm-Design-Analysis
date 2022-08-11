package ADA_Assignment_1;

import java.util.ArrayList;

/**
 *
 * @author coola
 */
public class MockTask<E, F> extends Task<E, F>{

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
            synchronized(o) {
                o.notify();
            }
        }
    }
    
    @Override
    public void run() {
        
        for (int i = 0; i < 1000; i++) {
            System.out.println("running some task");
        }
    }
    
    
}
