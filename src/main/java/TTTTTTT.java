import java.util.Arrays;

public class TTTTTTT {

    public static void main(String[] args) {
        int[] org = new int[]{1,2,3};
        int[] oribbb = Arrays.copyOf(org,5);
        int[] copy = Arrays.copyOfRange(oribbb, 0,4);
        for (int i =0; i< copy.length; i++){
            System.out.println(copy[i] + "");
        }
    }


}
