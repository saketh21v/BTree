/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btreeold;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saketh
 */
public class BTree implements Serializable {

    public final static int INVALID = -1;
    public final static int ORDER = 5;
    public final static int DEGREE = ORDER - 1;
    public Node root;
    private final int initLevel = 1;
    private int highestLevel = initLevel;
    private String name;
    ObjectOutputStream oout;

    BTree(int val, String name) {
        this.root = new Node();
        this.root.keys[0] = val;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getHighestLevel() {
        return highestLevel;
    }

    public boolean insert(int val) {
//        System.out.println("The value " + val + " is being inserted");
//        if(this.searchVal(val)){
//            System.err.println("Value already exists");
//            return false;
//        }

        Node temp = this.root;
        this.root = BTree.insertVal(val, root);
        if (temp != this.root) {
            this.highestLevel++;
        }
        return true;
    }

    private static Node insertVal(int val, Node node) {
        //Making sure all the counts are proper.
        if (node == null) {
            return null;
        }
        node.updateChildCount();
        node.updateKeyCount();

        if (Node.isLeaf(node)) { // Checking if leaf node. CASE 1: Leaf Node.
            if (!node.isFull()) { //Leaf node not full case.
                node.insertVal(val);
                return node;
            } else { //Leaf node full. Split and send temporary parent to calling function
                return splitNode(val, null, node);
            }
        } else {// CASE 2 : NON-Leaf node 
            Node tParent, lNode;// tParent -> Temporary parent, lNode -> Leaf Node(Assumption. Harmless)
            int pos = 0;
            for (pos = 0; pos < DEGREE; ++pos) {
                if (node.keys[pos] == INVALID) {
                    break;
                }
                if (node.keys[pos] > val) {
                    break;
                }
            }
            lNode = node.childs[pos];
            tParent = insertVal(val, lNode);
            if (tParent == lNode) // SUBCASE 1: CHILD NOT FULL. Inserted in child node since it wasn't full. No worries.
            {
                return node;
            }
            //SUBCASE 2: CHILD FULL. Got a tParent.
            // tParent not equal to lNode means that leaf node was full and has been split.

            int midVal = tParent.keys[0];
            node.updateChildCount();
            node.updateKeyCount();
            if (!node.isFull()) { //(;p)SUB2-SUBCASE 1: Current node is NOT FULL
                int midValPos = 0;
                for (midValPos = 0; midValPos < DEGREE; ++midValPos) {
                    if (node.keys[midValPos] == INVALID) {
                        break;
                    }
                    if (node.keys[midValPos] > midVal) {
                        break;
                    }
                }
                node.shiftByOneAt(midValPos, true);
                node.keys[midValPos] = midVal;
                node.childs[midValPos + 1] = tParent.childs[1];
                return node;
            } else { // SUB2-SUBCASE 2: Current node is FULL. Delegate to Parent
                tParent = splitNode(midVal, tParent, node);
                return tParent;
            }
        }
    }

    private static Node splitNode(int val, Node midNode, Node node) {
        Node tParent = new Node(); // Temporary Parent
        Node sibl = new Node();
        int midPos = DEGREE / 2;

        int[] arr = new int[DEGREE + 1];
        Node[] arrN = new Node[ORDER + 1];
        for (int i = 0; i < node.keys.length; ++i) {
            arr[i] = node.keys[i];
        }
        int j = 0;
        int c = 0; // Used to find where to put the childs of midNode.
        arr[ORDER - 1] = val;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == val) {
                c = i;
                break;
            }
        }
        if (midNode != null) {
            arrN[c] = midNode.childs[0];
            arrN[c + 1] = midNode.childs[1];
        }
        for (Node x : node.childs) {
            if ((j == c)) {
                j++;
                continue;
            }
            if ((j == c + 1)) {
                j++;
                arrN[j++] = x;
                continue;
            }
            arrN[j] = x;
            ++j;
        }
        Node.resetNodeKeys(node);
        if (!Node.isLeaf(node)) {
            Node.resetNodeChilds(node);
        }
        for (int i = 0; i < DEGREE / 2; ++i) {
            node.keys[i] = arr[i];
        }
        for (int i = 0; i < ((ORDER % 2 == 0) ? DEGREE / 2 + 1 : DEGREE / 2); i++) {
            sibl.keys[i] = arr[midPos + i + 1];
        }
        //Updating the childs of all the nodes : node, sibl and tParent
        if (midNode != null) {
            for (int i = 0; i <= DEGREE / 2; ++i) {
                node.childs[i] = arrN[i];
            }
            j = 0;
            int ii = 0;
            for (int i = ((DEGREE / 2) + 1); i < (ORDER + 1); ++i) {
                sibl.childs[j++] = arrN[i];
            }
        }

        node.updateKeyCount();
        sibl.updateKeyCount();

        tParent.keys[0] = arr[midPos];
        tParent.childs[0] = node;
        tParent.childs[1] = sibl;

        tParent.updateKeyCount();

        tParent.updateChildCount();

        return tParent;
    }

    public void printBTree() {
        this.printTree(this.root, 0);
    }

    void writeToDisk(String parentPath) {
        ObjectOutputStream oout = null;
        try {
            oout = new ObjectOutputStream(new FileOutputStream(parentPath+"/"+this.name+".tree"));
            oout.writeObject(this);
            oout.close();
            this.writeTreeToDisk(root, parentPath, "root");
        } catch (IOException ex) {
            Logger.getLogger(BTree.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oout.close();
            } catch (IOException ex) {
                Logger.getLogger(BTree.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void writeTreeToDisk(Node root, String parentPath, String path) {
        if (root == null || root.isEmpty()) {
            return;
        }
//        if (root == this.root) {
//            Node.writeNode(root, parentPath, "root");
//        }
        if(root != this.root)
            Node.writeNode(root, parentPath, path);
        for (int x = 0; x < root.childs.length; x++) {
            writeTreeToDisk(root.childs[x], parentPath, path + "#" + x);
//            Node.writeNode(root.childs[x], parentPath, path + "#" + x);
        }
    }

    private void printTree(Node root, int i) {
        if (root == null || root.isEmpty()) {
            return;
        }
        System.out.print("Level " + i + " : ");
        for (int x : root.keys) {
            System.out.print(x + " ");
        }
        System.out.println();
        for (Node x : root.childs) {
            printTree(x, i + 1);
        }
    }

    public boolean searchVal(int val) {
        return search(val, this.root);
    }

    private static boolean search(int key, Node root) {
        if (root == null) {
            return false;
        }
        if (root.isEmpty()) {
            return false;
        }
        int i = 0;
        for (i = 0; i < DEGREE; ++i) {
            if (root.keys[i] == INVALID) {
                break;
            }
            if (root.keys[i] == key) {
                return true;
            }
            if (root.keys[i] > key) {
                return search(key, root.childs[i]);
            }
        }
//        if(root.keys[i] == INVALID)
//            return search(key, root.childs[i]);
        return search(key, root.childs[i]);
    }

    public static void writeTree(BTree tree, String parentPath) {
        BTree.writeTreeNode(tree.root, parentPath, "root");
    }

    private static void writeTreeNode(Node node, String parentPath, String path) {
        if (node == null) {
            return;
        }
        Node.writeNode(node, parentPath, path);
        StringBuilder ppath = new StringBuilder(path + "#");
        for (int i = 0; i < node.childs.length; i++) {
            ppath.append(String.valueOf(i));
            writeTreeNode(node.childs[i], parentPath, ppath.toString());
            int ind = ppath.length() - 1;
            ppath.deleteCharAt(ind);
        }
    }
}
