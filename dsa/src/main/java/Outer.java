public class Outer {

    public  static int temp1=1;
    private   static int temp2=2;
    public   int temp3=3;
    private    int temp4=4;

    public class Inner{

        private  int sumOf2(){
            return temp1+temp2;
        }
        private int sumOf3(){
            return temp3 + temp1;
        }

        private int sumOf4(){
            return temp1 + temp4;
        }
    }

//    public static void main(String[] args) {
//        Outer.Inner obj = new Outer.Inner();
//    }
}
