package blink75;

import java.util.HashMap;
import java.util.Map;

public class Leet6 {
//PAHNAPLSIIGYIR
//PAHNAPLSIIGYIR

//PINALSIGYAHRPI
//PINALSIGYAHRPI
  public static void main(String[] args) {
    convert("A", 4);
  }

  public static String convert(String s, int numRows) {

    Map<Integer,String> res = new HashMap<>();
    int row=0;
    if(numRows==1) return s;


    for(int i =0 ; i<s.length(); i++){
      if(row==0){
        while(row<numRows){
          if(s.length()<= i+row) break;
          res.putIfAbsent(row, "");
          res.put(row,res.get(row)+s.charAt(i+row));
          row++;
        }
        row--;
        i=i+row;
        row--;

      }else{
        res.put(row,res.get(row)+s.charAt(i));

        row--;
      }

    }

    String ans = "";
    for(int k=0;k<numRows;k++){
      if(res.get(k)!=null)
        ans = ans+res.get(k);
    }
    System.out.println(ans);
    return ans;
  }
}
