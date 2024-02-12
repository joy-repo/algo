import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MS2 {

  public static void main(String[] args) {
    int res = solution("x.xx...x...x..x", 6);
    System.out.println(res);
  }

  public static int solution(String S, int B) {
    List<Integer> bunches = new ArrayList<>();
    char prev='.';
    char[] cArr = S.toCharArray();
    for(int i=0; i< cArr.length; i++){
      int count = 0;
      while (i< cArr.length && cArr[i]=='x' ){
        count++;
        i++;
      }
      if(count>0) bunches.add(count);

    }
    Collections.sort(bunches, Collections.reverseOrder());
    System.out.println(bunches);
    int res = 0;

    while(B>1 && !bunches.isEmpty()){
      int pothCount =bunches.get(0);
      if(B>pothCount+1){
        B=B-pothCount-1;
        bunches.remove(0);
        res= res+pothCount;
      } else {
        int temp = B-1;
        res = res + temp;
        B=0;
      }
    }
    return res;

  }
}
