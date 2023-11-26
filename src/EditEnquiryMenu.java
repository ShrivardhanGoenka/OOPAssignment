import java.util.ArrayList;

/**
 * The {@code EditEnquiryMenu} class provides the execution logics of the menu for editing user's submitted enquiry.
 */
public class EditEnquiryMenu implements IMenu<Student> {

	/**
	 * Executes the menu logics for editing enquiry.
	 * The logics are as follows:
	 * 1. The program prompt the user to choose the enquiry to edit.
	 * 2. If the input choice is valid, the user will be prompted to enter the new enquiry message.
	 */
	public void runMenu(Student studentObject) {
		System.out.println("Choose the enquiry you want to edit: ");
		ArrayList<Enquiry> enquiries = studentObject.getUnprocessedEnquiries();
		if(enquiries.isEmpty()) {
			System.out.println("You have no open enquiries");
			return;
		}
		for(int i=0;i<enquiries.size();i++){
			System.out.println((i+1) + ": " + enquiries.get(i).getStringValue());
		}
		ConsoleReaderInteger cr = new ConsoleReaderInteger();
		ConsoleReaderString crs = new ConsoleReaderString();
		try{
			System.out.print("Your choice: ");
			int choice = cr.readFromConsole(1,enquiries.size());
			System.out.print("Enter edit: ");
			String enquiry = crs.readFromConsole();
			enquiries.get(choice-1).edit(enquiry);
			System.out.println("Enquiry edited successfully!");
		}catch(InputException e){
			System.out.println(e.getMessage());
		}
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Edit an enquiry";
	}
}
