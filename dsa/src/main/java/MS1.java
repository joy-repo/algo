import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MS1 {

  public static void main(String[] args) {

    int res = solution("aadDAbcCABBcD");
    System.out.println(res);


  }

  public static int solution(String letters) {
    Map<Character, Integer> small = new HashMap<>();
    Map<Character, Integer> caps = new HashMap<>();
    char[] arr = letters.toCharArray();
    for(int i=0; i< arr.length; i++){
      //if(small.containsKey(arr[i])) continue;
      if(caps.containsKey(arr[i])) continue;
      if(Character.isLowerCase(arr[i])){
        small.put(arr[i],i);
      } else {
        caps.put(arr[i], i);
      }
    }

    int res =0;
    Set<Character> checked = new HashSet<>();


    for(int i=0; i< arr.length; i++){

      if(Character.isLowerCase(arr[i]) && !checked.contains(arr[i])){
        checked.add(arr[i]);
        if(caps.containsKey(Character.toUpperCase(arr[i]))){
          if(small.get(arr[i])<caps.get(Character.toUpperCase(arr[i]))){


            res++;
          }
        }
      }
    }
    return res;
  }
}
