package com.bst.techiedelight;


//http://www.techiedelight.com/check-if-two-binary-trees-are-identical-not-iterative-recursive/

import com.bst.BinaryTree;
import com.bst.Node;

import java.util.Stack;

public class IdenticalTree {

   static class Pair{
         Node n1;
         Node n2;

        public Pair(Node n1, Node n2) {
            this.n1 = n1;
            this.n2 = n2;
        }

        boolean checkEquals(){
             if(n1==null && n2==null)
                 return true;
             else if(n1==null || n2==null)
                 return false;

             if(n1.data!=n2.data)
                 return false;

             return  true;
         }


    }

    public static void main(String[] args) {
        Node root1 = BinaryTree.generateBinaryTree();
        Node root2 = BinaryTree.generateBinaryTree();

        solByRecc(root1,root2);
        solByIter(root1,root2);
    }

    private static boolean solByIter(Node root1, Node root2) {

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root1,root2));

        if(!stack.isEmpty()){
            Pair p = stack.pop();;
           if( p.checkEquals()==false) return false;
           Node n1 = p.n1;
           Node n2 = p.n2;

           if(n1!=null && n2!=null){
               stack.push(new Pair(n1.left,n2.left));
               stack.push(new Pair(n1.right,n2.right));
           }
        }
        return  true;
    }

    private static boolean solByRecc(Node root1, Node root2) {
        if(root1==null && root2==null)
            return true;
         else if(root1==null || root2==null)
            return false;

        if(root1.data!=root2.data)
            return false;

        return solByRecc(root1.left,root2.left) && solByRecc(root1.right,root2.right);
    }
}
