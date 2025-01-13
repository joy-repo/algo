public class TTTTTT {

    public static void main(String[] args) {
       String ff = compressedString("aabbbccc");
       System.out.println(ff);
    }

    public static String compressedString(String message) {

        String res = "";
        if(message.length()==0) return res;

        int i=0;

        while(i<message.length()){
            int cnt =1;
            while(i < message.length()-1 && message.charAt(i) == message.charAt(i+1)){
                i++;
                cnt++;
            }
            if(cnt > 1){
                res = res + message.charAt(i)+ cnt;
                i++;
            } else {
                res = res + message.charAt(i);
                i++;
            }

        }

        return res;

    }
}
