// javadoc
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The ReplyToEnquiriesMenu class provides the execution logics of the menu for replying to attendee's enquiries.
 * Only camp committee are allowed to execute this menu.
 */
public class ReplyToEnquiriesMenu extends IMenu<CampCommittee> {

	/** 
	 * A buffer reader to handle the user input.
	 */
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Executes the menu logics for replying to enquiries.
	 * The logics is as follows:
	 * 1. The unreplied enquiries submitted by attendee's will be shown to the camp committee.
	 * 2. The program will prompt the camp committee to choose the enquiry to reply to.
	 * 3. The program will prompt the camp committee to enter the reply message.
	 */
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

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Make a reply to attendees' enquiries";
	}
}

