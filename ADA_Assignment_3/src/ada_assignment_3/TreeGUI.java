/*
    Callum Geoffrey Clow, 00000000
    Caitlyn Young, 20109380
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

    public void StartGUI(int version) {
        JFrame frame = new JFrame("Version: " + version);
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
}
