package test;

public class BB {

    public static void main(String[] args) {
        long phnum = 8618687736l;
        long sum = 0;
        while(phnum>0){
            sum = sum + phnum%10;
            phnum=phnum/10;

        }
        System.out.println(sum);
    }
}
