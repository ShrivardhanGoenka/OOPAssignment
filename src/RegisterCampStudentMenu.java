import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * The RegisterCampStudentMenu class provides the execution logics of the menu for registering to a camp as an attendee.
 */
public class RegisterCampStudentMenu extends IMenu<Student> {

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
			}
			else{
				try {
					chosen.registerCommitteeMember(studentObject.getUserID(), studentObject.getBlockedDates(), studentObject.getFaculty());
					Registry.studentMap.remove(studentObject.getUserID());
					CampCommittee cc = new CampCommittee(studentObject.getUserID(), studentObject.getPassword(), studentObject.getEmail(), studentObject.getFaculty(), studentObject.isLocked(), chosen);
					Registry.committeeMap.put(studentObject.getUserID(), cc);
					throwexception = 1;
				}
				catch(CampException e){
					System.out.println(e.getMessage());
					return;
				}
			}
		} catch (InputException e) {
			System.out.println(e.getMessage());
			return;
		}
		if(throwexception == 1){
			throw new CampException("You have been registered as a committee member. Please log in again to view the changes.");
		}
	}

	public String getMenuDescription() {
		return "Register for a Camp";
	}
}
