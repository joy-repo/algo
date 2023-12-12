
////
//// Select * from (select salary  from emp order by sal desc Limit=3) e  order by e.salary asc Limit=1;

// Select * from Emp order by id Limit :initial, Floor((select count(*) from emp) * 0.25)



public class SumLargeNums {


  /// "3333311111111111"  -> 9

  /// "44422222221111"; ---> 8
  ///res
  /// val -> "7" + carry -> "1"
/// res = val + res
  static String STR1 = "9";
  static String STR2 =   "4";
 // Output : 3377733333332222

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {

    int len = STR1.length() > STR2.length() ? STR1.length() : STR2.length();

    String res = "";

    int count = len-1;
    int carry = 0;
    int l1 = STR1.length()-1;
    int l2 = STR2.length()-1;

    while(count>=0) {
      int s1=0;
      int s2=0;

      if(l1>=0) {
        s1 = Integer.parseInt(STR1.charAt(l1) + "");
      }

      if(l2>=0) {
        s2 = Integer.parseInt(STR2.charAt(l2) + "");
      }

      int sum = s1 + s2 + carry;

      if ((sum + "").length() > 1) {

        char[] carr = (sum + "").toCharArray();
        res = carr[1]+res;
        carry = Integer.parseInt(carr[0] + "");
      } else {
        res =  (sum + "")+res ;
        carry = 0;
      }
      l1--;
      l2--;
      count--;
    }
    if(carry>0){
      res = carry+res;
    }

    System.out.println(res);

    }


  }

