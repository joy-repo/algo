//import java.util.Stack;
//
//public class ImplementQueueWithStack {
//
//
//
//  /// 6 7  ===stack
//
//  /// temps ===> 4 3 2  returned
//
//
//
//  MyStack  stack = new MyStack();
//
//  class MyStack{
//
//
//  };
//
//  MyStack  tempStack = new MyStack();
//
//  int size=0;
//  int sizeTemp=0;
//
//
//  // 1,2 , 3
//
//  public static void main(String[] args) {
//
//
//
//  }
//
//  public  void enque(int data){
//
//    stack.push(data);
//    size++;
//
//
//
//
//  }
//
//  public  int deque(){
//
//    if(sizeTemp!=0){
//      sizeTemp--;
//      return tempStack.pop();
//    }
//
//    while (size>0){
//      int data = stack.pop();
//      tempStack.push(data);
//      sizeTemp++;
//      size--;
//    }
//
//    sizeTemp--;
//    return tempStack.pop();
//
//  }
//}
