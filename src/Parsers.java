import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parsers {
    public static Date parseDate(String dateStr) throws ParseException {
        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = dateFormat.parse(dateStr.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }

    public static ArrayList<Date> parseDates(String dates) throws ParseException {
        if(dates == null || dates.equals("")) return new ArrayList<Date>();
        ArrayList<Date> dateList = new ArrayList<>();
        try {
            String[] dateArr = dates.split(",");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            for (String dateStr : dateArr) {
                Date date = dateFormat.parse(dateStr.trim());
                dateList.add(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateList;
    }
    public static ArrayList<Integer> parseIntegerList(String l) throws NumberFormatException{
        if(l == null || l.isEmpty()){
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        String[] arr = l.split(",");
        for(String s : arr){
            if(s == null || s.isEmpty()) continue;
            list.add(Integer.parseInt(s.trim()));
        }
        return list;
    }
    public static ArrayList<String> parseStringList(String l) {
        if(l == null || l.isEmpty()){
            return new ArrayList<String>();
        }
        ArrayList<String> list = new ArrayList<String>();
        String[] arr = l.split(",");
        for(String s : arr){
            if(s == null || s.isEmpty()) continue;
            list.add(s.trim());
        }
        return list;
    }

    static String dateToString(Date date)  {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
    static String datesToString(ArrayList<Date> dates) {
        String dateStr = "";
        for(Date date : dates){
            dateStr += dateToString(date) + ",";
        }
        if(dateStr.isEmpty()) return dateStr;
        return dateStr.substring(0,dateStr.length()-1);
    }
    static String integerListToString(ArrayList<Integer> list){
        String str = "";
        for(Integer i : list){
            str += i + ",";
        }
        if(str.isEmpty()) return str;
        return str.substring(0,str.length()-1);
    }
    static String stringListToString(ArrayList<String> list){
        String str = "";
        for(String s : list){
            str += s + ",";
        }
        if(str.isEmpty()) return str;
        return str.substring(0,str.length()-1);
    }
}
