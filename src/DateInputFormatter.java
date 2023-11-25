import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * The {@code DateInputFormatter} class provides a method to parse a comma-separated string
 * of dates list and converts into {@code ArrayList} of Date objects.
 */
public class DateInputFormatter {	
    /**
     * Parses a comma-separated string representing dates and converts into ArrayList of Date objects.
     *
     * @param input 		The comma-separated string of dates list.
     * @return {@code ArrayList} of {@code Date} objects.
     * @throws ParseException If there is an error parsing the date strings.
     */
    public static ArrayList<Date> formatDateInput(String input) throws ParseException{
        ArrayList<Date> dates = new ArrayList<>();

        String[] dateStrings = input.split(",");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (String dateString : dateStrings) {
            Date date = dateFormat.parse(dateString.trim());
            dates.add(date);
        }
        return dates;
    }
}
