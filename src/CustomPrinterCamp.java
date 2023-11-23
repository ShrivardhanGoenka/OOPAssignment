import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class provides a method to print all camp details (name, camp ID, description, location, etc.) to the user.
 */
public class CustomPrinterCamp extends ICustomPrinter<Camp>{

    /**
     * Prints details of a camp to the console.
     */
    public void print(Camp camp){
        System.out.println("Camp Name: " + camp.getCampName());
        System.out.println("Camp ID: " + camp.getCampID());
        System.out.println("Camp Description: " + camp.getDescription());
        System.out.print("Camp Dates: " );
        for (Date date: camp.getCampDates())
            System.out.print(DBInterface.returnDateVal(date) + ", ");
        System.out.println();
        System.out.println("Camp Location: " + camp.getLocation());
        System.out.println("Camp Registration Deadline: " + DBInterface.returnDateVal( camp.getRegistrationDeadline()));
        System.out.println("Camp Total Slots: " + camp.getTotalSlots());
        System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
        System.out.println("Camp Staff ID: " + camp.getStaffID());
        System.out.println("Camp Visibility: " + camp.isVisible());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Prints a multiple camps with an input type of ArrayList of Camp to the console.
     */
    public void print(ArrayList<Camp> list){
        for(Camp camp:list) print(camp);
    }
}
