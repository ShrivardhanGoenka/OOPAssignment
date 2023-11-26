// javadoc
import java.util.ArrayList;

/**
 * The {@code RaiseEnquiryMenu} class provides the execution logics of the menu for submitting an enquiry to a camp committee.
 */
public class RaiseEnquiryMenu implements IMenu<Student> {
	/**
	 * Executes the menu logics for making an enquiry to the camp committee.
	 * The logics are as follows:
	 * 1. The program shows camp this student has registered.
	 * 2. The program will prompt the student to choose the camp to raise an enquiry to the camp committee.
	 * 3. The program will prompt the student to enter enquiry message.
	 */
	public void runMenu(Student studentObject) {
		System.out.println("Choose the camp you want to raise an enquiry for: ");
		ArrayList<Camp> camps = Filters.filterStudentCamps(studentObject.getFaculty());
		for(int i=0;i<camps.size();i++){
			System.out.println((i+1) + ". " + camps.get(i).getCampName());
		}
		ConsoleReaderString crs = new ConsoleReaderString();
		ConsoleReaderInteger cri = new ConsoleReaderInteger();
		try {
			System.out.print("Your choice: ");
			int tempchoice = cri.readFromConsole(1, camps.size());
			Camp chosenCamp = camps.get(tempchoice-1);
			System.out.print("Enter your enquiry: ");
			String enquiry = crs.readFromConsole();
			studentObject.raiseEnquiry(enquiry, chosenCamp.getCampID());
			System.out.println("Enquiry raised successfully!");
		} catch (InputException e) {
			System.out.println(e.getMessage());
		}
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Raise an enquiry";
	}
}
