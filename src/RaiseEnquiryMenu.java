import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RaiseEnquiryMenu implements IMenu {
	private Student studentObject;

        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public RaiseEnquiryMenu(Student studentObject) {
		this.studentObject = studentObject;
	}

	public void runMenu() {
	    	System.out.println("Choose the camp you want to raise an enquiry for: ");
		ArrayList<Camp> camps = Filters.filterStudentCamps(studentObject.getFaculty());
		for(int i=0;i<camps.size();i++){
			System.out.println((i+1) + ". " + camps.get(i).getCampName());
		}
		System.out.print("Your choice: ");
		try {
			int tempchoice = Integer.parseInt(br.readLine());
			if(tempchoice <1 || tempchoice > camps.size()){
				System.out.println("Invalid choice");
				return;
			}
			Camp chosenCamp = camps.get(tempchoice-1);
			System.out.print("Enter your enquiry: ");
			String enquiry = br.readLine();
			studentObject.raiseEnquiry(enquiry, chosenCamp.getCampID());
			System.out.println("Enquiry raised successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getMenuDescription() {
		return "Raise an enquiry";
	}
}
