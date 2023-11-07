public class GGHG {

    int num1=3;
    int num2=5;

    GGHG(int num1, int num2){
        if(num2<30){
            this.num2=num2;
        }
        this.num1=num1;
    }

    public static void main(String[] args) {
        GGHG s1 = new GGHG(9,10);
        GGHG s2 = new GGHG(12,22);
        System.out.println(s1.num1);
        System.out.println(s1.num2);
        System.out.println(s2.num1);
        System.out.println(s2.num2);

        System.out.print(10*20 + "ABC");
        System.out.println( "DEF" + 10*20 );
    }
}
