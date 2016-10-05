/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btreeold;

import static btreeold.BTree.DEGREE;
import static btreeold.BTree.INVALID;
import static btreeold.BTree.ORDER;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saketh
 */
public class Node implements Serializable {

    int[] keys = new int[DEGREE];
    transient Node[] childs = new Node[ORDER];
    private int keyCount = 0;
    private int childCount = 0;
    public int level = 0;
    private String path;
    
    Node() {
        this.level = level;
        for (int i = 0; i < keys.length; ++i) {
            keys[i] = INVALID;
        }
        for (int i = 0; i < childs.length; ++i) {
            childs[i] = null;
        }
    }

    boolean isFull() {
        return (keyCount == DEGREE);
    }

    boolean isEmpty() {
        return (keyCount == 0);
    }

    void insertVal(int val) {
//        System.out.println("INSERTVAL : Val = "+val+" isLeaf = "+isLeaf(this)+" Node = "+this);
        int j = 0;
        for (; j < keys.length; ++j) {
            if (keys[j] == INVALID) {
                break;
            }
            if (keys[j] > val) {
                break;
            }
        }
        for (int i = (keys.length - 2); i >= j; --i) {
            keys[i + 1] = keys[i];
        }
        keys[j] = val;
        this.updateKeyCount();
    }

    void updateKeyCount() {
        int j = 0;
        for (; j < keys.length; ++j) {
            if (keys[j] == INVALID) {
                break;
            }
        }
        this.keyCount = j;
    }

    void updateChildCount() {
        this.childCount = 0;
        for (int j = 0; j < childs.length; ++j) {
            if (childs[j] != null) {
                ++this.childCount;
            }
        }
    }

    void shiftByOneAt(int pos, boolean right) {
        if (!(pos < DEGREE)) {
            System.err.println("ERRROR : pos is greater than DEGREE");
            System.exit(0);
        }
        if (right) {
            int shiftToPos = 0;
            for (shiftToPos = pos; shiftToPos < DEGREE; ++shiftToPos) {
                if (this.keys[shiftToPos] == -1) {
                    break;
                }
            }
            for (int i = shiftToPos; i > pos; --i) {
                this.keys[i] = this.keys[i - 1];
                this.childs[i + 1] = this.childs[i];
            }
        } else {
            int shiftToPos = 0;
            for (shiftToPos = pos; shiftToPos >= 0; --shiftToPos) {
                if (this.keys[shiftToPos] == -1) {
                    break;
                }
            }
            for (int i = shiftToPos; i < pos; ++i) {
                this.keys[i] = this.keys[i + 1];
                this.childs[i - 1] = this.childs[i];
            }
        }
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path){
        this.path = path;
    }

    static void resetNodeKeys(Node node) {
        for (int i = 0; i < node.keys.length; ++i) {
            node.keys[i] = INVALID;
        }
    }

    static void resetNodeChilds(Node node) {
        if (Node.isLeaf(node)) {
            return;
        }
        for (int i = 0; i < node.childs.length; ++i) {
            node.childs[i] = new Node();
        }
    }

    static boolean isLeaf(Node node) {
        return (node.childCount == 0);
    }

    static void printNode(Node node) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < node.keys.length; ++i) {
            System.out.printf(node.keys[i] + " ");
        }
        System.out.println();
    }
    
    public static void writeNode(Node node,String parentPath, String path){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(parentPath+"/"+path+".bnode"));
            out.writeObject(node);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(BTree.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
}
