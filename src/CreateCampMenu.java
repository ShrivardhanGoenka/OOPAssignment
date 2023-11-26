import java.util.ArrayList;
import java.util.Date;

/**
 * The {@code CreateCampMenu} class provides the execution logics of the menu for the staff to create a new camp.
 */
public class CreateCampMenu implements IMenu<Staff> {

    /** 
    * @return the menu description
    */
    @Override
    public String getMenuDescription() {
        return "Create Camp";
    }

    /**
    * Executes the menu logics for making an enquiry to the camp committee.
    * The logics are as follows:
	* 1. The program prompt the staff to enter camp details (camp name, description, dates, number of slots, visibility, faculty that the camp opens to and registration deadline).
	 * 2. The program will show the error message when there is an error parsing the input of each field.
	 * 3. If the camp is created successfully, the successful message will be shown to the staff.
	*/
    @Override
    public void runMenu(Staff userObject) throws CampException {
        ConsoleReaderDateList dateListReader = new ConsoleReaderDateList();
        ConsoleReaderString stringReader = new ConsoleReaderString();
        ConsoleReaderInteger intReader = new ConsoleReaderInteger();
        ConsoleReaderDate dateReader = new ConsoleReaderDate();
        try{
            System.out.println("<------------------Enter the details of the camp------------------>");
            System.out.print("Camp Name: ");
            String campName = stringReader.readFromConsole();
            System.out.print("Camp Description: ");
            String description = stringReader.readFromConsole();
            System.out.print("Camp Dates (Enter comma separated in dd/mm/yyyy format): ");
            ArrayList<Date> campDates = dateListReader.readFromConsole();
            System.out.print("Camp Location: ");
            String location = stringReader.readFromConsole();
            System.out.print("Camp Registration Deadline (dd/mm/yyyy): ");
            Date registrationDate = dateReader.readFromConsole();
            if(registrationDate.before(new Date())) throw new CampException("Registration date has passed");
            System.out.print("Camp Total Slots: ");
            int totalSlots = intReader.readFromConsole(0, Integer.MAX_VALUE);
            System.out.print("Camp Committee Slots: ");
            int committeeSlots = intReader.readFromConsole(0, 10);
            System.out.print("Camp Visibility (1 for visible, 0 for invisible): ");
            int visible = intReader.readFromConsole(0, 1);
            System.out.print("Camp Open to (1 for all, 2 for only your faculty): ");
            String faculty = intReader.readFromConsole(1,2) == 1 ? "*" : userObject.getFaculty();
            userObject.createCamp(campName, description, faculty.equals("*"), visible == 1, campDates, location, registrationDate, totalSlots, committeeSlots);
            System.out.println("Camp created successfully");
        } catch (InputException e) {
            System.out.println(e.getMessage());
        }

    }
}
