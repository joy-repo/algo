public class Prob1 {


  static class Node {

    public int data;
    public Node next;

    public Node(int data){
      this.data=data;
    }
  }

  public static void main(String[] args) {

    Node root = new Node(1);
    root.next= new Node(2);


    sol(root);


  }

  private static boolean sol(Node root) {


    Node fastP = root.next.next;
    Node slowP = root.next;

    while (fastP.next.next!=null){

      if(slowP.data==fastP.data) return true;

      fastP= fastP.next.next;
      slowP=slowP.next;

    }
    return false;

  }
}
