package blink75;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NonReeatingChar1st {

  public static String STR = "rtrtyipobnvbnvqew";

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {



    char [] cArr = STR.toCharArray();
    int[] irr = new int[3];

//    Map<String, Integer> map = STR.chars().collect(Collectors.groupingBy(Function.identity(), ))
//
//
//    char ans =   input.chars().mapToObj(e -> (char)e)

   //Map<String,Integer> mm - STR.chars().collect(Collectors.counting())

  Map<Character,Long> m = STR.chars().mapToObj(e -> (char)e).collect(
    Collectors.groupingBy(
            Function.identity(),
            LinkedHashMap::new,
            Collectors.counting()
        ));

    char c =m.entrySet()
        .stream()
        .filter(e -> e.getValue() == 1)
        .map(e -> e.getKey())
        .findFirst().get();

   System.out.println(c);

  }
}
