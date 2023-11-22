import java.util.Date;
import java.util.ArrayList;
//Complete
public class ViewCampStudentMenu extends IMenu<Student> {

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

	public String getMenuDescription() {
		return "View all camps";
	}
}
