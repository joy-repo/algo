package test;

public class Test {

    public static void main(String[] args) {
        System.out.println("yuyiuyu");

        String input = "Geeks for Geeks";

        StringBuilder input1 = new StringBuilder();

        // append a string into StringBuilder input1
        input1.append(input);

        // reverse StringBuilder input1
        input1.reverse();

        // print reversed String
        String str = input1.toString();
    }
}
