/*
    Callum Geoffrey Clow, 20119952
    Caitlyn Young, 20109380
 */
package ada_assignment_3;

import java.util.ArrayList;

public class PersistentDynamicTree extends BinarySearchTree {

    private ArrayList<BinaryTreeNode> dupeList;
    protected ArrayList<BinaryTreeNode> versionList;

    //constructor for empty tree
    public PersistentDynamicTree() {
        dupeList = new ArrayList();
        versionList = new ArrayList();
    }

    //constructor for existing tree
    public PersistentDynamicTree(BinarySearchTree tree) {
        dupeList = new ArrayList();
        versionList = new ArrayList();

        if (tree.rootNode != null) {
            this.rootNode = tree.rootNode;
            versionList.add(rootNode);
        } else {
            System.out.println("Tree does not Exist");
        }
    }

    @Override
    public void nodeVisited(BinaryTreeNode node) {
//        BinaryTreeNode newNode = copyNode(node);

        BinaryTreeNode newNode = new BinaryTreeNode(node.element);
        newNode.leftChild = node.leftChild;
        newNode.rightChild = node.rightChild;
//
        dupeList.add(newNode);
    }

    @Override
    public void allNodesVisited() {

        if (dupeList.isEmpty()) {
            versionList.add(rootNode);

        } else if (this.size() != 1) {
            for (int i = 0; i < dupeList.size() - 1; i++) {

                if (dupeList.get(i).leftChild != null
                        && dupeList.get(i).leftChild.element.equals(dupeList.get(i + 1).element)) {
                    dupeList.get(i).leftChild = dupeList.get(i + 1);
                }

                if (dupeList.get(i).rightChild != null
                        && dupeList.get(i).rightChild.element.equals(dupeList.get(i + 1).element)) {
                    dupeList.get(i).rightChild = dupeList.get(i + 1);
                }
            }
            versionList.add(versionList.size() - 1, dupeList.get(0));

        } else {
            versionList.add(versionList.size() - 1, dupeList.get(0));
        }

        dupeList.clear();
    }

    public BinaryTreeNode copyNode(BinaryTreeNode node) {
        BinaryTreeNode nodeCopy = new BinaryTreeNode(node.element);

        if (node.leftChild != null) {
            nodeCopy.leftChild = copyNode(node.leftChild);
        }

        if (node.rightChild != null) {
            nodeCopy.rightChild = copyNode(node.rightChild);
        }

        return nodeCopy;
    }

    public BinarySearchTree createTree(int index) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.rootNode = versionList.get(index);
        return tree;
    }
}
