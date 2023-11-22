import java.util.Date;
import java.util.ArrayList;
//Complete
public class ViewMyRegisteredCampMenu extends IMenu<Student> {

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

	public String getMenuDescription() {
		return "View my registered camps";
	}
}
