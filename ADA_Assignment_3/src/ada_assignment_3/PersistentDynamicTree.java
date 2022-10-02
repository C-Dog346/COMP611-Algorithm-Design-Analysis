/*
    Callum Geoffrey Clow, 20119952
    Caitlyn Young, 20109380
 */
package ada_assignment_3;

import java.util.ArrayList;

public class PersistentDynamicTree extends BinarySearchTree {

    private ArrayList<BinaryTreeNode> dupeList;
    protected ArrayList<BinaryTreeNode> versionList;
    public int versionNum;

    //constructor for empty tree
    public PersistentDynamicTree() {
        dupeList = new ArrayList();
        versionList = new ArrayList();
        versionNum = 0;
    }

    //constructor for existing tree
    public PersistentDynamicTree(BinarySearchTree tree) {
        dupeList = new ArrayList();
        versionList = new ArrayList();

        if (tree.rootNode != null) {
            this.rootNode = tree.rootNode;
            versionList.add(copyTree(rootNode));
            versionNum = 1;
        } else {
            System.out.println("Tree does not Exist");
        }
    }

    @Override
    public void nodeVisited(BinaryTreeNode node) {
        BinaryTreeNode newNode = new BinaryTreeNode(node.element);
        dupeList.add(newNode);
    }

    @Override
    public void allNodesVisited() {
        if (dupeList.isEmpty()) {
            BinarySearchTree original = new BinarySearchTree();
            original.rootNode = new BinaryTreeNode(rootNode.element);
            versionList.add(original.rootNode);

        } else {
            for (int i = 0; i < dupeList.size() - 1; i++) {

                if (compare(dupeList.get(i).element, dupeList.get(i + 1).element) > 0) {
                    dupeList.get(i).leftChild = dupeList.get(i + 1);
                    findChildPos(versionList.get(versionNum - 1), dupeList.get(i), false);
                }

                if (compare(dupeList.get(i).element, dupeList.get(i + 1).element) < 0) {
                    dupeList.get(i).rightChild = dupeList.get(i + 1);
                    findChildPos(versionList.get(versionNum - 1), dupeList.get(i), true);
                }
            }

            if ((dupeList.get(dupeList.size() - 2).leftChild == null
                    || dupeList.get(dupeList.size() - 2).rightChild == null) && (dupeList.get(dupeList.size() - 2) != rootNode)) {

                if (compare(dupeList.get(dupeList.size() - 2).element, dupeList.get(dupeList.size() - 1).element) < 0) {
                    dupeList.get(dupeList.size() - 2).rightChild = dupeList.get(dupeList.size() - 1);
                } else {
                    dupeList.get(dupeList.size() - 2).leftChild = dupeList.get(dupeList.size() - 1);
                }
            }
            versionList.add(dupeList.get(0));
        }
        dupeList.clear();
        versionNum++;
    }

    public BinaryTreeNode findChildPos(BinaryTreeNode oldRoot, BinaryTreeNode newParent, boolean left) {
        BinaryTreeNode oldParent = oldRoot;

        boolean found = false;
        while (!found && oldParent != null) {
            int comparison = compare(oldParent.element, newParent.element);

            if (comparison == 0) {
                found = true;

            } else if (comparison < 0) {
                oldParent = oldParent.rightChild;
            } else { // comparison > 0
                oldParent = oldParent.leftChild;
            }
        }

        if (left) {
            newParent.leftChild = oldParent.leftChild;
        } else { //right 
            newParent.rightChild = oldParent.rightChild;
        }

        return oldParent;
    }

    public BinaryTreeNode copyTree(BinaryTreeNode node) {
        BinaryTreeNode treeCopy = new BinaryTreeNode(node.element);

        if (node.leftChild != null) {
            treeCopy.leftChild = copyTree(node.leftChild);
        }

        if (node.rightChild != null) {
            treeCopy.rightChild = copyTree(node.rightChild);
        }

        return treeCopy;
    }

    public BinarySearchTree createTree(int index) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.rootNode = versionList.get(index);
        return tree;
    }
}
