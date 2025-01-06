package binary_tree;

import binary_tree.MyBinaryTree.Node;

import java.util.HashMap;
import java.util.Map;

public class Hard_9_BTFromPostOrderAndInOrder {

    public static int[] inOrder = {40, 20, 50, 10, 60 ,30};

    public static int[] postOrder = {40, 50, 20, 60, 30 ,10 };

    public static void main(String[] args) {
        Map<Integer,Integer> inorderMap = new HashMap<>();

        for(int i=0; i< inOrder.length; i++){
            inorderMap.put(inOrder[i],i );
        }

        Node root =generateBT_(0, inOrder.length-1, 0, postOrder.length-1, inorderMap);
        System.out.print(root);
    }

    private static Node generateBT_(int inStart, int inend, int postStart, int postEnd, Map<Integer, Integer> inorderMap) {

        if(inStart>inend || postStart>postEnd) return null;

        Node root = new Node(postOrder[postEnd]);

        int inorderIndex = inorderMap.get(postOrder[postEnd]);

        int postLeftLen = (inorderIndex-inStart);

        root.left = generateBT_(inStart, inorderIndex-1,
                postStart, postStart+postLeftLen-1, inorderMap);

        root.right = generateBT_(inorderIndex+1, inend,
                postStart+postLeftLen, postEnd-1,
                inorderMap);
        return root;

    }
}
