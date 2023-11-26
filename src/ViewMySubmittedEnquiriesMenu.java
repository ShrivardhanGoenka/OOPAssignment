import java.util.ArrayList;
/**
 * The {@code ViewMySubmittedEnquiriesMenu} class provides the execution logics of the menu for viewing the submitted enquiries.
 */
public class ViewMySubmittedEnquiriesMenu implements IMenu<Student> {
	/**
	 * Executes the menu logics for viewing the submitted enquiries.
	 * @param studentObject 		The student that runs the menu.
	 */
	public void runMenu(Student studentObject) {
	    ArrayList<Enquiry> enquiries = new ArrayList<>(studentObject.submittedEnquiries());
		if(enquiries.isEmpty()){
	        System.out.println("You have not submitted any enquiries yet.");
	        return;
	    }
		SortableEnquiryStudent sortableEnquiry = new SortableEnquiryStudent();
		sortableEnquiry.runMenu(enquiries);
		System.out.println(">-------------List of Enquiries Submitted-------------<");
		CustomPrinterEnquiry printer = new CustomPrinterEnquiry();
		printer.print(enquiries);
	}

	/**
	 * @return String of the menu description.
	 */
	public String getMenuDescription() {
		return "Viewed my submitted enquiries";
	}
}
