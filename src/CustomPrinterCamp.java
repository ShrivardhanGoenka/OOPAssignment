import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class CustomPrinterCamp extends ICustomPrinter<Camp>{
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
    public void print(ArrayList<Camp> list){
        for(Camp camp:list) print(camp);
    }
}
