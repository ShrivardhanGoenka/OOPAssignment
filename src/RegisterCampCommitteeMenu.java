import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * The {@link RegisterCampCommitteeMenu} class provides the execution logics of the menu for registering to a camp as a camp committee.
 */
public class RegisterCampCommitteeMenu extends IMenu<Student> {


	/** 
	 * A buffer reader to handle the user input.
	 */
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Executes the menu logics for registering to a camp as a camp committee.
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

    public String getMenuDescription() {
        return "Register for a Camp";
    }
}
