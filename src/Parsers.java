import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * The {@code Parsers} provides the methods to validate, parse and convert the value between string and each object type
 * This include Date and Ingeter.
 */
public class Parsers {
    /**
     * Converts the date string in "dd/mm/yyyy" format to the {@code Date} type
     * @param dateStr 		The string of date to format to Date object
     * @throws ParseException 	If the date is not in the correct format
     * @return {@code Date}
     */
    public static Date parseDate(String dateStr) throws ParseException {
        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = dateFormat.parse(dateStr.trim());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return date;
    }

    /**
     * Converts the date list of string in "dd/mm/yyyy" format separated by "," to the {@code ArrayList<Date>} type
     * @param dates 		The strinf list of date to format to ArrayList of Date object
     * @throws ParseException If the date list is not in the correct format
     * @return {@code ArrayList<Date>}
     */
    public static ArrayList<Date> parseDates(String dates) throws ParseException {
        if(dates == null || dates.equals("")) return new ArrayList<Date>();
        ArrayList<Date> dateList = new ArrayList<>();
        try {
            String[] dateArr = dates.split(",");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            for (String dateStr : dateArr) {
                Date date = dateFormat.parse(dateStr.trim());
                dateList.add(date);
            }
        } catch (ParseException e) {
            throw new ParseException("Invalid date format",0);
        }


        return dateList;
    }

    /**
     * Converts the string of number list to ArrayList of Integer
     * @param integerList 			The string of integer list (separated by ",")
     * @throws NumberFormatException 		If there is an error parsing the string list
     * @return {@code ArrayList<Integer>}
     */
    public static ArrayList<Integer> parseIntegerList(String integerList) throws NumberFormatException{
        if(integerList == null || integerList.isEmpty()){
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        String[] arr = integerList.split(",");
        for(String s : arr){
            if(s == null || s.isEmpty()) continue;
            list.add(Integer.parseInt(s.trim()));
        }
        return list;
    }
    /**
     * Converts the string of string list to ArrayList of string
     * @param stringList 			The string of text list (separated by ",")
     * @return {@code ArrayList<String>}
     */
    public static ArrayList<String> parseStringList(String stringList) {
        if(stringList == null || stringList.isEmpty()){
            return new ArrayList<String>();
        }
        ArrayList<String> list = new ArrayList<String>();
        String[] arr = stringList.split(",");
        for(String s : arr){
            if(s == null || s.isEmpty()) continue;
            list.add(s.trim());
        }
        return list;
    }

    /**
     * Converts the date of type {@code Date} to string in "dd/mm/yyyy" format
     * @param date 				The Date object to convert to string
     * @return {@code String}
     */
    public static String dateToString(Date date)  {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    /**
     * Converts the date list of type {@code ArrayList<Date>} to string in "dd/mm/yyyy" format separated by ","
     * @param dates 				The ArrayList of Date object to format to string
     * @return {@code String}
     */
    public static String datesToString(ArrayList<Date> dates) {
        String dateStr = "";
        for(Date date : dates){
            dateStr += dateToString(date) + ",";
        }
        if(dateStr.isEmpty()) return dateStr;
        return dateStr.substring(0,dateStr.length()-1);
    }

    /**
     * Converts the integer list of type {@code ArrayList<Integer>} to string of number separated by ","
     * @param list 				The list of integer to convert to string
     * @return {@code String}
     */
    public static String integerListToString(ArrayList<Integer> list){
        String str = "";
        for(Integer i : list){
            str += i + ",";
        }
        if(str.isEmpty()) return str;
        return str.substring(0,str.length()-1);
    }

    /**
     * Converts the String list of type {@code ArrayList<String>} to string of each string separated by ","
     * @param list 				The list of text to convert to one single string
     * @return {@code String}
     */
    public static String stringListToString(ArrayList<String> list){
        String str = "";
        for(String s : list){
            str += s + ",";
        }
        if(str.isEmpty()) return str;
        return str.substring(0,str.length()-1);
    }
}
