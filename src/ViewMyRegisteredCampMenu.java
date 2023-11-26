import java.util.ArrayList;
/**
 * The {@code ViewMyRegisteredCampMenu} class provides the execution logics of the menu for viewing all the camp the student has registered as an attendee
 */
public class ViewMyRegisteredCampMenu implements IMenu<Student> {
	/**
	 * Executes the menu logics for viewing camp that a specific student has registered to.
	 * The user will be able to sort the list of camp based on user input attribute of camp chosen.
	 * @param studentObject 		The student that runs the menu.
	 */
	public void runMenu(Student studentObject) {
		ArrayList<Camp> camps = studentObject.registeredCamps();
		CustomPrinterCamp printer = new CustomPrinterCamp();
		if(camps.isEmpty()){
			System.out.println("You have not registered for any camps yet.");
			return;
		}
		SortableCamp sortableCamp = new SortableCamp();
		sortableCamp.runMenu(camps);
		System.out.println("-------------<List of Camps Registered>-------------");
		printer.print(camps);
	}

	/**
	 * @return String of the menu description.
	 */
	public String getMenuDescription() {
		return "View my registered camps as attendee";
	}
}
