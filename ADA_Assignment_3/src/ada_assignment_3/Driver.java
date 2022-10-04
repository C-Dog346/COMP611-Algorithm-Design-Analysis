/*
    Callum Geoffrey Clow, 20119952
    Caitlyn Young, 20109380
 */
package ada_assignment_3;

public class Driver {

    public static void main(String[] args) {
//        devTesting();

////////////////////////////////////DEMO////////////////////////////////////
        //make tree (BST)
        BinarySearchTree<Integer> testTree = new BinarySearchTree();

        testTree.add(100); //root node
        testTree.add(50);
        testTree.add(150);
        testTree.add(25);
        testTree.add(75);
        testTree.add(125);
        testTree.add(175);
        testTree.add(110);
        testTree.add(130);
        testTree.add(105);
        testTree.add(115);

        //build GUI
        TreeGUI existingChanged = new TreeGUI(testTree, TreeGUI.TreeType.BINARY_SEARCH_TREE);
        existingChanged.StartGUI("Binary Search Tree");

        //make tree (PDT) from existing tree
        PersistentDynamicTree testPDTree = new PersistentDynamicTree(testTree);

        //edit tree
        testPDTree.add(90); //add node
        testPDTree.remove(105); //remove leaf node
        testPDTree.remove(50); //remove internal node
        testPDTree.remove(100); //remove root node

        //console output
        System.out.println("____________________________________________________"
                + "Persistent Dynamic Set"
                + "____________________________________________________");

        System.out.println("Number of Versions = " + testPDTree.versionList.size() + "\n");

        for (int i = 0; i < testPDTree.versionList.size(); i++) {
            System.out.println("Version " + (i + 1) + " -> " + testPDTree.versionList.get(i));
        }
        System.out.println("\nCurrent Tree -> " + testPDTree);
        System.out.println("____________________________________________________"
                + "______________________"
                + "____________________________________________________");

        //build GUI
        TreeGUI existingGUI = new TreeGUI(testPDTree, TreeGUI.TreeType.PERSISTENT_DYNAMIC);
        existingGUI.StartGUI("Persistent Dynamic Tree");
        
        ////-----------------------------------PBDT------------------------------------------ 
        BalancedPersistentDynamicTree rbt = new BalancedPersistentDynamicTree();

        rbt.add(5);
        rbt.add(10);
        rbt.add(7);
        rbt.add(8);
        rbt.add(3);
        rbt.add(15);
        rbt.add(20);
        rbt.add(25);
        rbt.add(1);

        rbt.rightRotate(rbt.rootNode, null);
        rbt.leftRotate(rbt.rootNode, null);
        rbt.leftRotate(rbt.rootNode, null);

        TreeGUI rbtGUI = new TreeGUI((BinarySearchTree) rbt, TreeGUI.TreeType.BINARY_SEARCH_TREE);
        rbtGUI.StartGUI("RBT RBT RBT");
    }

