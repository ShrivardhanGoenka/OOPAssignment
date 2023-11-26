import java.util.ArrayList;
/**
 * The {@code ViewCreatedCampsStaffMenu} class provides the execution logics of the menu for viewing all camps created by a specific staff account.
 */
public class ViewCreatedCampsStaffMenu implements IMenu<Staff>{

	/**
	 * @return String of the menu description.
	 */
    @Override
    public String getMenuDescription() {
        return "View Created Camps";
    }

	/**
	 * Executes the menu logics for viewing the list of camps created by a specific staff account.
	 * @param userObject 			The staff that runs the menu.
	 */
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
