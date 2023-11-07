import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MM {

 public static void main(String[] args) throws ParseException {

  String str
      = "[[\"01-01-2023\", \"14:00\", \"ERROR\", \"failed\"], [\"01-01-2023\", \"15:00\", \"INFO\", \"established\"], [\"01-01-2023\", \"01:30\", \"ERROR\", \"failed\"]]";

  String[] strArr = str.split("],");
  String pattern = "MM-dd-yyyy hh:mm";
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

  for (String s : strArr) {
   s = s.replaceAll("\\[", "");
   s = s.replaceAll("\\]", "");
   String[] strAr = s.split(",");
   String dt = strAr[0].replaceAll("\"", "");
   String tm = strAr[1].replaceAll("\"", "");
   Date dttt = simpleDateFormat.parse(dt + " "+ tm);
   System.out.println(simpleDateFormat.parse(dt + " "+ tm));
  }
 }
}
