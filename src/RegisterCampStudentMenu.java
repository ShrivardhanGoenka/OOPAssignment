import java.util.ArrayList;

/**
 * The RegisterCampStudentMenu class provides the execution logics of the menu for registering to a camp as an attendee.
 */
public class RegisterCampStudentMenu implements IMenu<Student> {

	/**
	 * Executes the menu logics for registering to a camp as attendee.
	 *
	 * @param studentObject 			The student object that runs the menu.
	 */
	public void runMenu(Student studentObject) throws CampException{
		ArrayList<Camp> availablaCamps = Filters.filterStudentCamps(studentObject.getFaculty());
		ConsoleReaderInteger consoleReaderInteger = new ConsoleReaderInteger();
		System.out.println("Choose the camp you want to register for: ");
		for(int i=0;i<availablaCamps.size();i++){
				System.out.println((i+1) + ". " + availablaCamps.get(i).getCampName());
		}
		System.out.print("Your choice: ");
		int throwexception = 0;
		try {
			int tempchoice = consoleReaderInteger.readFromConsole(1, availablaCamps.size());
			Camp chosen = availablaCamps.get(tempchoice-1);
			System.out.print("Enter 1 if you want to register as an attendee, 2 if you want to register as a committee member: ");
			int choice = consoleReaderInteger.readFromConsole(1, 2);
			if(choice == 1){
				studentObject.registerCamp(chosen);
				System.out.println("You have successfully registered for the camp!");
			}
			else{
				try {
					chosen.registerCommitteeMember(studentObject.getUserID(), studentObject.getBlockedDates(), studentObject.getFaculty());
					RegistryFactory.studentRegistry.removeEntry(studentObject.getUserID());
					CampCommittee cc = new CampCommittee(studentObject.getUserID(), studentObject.getPassword(), studentObject.getEmail(), studentObject.getFaculty(), studentObject.isLocked(), chosen);
					RegistryFactory.committeeRegistry.addEntry(cc,studentObject.getUserID());
					throwexception = 1;
				}
				catch(CampException e){
					System.out.println(e.getMessage());
					return;
				}
			}
		} catch (InputException | CampException e) {
			System.out.println(e.getMessage());
			return;
		}
		if(throwexception == 1){
			throw new CampException("You have been registered as a committee member. Please log in again to view the changes.");
		}
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Register for a Camp";
	}
}
