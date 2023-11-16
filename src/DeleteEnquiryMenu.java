import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class DeleteEnquiryMenu implements IMenu {
	private Student studentObject; 

        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public DeleteEnquiryMenu (Student studentObject) {
		this.studentObject = studentObject;
	}

	public void runMenu() {
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

	public String getMenuDescription() {
		return "Delete Enquiry";
	}
}
