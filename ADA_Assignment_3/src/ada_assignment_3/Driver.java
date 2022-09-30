/*
    Callum Geoffrey Clow, 00000000
    Caitlyn Young, 20109380
 */
package ada_assignment_3;

public class Driver {

    public static void main(String[] args) {

        System.out.println("____________________________________________________");
        PersistentDynamicTree PDTree = new PersistentDynamicTree();
        PDTree.add(100); //root
        System.out.println("____________________________________________________");

        System.out.println("adding 0...");
        PDTree.add(0);
//        System.out.println("adding 200...");
//        PDTree.add(200);
        System.out.println("adding -100...");
        PDTree.add(-100);
        System.out.println("adding 50...");
        PDTree.add(50);
        System.out.println("adding 150...");
        PDTree.add(150);
//        System.out.println("adding 300...");
//        PDTree.add(300);
        System.out.println("adding -200...");
        PDTree.add(-200);
//        System.out.println("adding -50...");
//        PDTree.add(-50);
//        System.out.println("adding 25...");
//        PDTree.add(25);
//        System.out.println("adding 75...");
//        PDTree.add(75);
//        System.out.println("adding 350...");
//        PDTree.add(350);
//        System.out.println("adding 125...");
//        PDTree.add(125);
//        System.out.println("adding 175...");
//        PDTree.add(175);
//        System.out.println("adding 250...");
        PDTree.add(250);

        System.out.println("____________________________________________________");
        System.out.println("Number of Versions = " + PDTree.versionList.size() + "\n");
        for (int i = 0; i < PDTree.versionList.size(); i++) {
            System.out.println("Version " + (i + 1) + " -> " + PDTree.createTree(i).toString());
//            System.out.println("Version " + (i + 1) + " -> " + PDTree.versionList.get(i));
        }
        System.out.println("____________________________________________________");

        //build GUI
        TreeGUI original = new TreeGUI(PDTree.createTree(0));
        original.StartGUI(1);

        TreeGUI newest = new TreeGUI(PDTree.createTree(PDTree.versionList.size() - 1));
        newest.StartGUI(PDTree.versionList.size());
//
//
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
//        testTree.add(175);
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
////        System.out.println("adding 88...");
////        testPDTree.add(88);
////
//        System.out.println("____________________________________________________");
//        System.out.println("Number of Versions = " + testPDTree.versionList.size() + "\n");
//        for (int i = 0; i < testPDTree.versionList.size(); i++) {
//            System.out.println("Version " + (i + 1) + " -> " + testPDTree.createTree(i).toString());
////            System.out.println("Version " + (i + 1) + " -> " + PDTree.versionList.get(i));
//        }
//        System.out.println("____________________________________________________");
//
//        //build GUI
//        TreeGUI existingTree = new TreeGUI(testPDTree.createTree(0));
//        existingTree.StartGUI(1);
//
//        TreeGUI existingChanged = new TreeGUI(testPDTree.createTree(testPDTree.versionList.size() - 1));
//        existingChanged.StartGUI(testPDTree.versionList.size());
    }
}
