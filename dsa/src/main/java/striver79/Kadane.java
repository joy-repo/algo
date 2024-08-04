package striver79;

public class Kadane {

    static int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4};

    public static void main(String[] args) {
        kadane();
    }

    private static void kadane() {
        
        int res = 0;
        int tempRes = 0;
        int start = 0;
        int end = 0;
        
        for(int c=0;c< arr.length;c++){
            tempRes = tempRes + arr[c];

            if(res<tempRes){
                res = tempRes;
                end=c;
            }
            if(tempRes < 0){
                tempRes = 0;
                start=c+1;
                end=c+1;
            }
        }
        System.out.println(res);
        System.out.println("Start :"+start+", end : "+end);

    }
}
