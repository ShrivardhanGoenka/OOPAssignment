/**
 * This class provides a method to print all camp details (name, camp ID, description, location, etc.) to the user.
 */
public class CustomPrinterCamp extends CustomPrinter<Camp> {

    /**
     * Prints details of a camp to the console.
     */
    public void print(Camp camp){
        System.out.println("Camp Name: " + camp.getCampName());
        System.out.println("Camp ID: " + camp.getCampID());
        System.out.println("Camp Description: " + camp.getDescription());
        System.out.println("Camp Dates: " + Parsers.datesToString(camp.getCampDates()));
        System.out.println("Camp Location: " + camp.getLocation());
        System.out.println("Camp Registration Deadline: " + Parsers.dateToString(camp.getRegistrationDeadline()));
        System.out.println("Camp Total Slots: " + camp.getTotalSlots());
        System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
        System.out.println("Camp Staff ID: " + camp.getStaffID());
        System.out.println("Camp Visibility: " + camp.isVisible());
        System.out.println("-----------------------------------------------");
    }

}
