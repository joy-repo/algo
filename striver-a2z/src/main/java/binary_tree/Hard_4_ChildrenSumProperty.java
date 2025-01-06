package binary_tree;

import binary_tree.MyBinaryTree.Node;

public class Hard_4_ChildrenSumProperty {

    public static void main(String[] args) {
        Node root = MyBinaryTree.generateBinaryTree();
        childrenSumPtoperty(root);
        System.out.print(root);
    }

    private static int childrenSumPtoperty(Node root) {

        if(root==null) return 0;
        if(root.left==null && root.right==null) return root.data;
        int sum =0;
        if(root.left!=null) sum=sum+root.left.data;
        if(root.right!=null) sum=sum+root.right.data;

        if(sum<root.data){
            if(root.left!=null) root.left.data=root.data;
            if(root.right!=null) root.right.data=root.data;
        }

        int rightSum = childrenSumPtoperty(root.right);
        int leftSum =childrenSumPtoperty(root.left);

        root.data=rightSum+leftSum;
        return root.data;

    }
}
