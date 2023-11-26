import java.util.ArrayList;

/**
 * The {@code DeleteEnquiryMenu} class provides the execution logics of the menu for deleting the user's submitted enquiries.
 */
public class DeleteEnquiryMenu implements IMenu<Student> {
	/**
	 * Executes the menu logics for deleing enquiries.
	 * The logics are as follows:
	 * 1. The program prompt the user to choose the enquiry to delete.
	 * 2. The program shows the message to indicate whether the deletion is successful.
	 * 3. If the user enter the invalid choice, the user will have to enter the input again.
	 */
	public void runMenu(Student studentObject) {
		System.out.println("Choose the enquiry you want to delete: ");
		ArrayList<Enquiry> enquiries = studentObject.getUnprocessedEnquiries();
		if(enquiries.isEmpty()) {
			System.out.println("You have no open enquiries");
			return;
		}
		for(int i=0;i<enquiries.size();i++){
			System.out.println((i+1) + ": " + enquiries.get(i).getStringValue());
		}
		ConsoleReaderInteger consoleReaderInteger = new ConsoleReaderInteger();
		try {
			System.out.print("Your choice: ");
			int tchoice = consoleReaderInteger.readFromConsole(1, enquiries.size());
			studentObject.deleteEnquiry(enquiries.get(tchoice-1).getID());
			System.out.println("Enquiry deleted successfully!");
		} catch (InputException e) {
			System.out.println(e.getMessage());
		}
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Delete Enquiry";
	}
}
