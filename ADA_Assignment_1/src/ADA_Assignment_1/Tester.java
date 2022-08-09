package ADA_Assignment_1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author jack
 */
public class Tester {
    
    private int numThreads = 0;
    private ThreadPool pool;
    
    JFrame frame;
    JPanel topPanel;
    JPanel midPanel;
    JPanel botPanel;
    JLabel taskQ;
    JButton threadsButton;
    JButton tasksButton;
    JLabel threadNum;
    
    public Tester(int numThreads) {
        frame = new JFrame("Testing app");
        frame.setLayout(new BorderLayout());
        
        topPanel = new JPanel();
        midPanel = new Rectangles();
        botPanel = new JPanel();
        taskQ = new JLabel("Tasks in queue: ");
        threadNum = new JLabel("Number of threads: "+numThreads);
        threadsButton = new JButton("Make threads");
        tasksButton = new JButton("Add task");        
        
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(midPanel, BorderLayout.CENTER);
        frame.add(botPanel, BorderLayout.SOUTH);
        topPanel.add(threadsButton, BorderLayout.NORTH);
        topPanel.add(tasksButton, BorderLayout.NORTH);
        botPanel.add(taskQ);
        botPanel.add(threadNum);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        pool = new ThreadPool(numThreads);
        Thread taskDisplayThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    taskQ.setText("Tasks in queue: "+pool.getTasks());
                }
            }
            
        });
        taskDisplayThread.start();
        
        tasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pool.perform(new Task());
            }
        });
        
        
       
    }
    
    public class Rectangles extends JPanel {
        
        int width = 50;
        int height = 50;
        int offset = 0;
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            
            for (int i = 0; i < pool.getSize(); i++) {
                if (pool.getThreads()[i].getState() == Thread.State.WAITING)
                    g.setColor(Color.green);
                else
                    g.setColor(Color.red);
                g.fillRect(10+offset, 10, height, width);
                
                offset += 100;
            }
        }
    }
    
    public static void main(String[] args) {
        
        final int NumberOfThreads = 5;
        Tester t = new Tester(NumberOfThreads);
        
    }

}
