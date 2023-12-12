import java.util.ArrayList;
import java.util.List;

public class TTest2 {
//[[1,3],[2,6],[8,16],[15,18]]
  static int[][] INPUT = {new int[]{1,3},new int[]{4,6},new int[]{5,11},new int[]{15,18}};

  public static void main(String[] args) {

    solu();

  }

  private static void solu(){

    List<List<Integer>> res = new ArrayList<>();

    int[] pre = INPUT[0];
    int t0=pre[0];
    int t1=pre[1];

    for(int i = 1; i< INPUT.length; i++){
      int[] curr = INPUT[i];
      if(t1>curr[0]){
          t1=curr[1];
      } else {
        List<Integer> ll = new ArrayList<>();
        ll.add(t0);
        ll.add(t1);
        res.add(ll);
        t0=curr[0];
        t1=curr[1];
      }
    }

    int k = res.get(res.size()-1).get(1);
    if( k != INPUT[INPUT.length-1][1]){
      List<Integer> ll = new ArrayList<>();
      ll.add(t0);
      ll.add(t1);
      res.add(ll);
    }
    res.stream().forEach(e-> {System.out.println(e.get(0) +", " +e.get(1));});
  }
}
