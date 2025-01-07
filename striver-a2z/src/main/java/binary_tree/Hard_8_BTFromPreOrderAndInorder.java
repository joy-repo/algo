package binary_tree;

import binary_tree.MyBinaryTree.Node;

import java.util.HashMap;
import java.util.Map;



public class Hard_8_BTFromPreOrderAndInorder {

    //Root left Right
    static int[] preOrder = {10, 20, 40, 50, 30, 60 };

    //left root right
    static int[] inOrder = { 40, 20,50, 10, 60,30 };

    //PREODER -> --10--, 20, 40, 50, 30, 60

    //             left             right
    //INORDER -> 40, 20,50, --10--, 60,30

    ///           10
    ///    20                30
    /// 40    50            60
    public static void main(String[] args) {
        Map<Integer, Integer> inOrdeMap = new HashMap<>();

        for(int i=0; i<inOrder.length; i++){
            inOrdeMap.put(inOrder[i],i );
        }

        Node root = generateBT(0, inOrder.length-1, 0, preOrder.length-1, inOrdeMap);
        System.out.print(root);
    }

    private static Node generateBT(int inStart, int inEnd, int preStart, int preEnd, Map<Integer, Integer> inOrdeMap) {

        if(preStart>preEnd || inStart>inEnd) return null;

        Node root = new Node(preOrder[preStart]);

        int inIndex = inOrdeMap.get(preOrder[preStart]);
        int preLeftlen = inIndex-inStart;
        int newPreEndleft = preStart+preLeftlen;

        root.left = generateBT(inStart, inIndex-1,
                preStart+1, newPreEndleft,
                inOrdeMap);

        root.right = generateBT(inIndex+1, inEnd,
                newPreEndleft+1, preEnd, inOrdeMap);
        return root;


    }
}
