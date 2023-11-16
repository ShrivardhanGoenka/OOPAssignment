import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class WithdrawCampMenu implements IMenu {
	private Student studentObject;

        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public WithdrawCampMenu(Student studentObject) {
		this.studentObject = studentObject;
	}

	public void runMenu() {
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

	public String getMenuDescription () {
		return "Withdraw from a camp";
	}
}
