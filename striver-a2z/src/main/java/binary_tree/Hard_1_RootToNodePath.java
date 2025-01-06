package binary_tree;
import binary_tree.MyBinaryTree.Node;

public class Hard_1_RootToNodePath {

    public static void main(String[] args) {
        Node root = MyBinaryTree.generateBinaryTree();
        String res = root2Nodepath(root, 21 );
        System.out.print(res);

    }

    private static String root2Nodepath(Node root, int nodeData) {

        if(root==null) return "";

        if(root.data==nodeData){
            return root.data+", ";
        }

        String rigthP = root2Nodepath(root.right, nodeData);
        if(!rigthP.equals("")){
            return root.data+", "+ rigthP;
        }



        String leftP =root2Nodepath(root.left, nodeData);
        if(!leftP.equals("")){
            return root.data+", "+ leftP;
        }
        return "";

    }
}
