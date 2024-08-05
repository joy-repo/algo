package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2 {

    public static void main(String[] args) throws ParseException {
        final SimpleDateFormat outputDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = outputDateFormatter.parse("22/02/2024");
        System.out.println(date);
    }
}
