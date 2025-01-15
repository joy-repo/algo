package binary_search_tree;

import binary_tree.MyBinaryTree;
import binary_tree.MyBinaryTree.Node;
import lombok.ToString;

public class LargestBSTInBT {

    @ToString
    public static class Container{

        public int minVal;
        public int maxVal;
        public int size;

        public Container(int maxVal, int minVal, int size){
            this.minVal=minVal;
            this.maxVal=maxVal;
            this.size=size;
        }
    }

    public static void main(String[] args) {
        Node root = MyBinaryTree.generateBinaryTree();
        Container container = getLargestBSTInBT(root);
        System.out.println(container);
        BSTInfo bstInfo =largestBstBt(root);
        System.out.println(bstInfo);
    }

    private static Container getLargestBSTInBT(Node root) {
        if(root==null)
            return new Container(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        Container left = getLargestBSTInBT(root.left);
        Container right = getLargestBSTInBT(root.right);

        if(left.maxVal < root.data && root.data < right.minVal){
            return new Container(Math.min(root.data, left.minVal)
                    , Math.max(root.data, right.maxVal)
                    ,left.size + right.size+1);
        }
        return new Container(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.size, right.size));

    }




    ////////////////////////////////////////////////
    @ToString
    static class BSTInfo {
        int min;
        int max;
        int mxSz;

        BSTInfo(int mn, int mx, int sz) {
            min = mn;
            max = mx;
            mxSz = sz;
        }
    }

    static BSTInfo largestBstBt(Node root) {
        if (root == null)
            return new BSTInfo(Integer.MAX_VALUE,
                    Integer.MIN_VALUE, 0);

        BSTInfo left = largestBstBt(root.left);
        BSTInfo right = largestBstBt(root.right);

        // Check if the current subtree is a BST
        if (left.max < root.data && right.min > root.data) {
            return new BSTInfo(Math.min(left.min, root.data),
                    Math.max(right.max, root.data),
                    1 + left.mxSz + right.mxSz);
        }

        return new BSTInfo(Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                Math.max(left.mxSz, right.mxSz));
    }
}