    public static void devTesting() {
//        System.out.println("____________________________________________________");
//        PersistentDynamicTree PDTree = new PersistentDynamicTree();
//        PDTree.add(100); //root
//        System.out.println("Root = " + PDTree.rootNode);
//        System.out.println("____________________________________________________");
//
////        System.out.println("adding 0...");
////        PDTree.add(0);
////        System.out.println("adding 200...");
////        PDTree.add(200);
////        System.out.println("adding -100...");
////        PDTree.add(-100);
////        System.out.println("adding 50...");
////        PDTree.add(50);
////        System.out.println("adding 75...");
////        PDTree.add(75);
////        System.out.println("adding 74...");
////        PDTree.add(74);
////        System.out.println("removing -100...");
////        PDTree.remove(-100);
////        System.out.println("removing 0...");
////        PDTree.remove(0);
////        System.out.println("adding 150...");
////        PDTree.add(150);
////        System.out.println("adding 300...");
////        PDTree.add(300);
////        System.out.println("adding 350...");
////        PDTree.add(350);
////        System.out.println("removing 200...");
////        PDTree.remove(200);
////        System.out.println("removing 350...");
////        PDTree.remove(350);
////        System.out.println("adding -200...");
////        PDTree.add(-200);
////        System.out.println("adding -50...");
////        PDTree.add(-50);
////        System.out.println("adding 25...");
////        PDTree.add(25);
////        System.out.println("adding 75...");
////        PDTree.add(75);
////        System.out.println("adding 350...");
////        PDTree.add(350);
////        System.out.println("adding 125...");
////        PDTree.add(125);
////        System.out.println("adding 175...");
////        PDTree.add(175);
////        System.out.println("adding 250...");
////        PDTree.add(250);
////        System.out.println("removing 100...");
////        PDTree.remove(100);
//
//        System.out.println("____________________________________________________");
//        System.out.println("Number of Versions = " + PDTree.versionList.size());
//        System.out.println("Version Number = " + PDTree.versionNum + "\n");
//        for (int i = 0; i < PDTree.versionList.size(); i++) {
////            System.out.println("Version " + (i + 1) + " -> " + PDTree.createTree(i).toString());
//            System.out.println("Version " + (i + 1) + " -> " + PDTree.versionList.get(i));
//        }
//        System.out.println("\nCurrent Tree -> " + PDTree);
//        System.out.println("____________________________________________________");
//
//        //build GUI          
//        TreeGUI testGUI = new TreeGUI(PDTree, TreeGUI.TreeType.PERSISTENT_DYNAMIC);
//        testGUI.StartGUI("Persistent Dynamic Tree");
//
////--------------------------------------------------------------------------------
//        //existing tree test
//        BinarySearchTree<Integer> testTree = new BinarySearchTree();
//        testTree.add(90);
//        testTree.add(50);
//        testTree.add(25);
//        testTree.add(75);
//        testTree.add(80);
//        testTree.add(200);
//        testTree.add(150);
//        testTree.add(125);
//        testTree.add(122);
//        testTree.add(126);
//        testTree.add(124);
//        testTree.add(175);
//        testTree.add(176);
////        testTree.add(112);
//        System.out.println(testTree.toString());
//
//        System.out.println("____________________________________________________");
//        PersistentDynamicTree testPDTree = new PersistentDynamicTree(testTree);
//        System.out.println("in PDT ---> " + testPDTree.toString());
//        System.out.println("____________________________________________________");
//
//        System.out.println("adding 999...");
//        testPDTree.add(999);
//        System.out.println("adding 88...");
//        testPDTree.add(88);
//        System.out.println("removing 150...");
//        testPDTree.remove(150);
//        System.out.println("removing 90...");
//        testPDTree.remove(90);//root removed
//
//        System.out.println("____________________________________________________");
//        System.out.println("after changed: " + testPDTree.versionList.toString());
////
//        System.out.println("____________________________________________________");
//        System.out.println("Number of Versions = " + testPDTree.versionList.size() + "\n");
//        for (int i = 0; i < testPDTree.versionList.size(); i++) {
////            System.out.println("Version " + (i + 1) + " -> " + testPDTree.createTree(i).toString());
//            System.out.println("Version " + (i + 1) + " -> " + testPDTree.versionList.get(i));
//        }
//        System.out.println("\nCurrent Tree -> " + testPDTree);
//        System.out.println("____________________________________________________");
//
//        //build GUI(s)
//        TreeGUI existingChanged = new TreeGUI(testTree, TreeGUI.TreeType.BINARY_SEARCH_TREE);
//        existingChanged.StartGUI("Binary Search Tree");
//
//        TreeGUI existingGUI = new TreeGUI(testPDTree, TreeGUI.TreeType.PERSISTENT_DYNAMIC);
//        existingGUI.StartGUI("Persistent Dynamic Tree");
    }
}
