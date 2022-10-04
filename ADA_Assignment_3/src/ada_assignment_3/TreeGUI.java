/*
    Callum Geoffrey Clow, 20119952
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TreeGUI extends JPanel implements ActionListener {

    protected BinarySearchTree tree1;
    protected PersistentDynamicTree tree2;
//    protected BalancedPersistenDynamicTree tree3;
    protected TreeType type;

    private JComboBox cmbVersions = null;
    private JLabel lblVersions = null;
    private final Timer timer;
    private final DrawPanel drawPanel;

    public TreeGUI(BinarySearchTree tree, TreeType type) {
        super(new BorderLayout());

        this.tree1 = tree;
        this.type = type;

        //GUI initialisation
        if (type.equals(TreeType.PERSISTENT_DYNAMIC) || type.equals(TreeType.BALANCED_PERSISTENT_DYNAMIC)) {
            this.tree2 = (PersistentDynamicTree) tree;

            this.lblVersions = new JLabel("Version: ");
            this.cmbVersions = new JComboBox();
            this.cmbVersions.setPreferredSize(new Dimension(100, 20));

            if (this.type.equals(TreeType.PERSISTENT_DYNAMIC)) {
                this.fillVersions(tree2);
            } else {
//                this.tree3 = (BalancedPersistentDynamicTree)tree;
//                this.fillVersions((BalancedPersistentDynamicTree) tree);
            }

            JPanel pnlControl = new JPanel();
            pnlControl.add(this.lblVersions);
            pnlControl.add(this.cmbVersions);
            super.add(pnlControl, BorderLayout.NORTH);
        }

        //time between GUI updates (ms)
        this.timer = new Timer(30, this);
        this.timer.start();

        this.drawPanel = new DrawPanel();
        super.add(this.drawPanel, BorderLayout.CENTER);
    }

    public void StartGUI(String title) {
        JFrame frame = new JFrame(title);
        frame.setLocation(20, 20);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    private void fillVersions(PersistentDynamicTree tree) {
        for (int i = 0; i < tree.versionList.size(); i++) {
            this.cmbVersions.addItem(i + 1);
        }
    }

//    private void fillVersions(BalancedPersistentDynamicTree tree){
//        for (int i = 0; i < tree.versionList.size(); i++) {
//            this.cmbVersions.addItem(i);
//        }
//    }
//    
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

            switch (type) {
                case PERSISTENT_DYNAMIC:
                    tree2.createTree((int) cmbVersions.getSelectedItem() - 1).draw(g);
                    break;
                case BALANCED_PERSISTENT_DYNAMIC:
                    break;
                default:
                    tree1.draw(g);
                    break;
            }
        }
    }

    //enum
    public enum TreeType {
        BINARY_SEARCH_TREE, PERSISTENT_DYNAMIC, BALANCED_PERSISTENT_DYNAMIC
    };
}
