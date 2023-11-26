import java.util.ArrayList;

/**
 * The ReplyToEnquiriesMenu class provides the execution logics of the menu for replying to attendee's enquiries.
 * Only camp committee are allowed to execute this menu.
 */
public class ReplyToEnquiriesMenu implements IMenu<CampCommittee> {
	/**
	 * Executes the menu logics for replying to enquiries.
	 * The logics are as follows:
	 * 1. The unreplied enquiries submitted by attendee's will be shown to the camp committee.
	 * 2. The program will prompt the camp committee to choose the enquiry to reply to.
	 * 3. The program will prompt the camp committee to enter the reply message.
     * @param committeeObject               The camp committee that runs the menu
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
		System.out.print("Which Enquiry to Reply?\nYour choice: ");
		ConsoleReaderInteger cr = new ConsoleReaderInteger();
		ConsoleReaderString crs = new ConsoleReaderString();
		try {
			int choice = cr.readFromConsole(1, enquiries.size());
		    System.out.print("Enter reply message: ");
		    String reply = crs.readFromConsole();
		    committeeObject.replyToAttendeeEnquiry(enquiries.get(choice-1), reply, committeeObject.getUserID());
		    System.out.println("Reply successfully!");
		}
		catch (InputException e) {
			System.out.println(e.getMessage());
		}
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Make a reply to attendees' enquiries";
	}
}

