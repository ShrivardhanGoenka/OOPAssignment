import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The {@code ViewBlockedDatesMenu} class provides the execution logics of the menu for viewing the date this user is not available.
 * The date shown will be all dates that some registered camp will be held on that day.
 */
public class ViewBlockedDatesMenu implements IMenu<Student>{
	/**
	 * Executes the menu logics for viewing the occupied date.
	 * @param studentObject                 student that runs the menu.
	 */
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
