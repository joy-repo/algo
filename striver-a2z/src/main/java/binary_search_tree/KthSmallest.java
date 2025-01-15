package binary_search_tree;

import binary_search_tree.MyBinarySearchTree.BSNode;

public class KthSmallest {
    public static int K = 5;

    public static void main(String[] args) {
        MyBinarySearchTree mbst = MyBinarySearchTree.generateBST();
        int[] count = new int[1];
        count[0]=0;
        BSNode res = getKthSmallest(mbst.root, count);
        System.out.println(res);

        count[0]=0;
        sol(mbst.root, count);

    }

    private static BSNode getKthSmallest(BSNode root, int[] count) {

        if(root==null) {
            return null;
        }

        BSNode left = getKthSmallest(root.left, count);

        if(left!=null)
            return left;
        count[0]++;
        if(count[0]==K) return root;

        return getKthSmallest(root.right, count);


    }

    private static void sol(BSNode node, int[] count){

        if(node==null || count[0]>K) return ;

        sol(node.left, count);
        count[0]++;
        if(count[0]==K){
            System.out.println("ans: "+ node);
            return ;
        }

        sol(node.right, count);
    }
}
