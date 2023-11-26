import java.util.ArrayList;
/**
 * The {@code ViewCampStaffMenu} class provides the execution logics of the menu for viewing all of the camp in the camp management system.
 */
public class ViewCampStaffMenu implements IMenu<Staff>{

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
        ArrayList<Camp> camps = RegistryFactory.campRegistry.getAllEntries();
        SortableCamp sortableCamp = new SortableCamp();
        sortableCamp.runMenu(camps);
        CustomPrinterCamp printer = new CustomPrinterCamp();
        printer.print(camps,"List of Camps");
    }
}
