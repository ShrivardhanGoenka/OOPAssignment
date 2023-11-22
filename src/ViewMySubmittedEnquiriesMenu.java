import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
//Complete
public class ViewMySubmittedEnquiriesMenu extends IMenu<Student> {
	public void runMenu(Student studentObject) {
	    ArrayList<Enquiry> enquiries = new ArrayList<>(studentObject.submittedEnquiries());
		if(enquiries.isEmpty()){
	        System.out.println("You have not submitted any enquiries yet.");
	        return;
	    }
		SortableEnquiryStudent sortableEnquiry = new SortableEnquiryStudent();
		sortableEnquiry.runMenu(enquiries);
		System.out.println("-------------<List of Enquiries Submitted>-------------");
		CustomPrinterEnquiry printer = new CustomPrinterEnquiry();
		printer.print(enquiries);
	}

	public String getMenuDescription() {
		return "Viewed my submitted enquiries";
	}
}
