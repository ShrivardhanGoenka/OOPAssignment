import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ViewAllEnquiriesMenu extends IMenu<CampCommittee> {
	
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void runMenu(CampCommittee committeeObject) {
		HashMap<Integer, Enquiry> enquiryMap = committeeObject.getAttendeeEnquiryMap();
		System.out.printf("----------<View All Enquiries for Camp %s>-----------\n", committeeObject.getCamp().getCampName());
		for (Map.Entry<Integer, Enquiry> enquiryPair : enquiryMap.entrySet()) {
			Enquiry enquiry = enquiryPair.getValue();
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
