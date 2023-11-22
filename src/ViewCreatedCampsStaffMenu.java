import java.util.ArrayList;
import java.util.Date;
//Complete
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
        SortableCamp sortableCamp = new SortableCamp();
        sortableCamp.runMenu(camps);
        System.out.println("-------------<List of Camps Created>-------------");
        CustomPrinterCamp printer = new CustomPrinterCamp();
        printer.print(camps);

    }
}
