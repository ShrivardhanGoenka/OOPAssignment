public class ViewMySubmittedEnquiriesMenu extends IMenu<Student> {
	public void runMenu(Student studentObject) {
	    int counter = 0;
	    for (Enquiry enquiry : studentObject.submittedEnquiries()){
		System.out.println(counter++);
		System.out.println("Enquiry: " + enquiry.getStringValue());
		System.out.println("Camp: " + Registry.campMap.get(enquiry.getCampID()).getCampName());
		if(enquiry.isProcessed()){
		    System.out.println("Reply: " + enquiry.getReply());
		    System.out.println("Replied by: " + enquiry.getRepliedBy());
		}
		else{
		    System.out.println("Enquiry has not been replied to yet!");
		}
	    }
	}

	public String getMenuDescription() {
		return "Viewed my submitted enquiries";
	}
}
