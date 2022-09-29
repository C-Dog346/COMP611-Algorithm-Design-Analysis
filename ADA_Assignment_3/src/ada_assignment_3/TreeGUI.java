/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada_assignment_3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Cat and Macx
 * 
 */


public class TreeGUI extends JPanel implements ActionListener {

    protected BinarySearchTree tree;

    private final Timer timer;
    private final DrawPanel drawPanel;

    public TreeGUI(BinarySearchTree tree) {
        super(new BorderLayout());

        this.tree = tree;

        //time between GUI updates (ms)
        this.timer = new Timer(30, this);
        this.timer.start();

        this.drawPanel = new DrawPanel();
        super.add(this.drawPanel, BorderLayout.CENTER);
    }

    public void StartGUI() {
        JFrame frame = new JFrame("TREEEEEEEEEEESSS");
        frame.setLocation(20, 20);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == this.timer) {
            this.drawPanel.repaint();
        }
    }

//inner class
    private class DrawPanel extends JPanel {

        public DrawPanel() {
            super.setPreferredSize(new Dimension(1000, 600));
            super.setBackground(Color.WHITE);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Monospaced", Font.PLAIN, 18));

            tree.draw(g);
        }
    }

    public static void main(String[] args) {
        //build tree
        BinarySearchTree<Integer> testTree = new BinarySearchTree();
        testTree.add(100);
        testTree.add(50);
        testTree.add(25);
        testTree.add(75);
        testTree.add(80);
        testTree.add(200);
        testTree.add(150);
        testTree.add(125);
        testTree.add(112);
        testTree.add(175);

        //max level tree (for GUI)
        BinarySearchTree<Integer> maxTree = new BinarySearchTree();
        maxTree.add(100);
        maxTree.add(0);
        maxTree.add(200);
        maxTree.add(-100);
        maxTree.add(50);
        maxTree.add(150);
        maxTree.add(300);
        maxTree.add(-200);
        maxTree.add(-50);
        maxTree.add(25);
        maxTree.add(75);
        maxTree.add(350);
        maxTree.add(125);
        maxTree.add(175);
        maxTree.add(250);
        //node crossover (>4 levels)
//        maxTree.add(190);
//        maxTree.add(225);

        //build GUI
        TreeGUI test = new TreeGUI(testTree);
//        TreeGUI test = new TreeGUI(maxTree);
        test.StartGUI();
    }
}

