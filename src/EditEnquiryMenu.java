import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class EditEnquiryMenu extends IMenu<Student> {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
					System.out.print("Your choice: ");
		    try {
			    int tchoice = Integer.parseInt(br.readLine());
			    if(tchoice < 1 || tchoice > enquiries.size()){
				System.out.println("Invalid choice");
				return;
			    }
			    System.out.print("Enter edit: ");
			    String tenquiry = br.readLine();
			    enquiries.get(tchoice-1).edit(tenquiry);
			    System.out.println("Enquiry edited successfully!");
		    } catch (IOException e) {
			e.printStackTrace();
		    }
	}

	public String getMenuDescription() {
		return "Edit an enquiry";
	}
}
