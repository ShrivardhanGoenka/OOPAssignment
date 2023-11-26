import java.util.ArrayList;

public class ViewAllEnquiriesStaffMenu implements IMenu<Staff> {
    /**
     * Executes the menu logics for viewing all enquiries submitted by attendees to the staff's camp.
     * The user will be able to sort the list of enquiries based on user input attribute of enquiry chosen.
     * @param staffObject 		The camp committee that runs the menu.
     */
    public void runMenu(Staff staffObject) {
        ArrayList<Enquiry> enquiries = new ArrayList<>(staffObject.getEnquiries());

        SortableEnquiryCommittee sortableEnquiry = new SortableEnquiryCommittee();
        sortableEnquiry.runMenu(enquiries);

        System.out.print("----------<View All Enquiries %s>-----------\n");
        CustomPrinterEnquiry printer = new CustomPrinterEnquiry();
        for (Enquiry enquiry : enquiries) {
            printer.print(enquiry);
        }
    }

    /**
     * @return String of the menu description.
     */
    public String getMenuDescription() {
        return "View all enquiries from the attendees of your camps";
    }
}
