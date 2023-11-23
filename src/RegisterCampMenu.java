// javadoc
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * The RegisterCampMenu class provides the execution logics of the menu for registering a camp.
 */
public class  RegisterCampMenu extends IMenu<Student> {

	/** 
	 * A buffer reader to handle the user input.
	 */
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Executes the menu logics for registering to a camp.
	 * The logics are as follows:
	 * 1. The program shows camp avaiable to the student based on camp constraint and visibility.
	 * 2. The program will prompt the student to choose the camp to register to.
	 * @param studentObject 			The student object that runs the menu.
	 */
	public void runMenu(Student studentObject) {
                ArrayList<Camp> availablaCamps = Filters.filterStudentCamps(studentObject.getFaculty());
                System.out.println("Choose the camp you want to register for: ");
                for(int i=0;i<availablaCamps.size();i++){
                        System.out.println((i+1) + ". " + availablaCamps.get(i).getCampName());
                }
                System.out.print("Your choice: ");

		try {
			int tempchoice = Integer.parseInt(br.readLine());
			if(tempchoice <1 || tempchoice > availablaCamps.size()){
				System.out.println("Invalid choice");
				return;
			}
			Camp chosen = availablaCamps.get(tempchoice-1);
			studentObject.registerCamp(chosen);

		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Register for a Camp";
	}
}
