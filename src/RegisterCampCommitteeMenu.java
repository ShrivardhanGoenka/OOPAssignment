import java.util.ArrayList;

/**
 * The {@link RegisterCampCommitteeMenu} class provides the execution logics of the menu for registering to a camp as a camp committee.
 */
public class RegisterCampCommitteeMenu implements IMenu<Student> {
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
        ConsoleReaderInteger consoleReaderInteger = new ConsoleReaderInteger();
        try {
            System.out.print("Your choice: ");
            int tempchoice = consoleReaderInteger.readFromConsole(1, availablaCamps.size());
            Camp chosen = availablaCamps.get(tempchoice-1);
            studentObject.registerCamp(chosen);
            System.out.println("You have successfully registered for the camp!");
        } catch (InputException | CampException e) {
            System.out.println(e.getMessage());
        }
    }

	/** 
	 * @return the menu description
	 */
    public String getMenuDescription() {
        return "Register for a Camp";
    }
}
