package binary_tree;

import binary_tree.MyBinaryTree.Node;

import java.util.HashMap;
import java.util.Map;

public class Medium_11_RightView {

    public static void main(String[] args) {
        Node root = MyBinaryTree.generateBinaryTree();
        Map<Integer, Node> res = new HashMap<>();
        getRightView(root,0, res);
        System.out.print(res);

    }

    private static void getRightView(Node root, int level, Map<Integer, Node> res) {
        if(root==null) return;

        if(!res.containsKey(level)){
            res.put(level, root);
        }
        getRightView(root.right, level+1, res);
        getRightView(root.left, level+1, res);
    }
}
