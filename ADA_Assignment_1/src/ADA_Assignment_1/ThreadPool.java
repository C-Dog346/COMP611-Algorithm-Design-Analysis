package ADA_Assignment_1;

import java.util.LinkedList;

/**
 *
 * @author 20112529 Jack Briggs
 */
public class ThreadPool {
    
    static final int INITIAL_CAPACITY = 10;
    static final double LOAD_FACTOR = 0.70f;
    
    // Data structures for the task queue and threads
    private LinkedList taskQueue;
    private Thread[] threads;
    // Array for holding if a thread is performing a task or not;
    private boolean[] performing;
    
    // Create a new ThreadPool with a default size of INITIAL_CAPACITY;
    public ThreadPool() {
        taskQueue = new LinkedList();
        threads = new Thread[INITIAL_CAPACITY];
    }
    
    // Create a new ThreadPool with a customizable default size;
    public ThreadPool(int initialCapacity) {
        taskQueue = new LinkedList();
        threads = new Thread[initialCapacity];
    }
        
    public int getSize() {
        return threads.length;
    }
    
    // Checks the performing array for threads running tasks
    public int getAvaliable() {
        int count = 0;
        for (int i = 0; i < threads.length; i++) {
            if (performing[i])
                count++;
        }
        return count;
    }
    
    // Checks the performing 
    public void destroyPool() throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            if (performing[i]) {
                threads[i].join();
                performing[i] = false;
            }
        }
    }
}
