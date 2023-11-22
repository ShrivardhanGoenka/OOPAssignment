import java.util.ArrayList;
//All done
public class ViewAllEnquiriesMenu extends IMenu<CampCommittee> {

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
	public String getMenuDescription() {
		return "View all enquiries from the attendees";
	}
}
