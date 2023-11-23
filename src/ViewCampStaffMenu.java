import java.util.ArrayList;
import java.util.Date;
//Complete
/**
 * The {@code ViewCampStaffMenu} class provides the execution logics of the menu for viewing all of the camp in the camp management system.
 */
public class ViewCampStaffMenu extends IMenu<Staff>{

	/**
	 * @return String of the menu description.
	 */
    @Override
    public String getMenuDescription() {
        return "View all camps";
    }

	/**
	 * Executes the menu logics for viewing all camps.
	 * @param userObject 		The staff that runs the menu.
	 */
    @Override
    public void runMenu(Staff userObject) {
        ArrayList<Camp> camps = Registry.getAllCamps();
        SortableCamp sortableCamp = new SortableCamp();
        sortableCamp.runMenu(camps);
        CustomPrinterCamp printer = new CustomPrinterCamp();
        printer.print(camps,"List of Camps");
    }
}
