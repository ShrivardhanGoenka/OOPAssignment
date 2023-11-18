import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReplyToEnquiriesMenu extends IMenu<CampCommittee> {
	
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void runMenu(CampCommittee committeeObject) {
		System.out.printf("----------<List of Unprocessed Enquiries for Camp %s>-----------\n", committeeObject.getCamp().getCampName());
		int indexIterator=0;
		ArrayList<Enquiry> enquiries = committeeObject.getUnprocessedAttendeeEnquiry();
		if(enquiries.isEmpty()) {
			System.out.println("You have no open enquiries");
			return;
		}
		for (Enquiry enquiry : enquiries) {
			System.out.printf("%d. Enquiry: %s\n", ++indexIterator, enquiry.getStringValue());
		}
		System.out.printf("Which Enquiry to Reply?\nYour choice: ");
		
	    	try {
		    int tchoice = Integer.parseInt(br.readLine());
		    if(tchoice < 1 || tchoice > enquiries.size()){
			System.out.println("Invalid choice");
			return;
		    }
		    System.out.print("Enter reply message: ");
		    String treply = br.readLine();
		    committeeObject.replyToAttendeeEnquiry(Registry.enquiryMap.get(enquiries.get(tchoice-1).getID()), treply, committeeObject.getUserID());
		    System.out.println("Reply successfully!");
	    	} catch (IOException e) {
			e.printStackTrace();
	    	}
	}

	public String getMenuDescription() {
		return "Make a reply to attendees' enquiries";
	}
}

