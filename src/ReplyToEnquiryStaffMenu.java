import java.util.ArrayList;

/**
 * The {@code ReplyToEnquiryStaffMenu} class provides the execution logics of the staff's menu for replying to student's enquiries.
 */
public class ReplyToEnquiryStaffMenu implements IMenu<Staff>{
	/**
	 * Executes the menu logics for replying to enquiries.
	 * The logics are as follows:
	 * 1. The unreplied enquiries submitted by attendee's will be shown.
	 * 2. The program will prompt the staff to choose the enquiry to reply to.
	 * 3. The program will prompt the staff to enter the reply message.
     * @param staffObject                   The staff that runs the menu
	 */
    public void runMenu(Staff staffObject) {
        System.out.print("----------<List of Unprocessed Enquiries>-----------\n");
        int indexIterator=0;
        ArrayList<Enquiry> enquiries = staffObject.getUnprocessedEnquiries();
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
            staffObject.processEnquiry(enquiries.get(choice-1).getID(), reply);
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
