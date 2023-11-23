import java.util.ArrayList;
import java.util.stream.Collectors;

public class ViewBlockedDatesMenu extends IMenu<Student>{
    public void runMenu(Student studentObject) {
        System.out.println("Viewing Blocked Dates...");
        System.out.println("Blocked Dates: ");
        System.out.println(Parsers.datesToString(new ArrayList<>(studentObject.getBlockedDates().stream().sorted().collect(Collectors.toList()))));
    }

    /**
     * @return the menu description
     */
    public String getMenuDescription () {
        return "View Blocked Dates";
    }
}
