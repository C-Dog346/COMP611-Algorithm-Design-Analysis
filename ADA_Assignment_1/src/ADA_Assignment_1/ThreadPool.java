package ADA_Assignment_1;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20112529 Jack Briggs
 */
public class ThreadPool {
    
    static final int INITIAL_CAPACITY = 10;
    private boolean destroyPool = false;
    
    // Data structures for the task queue and threads
    private LinkedList<Task> taskQueue;
    private Thread[] threads;
    
    // Create a new ThreadPool with a default size of INITIAL_CAPACITY;
    public ThreadPool() {
        taskQueue = new LinkedList();
        threads = new Thread[INITIAL_CAPACITY];
        createThreads();
    }
    
    // Create a new ThreadPool with a customizable default size;
    public ThreadPool(int initialCapacity) {
        taskQueue = new LinkedList();
        threads = new Thread[initialCapacity];
        createThreads();
    }
    
    public void createThreads() {
        for (int i = 0; i < threads.length; i++) {
            
            threads[i] = new Thread(new Runnable() {                
                               
                @Override
                public void run() {
                    
                    while (!destroyPool) {
                        
                        // Threads 'waiting' in the pool for a task
                        try {
                            synchronized (taskQueue) {
                                if (taskQueue.isEmpty())
                                    taskQueue.wait();
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ThreadPool.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        // Threads begin performing a task once notify has been
                        // called on them by perform()
                        boolean hasTask = false;
                        Task task = null;
                        synchronized(taskQueue) {
                            if (!taskQueue.isEmpty()) {
                                task = taskQueue.poll();
                                hasTask = true;
                            }
                        }
                        if (hasTask) {
                            task.run();                            
                        }
                    }
                }                
            }
            );   
            
            threads[i].start();
            
        }
    }
        
    public int getSize() {
        int count = 0;
        //synchronized (threads)
        for (Thread t : threads) {
            count++;
        }           
        return count;
    }
    
    // Checks the performing array for threads running tasks
    public int getAvaliable() {
        int count = 0;
        for (int i = 0; i < threads.length; i++) {
            if (threads[i].getState() == Thread.State.WAITING);
                count++;
        }
        return count;
    }
    
    // Checks the performing 
    public void destroyPool() throws InterruptedException {
        
        // Main thread will spin until all tasks have been completed
        while (!taskQueue.isEmpty()) {;}
        
        // Synchronize on the queue to make sure no tasks get added during
        // pool destruction
        synchronized (taskQueue) {
            destroyPool = true;
            taskQueue.notifyAll();
        }
    }
    
    public boolean perform(Task task) {
        boolean success = false;
        synchronized(taskQueue) {
            if (!destroyPool) {
                taskQueue.add(task); 
                taskQueue.notifyAll();
                success =  true;
            }
        }
     
        return success;
    }
    
    public void resize(int newSize) throws InterruptedException {
        destroyPool();
        threads = new Thread[newSize];
        createThreads();
    }
    
    public int getTasks() {
        return taskQueue.size();
    }
    
    protected Thread[] getThreads() {
        return threads;
    }
}


