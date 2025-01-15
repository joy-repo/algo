package binary_search_tree;

import binary_tree.MyBinaryTree;
import binary_tree.MyBinaryTree.Node;

public class LargestBSTInBT {


    public static class Container{

        public int minVal;
        public int maxVal;
        public int size;

        public Container(int minVal, int maxVal, int size){
            this.minVal=minVal;
            this.maxVal=maxVal;
            this.size=size;
        }
    }

    public static void main(String[] args) {
        Node root = MyBinaryTree.generateBinaryTree();
        Container container = getLargestBSTInBT(root);
    }

    private static Container getLargestBSTInBT(Node root) {
        if(root==null)
            return new Container(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);

        Container left = getLargestBSTInBT(root.left);
        Container right = getLargestBSTInBT(root.right);

        if(root.data>left.maxVal && root.data<right.minVal){
            return new Container(Math.max(root.data, left.maxVal )
                    , Math.min(root.data, right.minVal)
                    ,left.size + right.size+1);
        }
        return new Container(Integer.MAX_VALUE, Integer.MIN_VALUE, Math.max(right.size,left.size));

    }
}
