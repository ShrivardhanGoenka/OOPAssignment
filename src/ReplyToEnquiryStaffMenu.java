import java.util.ArrayList;

public class ReplyToEnquiryStaffMenu extends IMenu<Staff>{
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
    public String getMenuDescription() {
        return "Make a reply to attendees' enquiries";
    }
}
