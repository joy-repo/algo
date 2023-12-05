package blink75;

public class Test11 {

  //whatIsYourName -> WHAT_IS_YOUR_NAME

  public static String STR = "whatIsYourName";

  public static void main(String[] args) {

    solu();

  }

  private static void solu() {

    String res = "";

    for(char c : STR.toCharArray()){
      if(Character.isLowerCase(c)){
        res = res + Character.toUpperCase(c);
      } else {
        res = res + "_"+ Character.toUpperCase(c);
      }
    }
    System.out.println(res);
  }
}
