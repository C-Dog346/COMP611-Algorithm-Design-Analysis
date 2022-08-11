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
    JTextField threadinput;
    
    public Tester(int numThreads) {
        this.numThreads = numThreads;
        frame = new JFrame("Testing app");
        frame.setLayout(new BorderLayout());
        
        topPanel = new JPanel();
        midPanel = new Rectangles();
        botPanel = new JPanel();
        taskQ = new JLabel("Tasks in queue: " + (pool == null ? "0" : pool.getTasks()));
        threadNum = new JLabel("Number of threads: 0");
        threadsButton = new JButton("Make threads");
        tasksButton = new JButton("Add task");    
        threadinput = new JTextField("Enter number of threads");
        
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(midPanel, BorderLayout.CENTER);
        frame.add(botPanel, BorderLayout.SOUTH);
        topPanel.add(threadinput, BorderLayout.NORTH);
        topPanel.add(threadsButton, BorderLayout.NORTH);
        topPanel.add(tasksButton, BorderLayout.NORTH);
        botPanel.add(taskQ);
        botPanel.add(threadNum);
        midPanel.setSize(600, 600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(615, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
  
        threadsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                threadinput.setEditable(false);
                String text = threadinput.getText();
                int num = 0;
                if (text.matches("[0-9]+")) {
                    num = Integer.parseInt(text);
                    pool = new ThreadPool(num);
                    threadsButton.setEnabled(false);
                    System.out.println(pool.getSize());
                }
                else {
                    
                    JFrame popup = new JFrame();
                    popup.add(new JLabel("Enter a single positive integer greater than 0 only."), BorderLayout.NORTH);
                    popup.setVisible(true);
                    popup.setAlwaysOnTop(true);
                    popup.setSize(300,300);
                    popup.setLocationRelativeTo(null);
                    threadinput.setEditable(true);
                }
                
                threadNum.setText("Number of threads: " + num);
            }
        });
        
        Thread taskDisplayThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    taskQ.setText("Tasks in queue: " + (pool == null ? "0" : pool.getTasks()));
                }
            }

        });
        taskDisplayThread.start();

        tasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pool.perform(new MockTask<String, String>("TEST"));
                taskQ.setText("Tasks in queue: " + (pool == null ? "0" : pool.getTasks()));
            }
        });
        
        
       
    }
    
    public class Rectangles extends JPanel {
        
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            if (pool != null) {            
                
                final int WIDTH = 60;
                final int HEIGHT = 60;
                final int iLimit = pool.getSize();

                super.paintComponent(g);
                int x = 0;
                int y = 0;

                for (int i = 0; i < iLimit; i++) {
                    if (pool.getThreads()[i].getState() == Thread.State.WAITING) {
                        g.setColor(Color.green);
                    } else {
                        g.setColor(Color.red);
                    }
                    if (x >= 10 ) {
                        y++;
                        x = 0;
                    }
                    
                    g.fillRect(0 + x * WIDTH, 0 + y * HEIGHT, WIDTH, HEIGHT);
                    g.setColor(Color.black);
                    g.drawRect(0 + x * WIDTH, 0 + y * HEIGHT, WIDTH, HEIGHT);
                    x++;
                }                    
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        final int NumberOfThreads = 5;
        Tester t = new Tester(NumberOfThreads);
        
        while (true) {
            t.frame.repaint();
            Thread.sleep(10);
        }
        
    }

}
