//package blink75;
//
//
////A singly linked list of length N is given such that each node contains an additional random
//// pointer, which could point to any node in the list, or null.
////
////    Write a method, making a deep copy of the list.
////    The deep copy should consist of N brand new nodes, where each new node has its value set to the value
////    of its corresponding original node.
////    Both the next and random pointer of the new nodes should point to new nodes in the copied list.
////    None of the pointers in the new list should point to nodes in the original list.
////
////public Node solution(Node head);
//
//
//
///// n1(n3) -> n2(n1) -> n3(null) -> n4(n1)
//
////Node -> next pointer, random pointer
//
//// Node head2 = new Node();
////head2.next = new Node();
////head2.random = new Node();
////head2.data = head.data;
////head2.next.data = head.next.data;
////head2.random.data = head.random.data;
////head = head.next;
////head2=head2.next;
//
// while (head !=null){
//   Node head2 = new Node();
//  head2.next = new Node();
//  head2.random = new Node();
//  head2.data = head.data;
//  head2.next.data = head.next.data;
//  head2.random.data = head.random.data;
//  head = head.next;
//  head2=head2.next;
//}
////
////}
//
//public class Test4 {
//
//
//   class Node {
//
//     public  int data;
//     public Node next;
//     public Node random;
//
//   }
//
//   ///Storege  .. list<Node> = <NOde1>, <Node2>, null,
//  ////
//
//  public static void main(String[] args) {
//    Node root =
//  }
//
//  public Node solution(Node head){
//    Node headR1 = head;
//    Node headRoot = new Node();
//
//    Node headR2 = headRoot;
//
//    Node head2 = headRoot;
//
//    while (head !=null){
//
//      head2.next = new Node();
//     // head2.random = new Node();
//      head2.data = head.data;
//      head2.next.data = head.next.data;
//     // head2.random.data = head.random.data;
//      head = head.next;
//      head2=head2.next;
//    }
//
//    while(headR1 !=null){
//
//      if(headR1.random!=null) {
//
//        int data = headR1.random.data;
//        Node temp = searchForData(data);
//        headR2
//      }
//    }
//
//
//
//    return  headRoot;
//
//  }
//
//
//}
