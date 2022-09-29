/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada_assignment_3;

import java.util.ArrayList;

/**
 *
 * @author Callum
 */
public class PersistentDynamicTree extends BinarySearchTree {
    
    private ArrayList<BinaryTreeNode> dupeList;
    
    public PersistentDynamicTree() {
        dupeList = new ArrayList();
    }
    
    @Override
    public void nodeVisited(BinaryTreeNode node) {
        BinaryTreeNode newNode = node;
        dupeList.add(newNode);
    }
    
    @Override
    public void allNodesVisited() {
        for (int i = 0; i < dupeList.size(); i++) {
            if (dupeList.get(i).leftChild.equals(dupeList.get(i+1))) {
                dupeList.get(i).leftChild = dupeList.get(i+1);
            }
            else if (dupeList.get(i).rightChild.equals(dupeList.get(i+1))) {
                dupeList.get(i).rightChild = dupeList.get(i+1);
            }
        }
    }
}


