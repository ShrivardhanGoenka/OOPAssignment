import java.util.ArrayList;
import java.util.Date;
public class ViewCreatedCampsStaffMenu extends IMenu<Staff>{

    @Override
    public String getMenuDescription() {
        return "View Created Camps";
    }

    @Override
    public void runMenu(Staff userObject) throws CampException {
        ArrayList<Camp> camps = userObject.createdCamps();
        if(camps.isEmpty()){
            System.out.println("You have not created any camps yet.");
            return;
        }
        System.out.println("-------------<List of Camps Created>-------------");
        for(Camp camp: camps){
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

    }
}
