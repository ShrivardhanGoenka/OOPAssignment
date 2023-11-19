import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateInputFormatter {
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