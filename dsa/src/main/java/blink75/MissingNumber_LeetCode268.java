package blink75;

public class MissingNumber_LeetCode268 {

  static int N = 4;
  static int[] ARR = {0,3,2, 1};

  public static void main(String[] args) {
    sol();
  }

  static void sol(){
    int exOR = 0;
    for(int i=0; i<=N; i++){
      exOR = exOR^i;
    }
    int eXORM=0;
    for( int n : ARR){
      eXORM = eXORM^n;
    }

    int res = exOR^eXORM;
    System.out.println(res);
  }
}
