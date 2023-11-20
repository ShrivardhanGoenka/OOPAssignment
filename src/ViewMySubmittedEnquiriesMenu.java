import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ViewMySubmittedEnquiriesMenu extends IMenu<Student> {
	/** 
	 * A buffer reader to handle the user input.
	 */
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void runMenu(Student studentObject) {
	    int counter = 0;
	    ArrayList<Enquiry> enquiries = new ArrayList<>(
		studentObject.submittedEnquiries()
	    );
		
	    System.out.println("Please select the attribute to sort");
	    System.out.println("1. campID");
	    System.out.println("2. enquiryID");
	    System.out.println("3. Date last update");
	    System.out.println("4. Date submitted");
	    int choice = 2;

	    try {
		choice = Integer.parseInt(br.readLine());
	    } catch(IOException e) {
		e.printStackTrace();
		System.out.println("Invalid option, using default sorting option");
	    }

		switch (choice) {
		    case 1:
			Enquiry.sortObjects(enquiries, (e1, e2) -> Integer.compare(((Enquiry) e1).getCampID(), ((Enquiry) e2).getCampID()));
			break;
		    case 2:
			Enquiry.sortObjects(enquiries, (e1, e2) -> Integer.compare(((Enquiry) e1).getID(), ((Enquiry) e2).getID()));
			break;
		    case 3:
			Enquiry.sortObjects(enquiries, (e1, e2) -> e1.getUpdatedOn().compareTo(e2.getUpdatedOn()));
			break;
		    case 4:
			Enquiry.sortObjects(enquiries, (e1, e2) -> e1.getSubmittedOn().compareTo(e2.getSubmittedOn()));
			break;
		}

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
