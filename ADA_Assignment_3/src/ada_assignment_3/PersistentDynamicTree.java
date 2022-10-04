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
        if (node != null) {
            BinaryTreeNode newNode = new BinaryTreeNode(node.element);
            dupeList.add(newNode);
        } else {
            dupeList.add(null);
        }
    }

    @Override
    public void allNodesVisited(boolean add) {
        if (add) { //node was added
            //root created
            if (dupeList.isEmpty()) {
                BinarySearchTree original = new BinarySearchTree();
                original.rootNode = new BinaryTreeNode(rootNode.element);

                //add new version
                versionList.add(original.rootNode);

            } else {
                for (int i = 0; i < dupeList.size() - 1; i++) {
                    assignChildren(i);
                }

                //add new node
                if ((dupeList.get(dupeList.size() - 2).leftChild == null
                        || dupeList.get(dupeList.size() - 2).rightChild == null)
                        && (dupeList.get(dupeList.size() - 2) != rootNode)) {

                    if (compare(dupeList.get(dupeList.size() - 2).element, dupeList.get(dupeList.size() - 1).element) < 0) {
                        dupeList.get(dupeList.size() - 2).rightChild = dupeList.get(dupeList.size() - 1);
                    } else {
                        dupeList.get(dupeList.size() - 2).leftChild = dupeList.get(dupeList.size() - 1);
                    }
                }

                //add new version
                versionList.add(dupeList.get(0));
            }

        } else { //node was removed
            //leaf node removed
            if (dupeList.get(dupeList.size() - 1) == null) {

                for (int i = 0; i < dupeList.size() - 2; i++) {
                    assignChildren(i);
                }

                //remove leaf
                if (compare(dupeList.get(dupeList.size() - 3).element, dupeList.get(dupeList.size() - 2).element) < 0) {
                    dupeList.get(dupeList.size() - 3).rightChild = null;
                } else {
                    dupeList.get(dupeList.size() - 3).leftChild = null;
                }

                //internal node removed
            } else if (dupeList.size() > 2) {

                for (int i = 0; i < dupeList.size() - 2; i++) {
                    assignChildren(i);
                }

                //replace node
                if (compare(dupeList.get(dupeList.size() - 3).element, dupeList.get(dupeList.size() - 2).element) < 0) {
                    dupeList.get(dupeList.size() - 3).rightChild = dupeList.get(dupeList.size() - 1);
                } else {
                    dupeList.get(dupeList.size() - 3).leftChild = dupeList.get(dupeList.size() - 1);
                }

                //assign replacement's children
                if (compare(dupeList.get(dupeList.size() - 1).element, dupeList.get(dupeList.size() - 2).element) < 0) {
                    //replace right
                    findChild(versionList.get(versionNum - 1), dupeList.get(dupeList.size() - 1), true);
                    dupeList.get(dupeList.size() - 1).rightChild
                            = findChild(versionList.get(versionNum - 1), dupeList.get(dupeList.size() - 2), false);
                } else {
                    //replace left
                    findChild(versionList.get(versionNum - 1), dupeList.get(dupeList.size() - 1), false);
                    dupeList.get(dupeList.size() - 1).leftChild
                            = findChild(versionList.get(versionNum - 1), dupeList.get(dupeList.size() - 2), true);
                }

                //root node removed
            } else {
                //replaced by right child
                if (compare(
                        findChild(versionList.get(versionNum - 1), dupeList.get(0), false).element,
                        dupeList.get(1).element) == 0) {

                    findChild(versionList.get(versionNum - 1), dupeList.get(1), false);
                    dupeList.get(1).leftChild
                            = findChild(versionList.get(versionNum - 1), dupeList.get(0), true);

                    //replaced by lower child
                } else {
                    //assign replacement's children
                    dupeList.get(1).leftChild = 
                            findChild(versionList.get(versionNum - 1), dupeList.get(0), true);
                    dupeList.get(1).rightChild = 
                            findChild(versionList.get(versionNum - 1), dupeList.get(0), false);

                    //remove replacement from original position
                    BinaryTreeNode current = dupeList.get(1).rightChild;
                    ArrayList<BinaryTreeNode> tempList = new ArrayList();
                    tempList.add(new BinaryTreeNode(current.element));

                    while (current.leftChild.leftChild != null) {
                        current = current.leftChild;
                        tempList.add(new BinaryTreeNode(current.element));
                    }
                    tempList.get(0).rightChild = dupeList.get(1).rightChild.rightChild;

                    for (int i = 1; i < tempList.size(); i++) {
                        findChild(versionList.get(versionNum - 1), tempList.get(i), false);

                        findChild(versionList.get(versionNum - 1), tempList.get(i), true);
                    }

                    if (tempList.get(tempList.size() - 1).leftChild.rightChild == null) {
                        tempList.get(tempList.size() - 1).leftChild = null;
                    } else {
                        tempList.get(tempList.size() - 1).leftChild 
                                = tempList.get(tempList.size() - 1).leftChild.rightChild;
                    }

                    dupeList.get(1).rightChild = tempList.get(0);
                    current = tempList.get(0);
                    for (int i = 0; i < tempList.size(); i++) {
                        current.leftChild = tempList.get(i);
                        current = current.leftChild;
                    }
                }
                dupeList.remove(0);
            }

            //add new version
            versionList.add(dupeList.get(0));
        }
        
        //prepare for next change
        dupeList.clear();
        versionNum++;
    }

    public void assignChildren(int i) {
        //left changed
        if (compare(dupeList.get(i).element, dupeList.get(i + 1).element) > 0) {
            dupeList.get(i).leftChild = dupeList.get(i + 1);
            findChild(versionList.get(versionNum - 1), dupeList.get(i), false);
        }

        //right changed
        if (compare(dupeList.get(i).element, dupeList.get(i + 1).element) < 0) {
            dupeList.get(i).rightChild = dupeList.get(i + 1);
            findChild(versionList.get(versionNum - 1), dupeList.get(i), true);
        }
    }

    public BinaryTreeNode findChild(BinaryTreeNode oldRoot, BinaryTreeNode newParent, boolean left) {
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
            return oldParent.leftChild;
        } else { //right 
            newParent.rightChild = oldParent.rightChild;
            return oldParent.rightChild;
        }
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
