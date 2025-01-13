package binary_search_tree;

import lombok.AllArgsConstructor;
import lombok.ToString;

public class MyBinarySearchTree {

    public BSNode root ;

    //     6
    //  4           10
    //3  5    9          50
    //     7    8    30       45

    public static MyBinarySearchTree generateBST(){
        MyBinarySearchTree msbst = new MyBinarySearchTree();
        msbst.insert_nonRec(6);
        msbst.insert_nonRec(4);
        msbst.insert_nonRec(10);
        msbst.insert_nonRec(9);
        msbst.insert_nonRec(7);
        msbst.insert_nonRec(3);
        msbst.insert_nonRec(50);
        msbst.insert_nonRec(5);
        msbst.insert_nonRec(8);
        msbst.insert_nonRec(7);
        //
        msbst.insert_nonRec(40);
        msbst.insert_nonRec(45);
        msbst.insert_nonRec(30);

        return msbst;
    }

    public static void main(String[] args) {
        MyBinarySearchTree mbst = generateBST();


       // System.out.print(mbst.root);
        mbst.deleteNode(10);
        System.out.print(mbst.root);


    }

    public  void deleteNode(int nodeData){

        BSNode bsNode = root;
        BSNode prevNode = null;

        while (bsNode != null && bsNode.data!=nodeData){
            prevNode=bsNode;
            if(bsNode.data > nodeData){
                bsNode= bsNode.left;
            } else {
                bsNode=bsNode.right;
            }
        }

        if(bsNode.left==null){
           if(prevNode.left !=null && prevNode.left.data==bsNode.data){
               prevNode.left=bsNode.right;
           } else {
               prevNode.right=bsNode.right;
           }
           return;
        }

        if(bsNode.right==null){
            if(prevNode.left !=null && prevNode.left.data==bsNode.data){
                prevNode.left=bsNode.left;
            } else {
                prevNode.right=bsNode.left;
            }
            return;
        }


        BSNode repNode = getLowestNode(bsNode.right);
        deleteNode(repNode.data);

        bsNode.data=repNode.data;


    }

    private BSNode getLowestNode(BSNode node) {
        if(node==null)
            return null;
       BSNode res = getLowestNode(node.left);
       if(res==null) {
           node.left=null;
           return node;
       }
       return res;
    }

    public BSNode insertREC(int nodeData, BSNode node){

        if(node==null) return null;
        BSNode temp;
        if(nodeData==node.data) return node;

        if(nodeData>node.data) {
            temp = insertREC(nodeData, node.right);
            if(temp==null){
                node.right=new BSNode(nodeData);
            }
            return node;
        } else {
            temp = insertREC(nodeData, node.left);
            if(temp==null){
                node.left=new BSNode(nodeData);
            }
            return node;

        }


    }

    public void insert_nonRec(int nodeData){

        if(root==null){
            root = new BSNode(nodeData);
            return;
        }

        BSNode node = root;
        BSNode preNode = null;

        while(node!=null){
            preNode=node;
            if(node.data==nodeData) return;
            if(node.data > nodeData)
                node = node.left;
            else
                node=node.right;
        }

        if(preNode.left==null){
            preNode.left=new BSNode(nodeData);
        } else {
            preNode.right=new BSNode(nodeData);
        }
    }


    @AllArgsConstructor
    //@ToString
    public static class BSNode{
        public BSNode left;
        public BSNode right;
        public int data;

        public BSNode(int data){
            this.data=data;
        }

        @Override
        public String toString(){
            return "Node{"+data+"}";
        }
    }
}


