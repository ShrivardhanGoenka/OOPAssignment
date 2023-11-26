import java.util.ArrayList;
/**
 * The {@code ViewAllEnquiriesMenu} class provides the execution logics of the menu for viewing all enquiries submitted from camp attendees.
 */
public class ViewAllEnquiriesMenu implements IMenu<CampCommittee> {
	/**
	 * Executes the menu logics for viewing all enquiries submitted by attendees to the camp that the committee oversees.
	 * The user will be able to sort the list of enquiries based on user input attribute of enquiry chosen.
	 * @param committeeObject 		The camp committee that runs the menu.
	 */
	public void runMenu(CampCommittee committeeObject) {
		ArrayList<Enquiry> enquiries = new ArrayList<>(committeeObject.getAttendeeEnquiryMap().values());
		
		SortableEnquiryCommittee sortableEnquiry = new SortableEnquiryCommittee();
		sortableEnquiry.runMenu(enquiries);

		System.out.printf("----------<View All Enquiries for Camp %s>-----------\n", committeeObject.getCamp().getCampName());
        CustomPrinterEnquiry printer = new CustomPrinterEnquiry();
		for (Enquiry enquiry : enquiries) {
			printer.print(enquiry);
		}
	}

	/**
	 * @return String of the menu description.
	 */
	public String getMenuDescription() {
		return "View all enquiries from the attendees";
	}
}
