import java.util.ArrayList;
import java.util.Date;
//Complete
public class ViewCampStaffMenu extends IMenu<Staff>{

    @Override
    public String getMenuDescription() {
        return "View all camps";
    }

    @Override
    public void runMenu(Staff userObject) {
        ArrayList<Camp> camps = Registry.getAllCamps();
        SortableCamp sortableCamp = new SortableCamp();
        sortableCamp.runMenu(camps);
        System.out.println("-------------<List of Camps>-------------");
        CustomPrinterCamp printer = new CustomPrinterCamp();
        printer.print(camps);
    }
}
