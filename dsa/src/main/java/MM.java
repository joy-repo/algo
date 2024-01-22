import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MM {

 public static void main(String[] args) throws ParseException {

//  String str
//      = "[[\"01-01-2023\", \"14:00\", \"ERROR\", \"failed\"], [\"01-01-2023\", \"15:00\", \"INFO\", \"established\"], [\"01-01-2023\", \"01:30\", \"ERROR\", \"failed\"]]";
//
//  String[] strArr = str.split("],");
//  String pattern = "MM-dd-yyyy hh:mm";
//  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//
//  for (String s : strArr) {
//   s = s.replaceAll("\\[", "");
//   s = s.replaceAll("\\]", "");
//   String[] strAr = s.split(",");
//   String dt = strAr[0].replaceAll("\"", "");
//   String tm = strAr[1].replaceAll("\"", "");
//   Date dttt = simpleDateFormat.parse(dt + " "+ tm);
//   System.out.println(simpleDateFormat.parse(dt + " "+ tm));

  LinkedHashMap<Integer, Integer> lmap = new LinkedHashMap<>();

  lmap.put(10,1);
  lmap.put(20, 2);
  lmap.put(30, 3);
  lmap.put(40, 4);

  Set<Integer> sss = lmap.keySet();

  //Integer[] keyArr =


  }
 }

