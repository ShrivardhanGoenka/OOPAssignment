import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ViewAllEnquiriesMenu extends IMenu<CampCommittee> {

	/** 
	 * A buffer reader to handle the user input.
	 */
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public void runMenu(CampCommittee committeeObject) {
		ArrayList<Enquiry> enquiries = new ArrayList<>(
			committeeObject.getAttendeeEnquiryMap().values()
		);
		
		SortableEnquiry sortableEnquiry = new SortableEnquiry();
		System.out.println("Please select the attribute to sort");
		ArrayList<ComparableAttribute<Enquiry>> sortableAttributes = sortableEnquiry.getSortableAttributes();
		for (int i=0;i<sortableAttributes.size();i++) {
			System.out.printf("%d. %s\n", i+1, sortableAttributes.get(i).getAttributeName());
		}
		int choice = 0;
		try {
			choice = Integer.parseInt(br.readLine());
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("Invalid option, using default sorting option");
		}
		if (choice < 1 || choice > sortableAttributes.size()) {
			System.out.println("Invalid option, using default sorting option");
			return;
		}

		ComparableAttribute<Enquiry> attribute = sortableAttributes.get(choice-1);
		sortableEnquiry.sortArrayList(enquiries, attribute);

		System.out.printf("----------<View All Enquiries for Camp %s>-----------\n", committeeObject.getCamp().getCampName());
		for (int i=0;i<enquiries.size();i++) {
			Enquiry enquiry = enquiries.get(i);
			System.out.printf("Enquiry: %s\n", enquiry.getStringValue());
			System.out.printf("Submitted by: %s\n", enquiry.getSubmittedBy());
			System.out.printf("Submitted on: %s\n", enquiry.getSubmittedOn());
			if (enquiry.getReply()!="") {
				System.out.printf("Reply: %s\n", enquiry.getReply());
				System.out.printf("Replied by: %s on: %s\n", enquiry.getRepliedBy(), enquiry.getRepliedOn());
			}
			System.out.printf("Last Updated on: %s\n", printDate(enquiry.getUpdatedOn()));
			System.out.println("---------------------");
		}
	}

	static String printDate(Date d){
		return d.getDate() + "/" + d.getMonth() + "/" + (d.getYear() + 1900);
	}

	public String getMenuDescription() {
		return "View all enquiries from the attendees";
	}
}
