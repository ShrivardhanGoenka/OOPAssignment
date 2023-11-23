import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.text.ParseException;

/**
 * The {@link CreateCampMenuclass} provides the execution logics of the menu for the staff to create a new camp.
 */
public class CreateCampMenu extends IMenu<Staff> {

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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("<------------------Enter the details of the camp------------------>");
        System.out.print("Camp Name: ");
        String campName = "";
        try {
            campName = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.print("Camp Description: ");
        String description = "";
        try {
            description = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.print("Camp Dates (dd/mm/yyyy): ");
        String dates = "";
        ArrayList<Date> campDates;
        try {
            dates = br.readLine();
            campDates = DateInputFormatter.formatDateInput(dates);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ParseException e) {
            System.out.println("Invalid date format");
            return;
        }
        System.out.print("Camp Location: ");
        String location = "";
        try {
            location = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.print("Camp Registration Deadline (dd/mm/yyyy): ");
        String registrationDeadline = "";
        Date registrationDate;
        try {
            registrationDeadline = br.readLine();
            registrationDate = DateInputFormatter.formatDateInput(registrationDeadline).get(0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ParseException e) {
            System.out.println("Invalid date format");
            return;
        }
        System.out.print("Camp Total Slots: ");
        int totalSlots = 0;
        try {
            totalSlots = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("Invalid choice");
            return;
        }
        if (totalSlots < 0) {
            System.out.println("Invalid number of slots");
            return;
        }
        System.out.print("Camp Committee Slots: ");
        int committeeSlots = 0;
        try {
            committeeSlots = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("Invalid choice");
            return;
        }
        if (committeeSlots < 0) {
            System.out.println("Invalid number of slots");
            return;
        }
        System.out.print("Camp Visibility (1 for visible, 0 for invisible): ");
        int visible = 0;
        try {
            visible = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("Invalid choice");
            return;
        }
        if (visible != 0 && visible != 1) {
            System.out.println("Invalid choice");
            return;
        }
        System.out.print("Camp Open to (1 for all, 2 for only your faculty): ");
        String faculty = "";
        try {
            faculty = br.readLine();
            if (!faculty.equals("1") && !faculty.equals("2")) {
                System.out.println("Invalid choice");
                return;
            }
            if(faculty.equals("2")){
                faculty = userObject.getFaculty();
            }
            else{
                faculty = "*";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        userObject.createCamp(campName, description, faculty.equals("*"), visible == 1, campDates, location, registrationDate, totalSlots, committeeSlots);
        System.out.println("Camp created successfully");

    }
}
