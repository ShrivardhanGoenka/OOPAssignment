//javadoc
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * The WithdrawCampMenu class provides the execution logics of the menu for withdrawing from a camp.
 */
public class WithdrawCampMenu extends IMenu<Student> {
	/** 
	 * A buffer reader to handle the user input.
	 */
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Executes the menu logics for withdrawing from a camp.
	 * The logics is as follows:
	 * 1. The program will prompt the student to choose the camp to withdraw from.
	 */
	public void runMenu(Student studentObject) {
                    System.out.println("Choose the camp you want to withdraw from: ");
                    ArrayList<Camp> camps = studentObject.registeredCamps();
                    for(int i=0;i<camps.size();i++){
                        System.out.println((i+1) + ": " + camps.get(i).getCampName());
                    }
                    System.out.print("Your choice: ");
		    try {
			    int tchoice = Integer.parseInt(br.readLine());
			    System.out.println(tchoice);
			    if(tchoice < 1 || tchoice > camps.size()){
				System.out.println("Invalid choice");
				return;
			    }
			    Camp c = camps.get(tchoice-1);
			    System.out.println(c.getAttendees());
			    studentObject.withdrawFromCamp(c);
			    System.out.println("Withdrawn successfully");
		    } catch (IOException e) {
			e.printStackTrace();
		    }
	}

	/**
	 * @return the menu description.
	 */
	public String getMenuDescription () {
		return "Withdraw from a camp";
	}
}
