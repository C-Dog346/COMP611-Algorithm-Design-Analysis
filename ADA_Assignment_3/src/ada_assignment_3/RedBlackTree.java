/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada_assignment_3;

import java.awt.Color;

/**
 *
 * @author Callum
 *
 * a balanced persistent dynamic set as a red black tree
 */
public class RedBlackTree<E> extends PersistentDynamicTree {

    //Color for a red node.
    protected static final Color RED = Color.red;

    //Color for a black node.
    protected static final Color BLACK = Color.black;

    protected class RBTNode extends BinarySearchTree.BinaryTreeNode {

        protected Color color;

        public RBTNode(E element) {
            super(element);
            this.color = RED;
        }

    }

    public void insert(E o) {
        super.add(o);
        insertFixup(o);
    }

    private void insertFixup(E o) {
        RBTNode y = null;
        RBTNode x = new RBTNode(o);

        RBTNode root = (RBTNode) this.versionList.get(versionList.size() - 1);

//        while (root.) {
//
//        }
    }

    protected void rightRotate(RBTNode x, RBTNode parent) {
        RBTNode y = (RBTNode) x.leftChild;

        x.leftChild = y.rightChild;
        y.rightChild = x;

        if (this.rootNode == x) {
            this.rootNode = y;
        }
        else if (parent.leftChild == x) {
            parent.leftChild = y;
        }
        else {
            parent.rightChild = y;
        }
    }

    protected void leftRotate(RBTNode x, RBTNode parent) {
        RBTNode y = (RBTNode) x.rightChild;

        x.rightChild = y.leftChild;
        y.leftChild = x;

        if (this.rootNode == x) {
            this.rootNode = y;
        }
        else if (parent.rightChild == x) {
            parent.rightChild = y;
        }
        else {
            parent.leftChild = y;
        }
    }
    
    @Override
    public void nodeVisited(BinaryTreeNode ndoe) {
        System.out.println("RedBlackTree nodeVisited");
    }
    
    @Override
    public void allNodesVisited(boolean add) {
        System.out.println("RedBlackTree allNodesVisited");
    }
}
