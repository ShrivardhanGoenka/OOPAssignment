import java.util.Date;
import java.util.ArrayList;
/**
 * The {@code ViewCampStudentMenu} class provides the execution logics of the menu for viewing the camp visible to the specific student.
 */
public class ViewCampStudentMenu extends IMenu<Student> {

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
	    System.out.println("-------------<List of Available Camp>-------------");
	    CustomPrinterCamp printer = new CustomPrinterCamp();
		printer.print(camps);
	}

	/**
	 * @return String of the menu description.
	 */
	public String getMenuDescription() {
		return "View all camps";
	}
}
