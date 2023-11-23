import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * The {@code DeleteEnquiryMenu} class provides the execution logics of the menu for deleting the user's submitted enquiries.
 */
public class DeleteEnquiryMenu extends IMenu<Student> {

	/** 
	 * A buffer reader to handle the user input.
	 */
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	
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
                    System.out.print("Your choice: ");
		    try {
			    int tchoice = Integer.parseInt(br.readLine());
			    if(tchoice < 1 || tchoice > enquiries.size()){
				System.out.println("Invalid choice");
				return;
			    }
			    studentObject.deleteEnquiry(enquiries.get(tchoice-1).getID());
			    System.out.println("Enquiry deleted successfully!");
		    } catch (IOException e) {
			e.printStackTrace();
		    }
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Delete Enquiry";
	}
}
