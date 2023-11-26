import java.util.ArrayList;
/**
 * The {@code ViewCampStudentMenu} class provides the execution logics of the menu for viewing all the camps visible to the specific student.
 */
public class ViewCampStudentMenu implements IMenu<Student> {

	/**
	 * Executes the menu logics for viewing the camp.
	 * @param studentObject 		The student that runs the menu.
	 */
	public void runMenu(Student studentObject) {
		ArrayList<Camp> camps = Filters.filterStudentCamps(studentObject.getFaculty());
		if (camps.isEmpty()) {
			System.out.println("There are no camps available for your faculty.");
			return;
		}
		SortableCamp sortableCamp = new SortableCamp();
		sortableCamp.runMenu(camps);
	    CustomPrinterCamp printer = new CustomPrinterCamp();
		printer.print(camps, "List of Available Camps");
	}

	/**
	 * @return String of the menu description.
	 */
	public String getMenuDescription() {
		return "View all camps";
	}
}
