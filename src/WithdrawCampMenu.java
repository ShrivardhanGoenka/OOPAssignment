import java.util.ArrayList;

/**
 * The WithdrawCampMenu class provides the execution logics of the menu for withdrawing from a camp.
 */
public class WithdrawCampMenu implements IMenu<Student> {
	/**
	 * Executes the menu logics for withdrawing from a camp.
	 * The program will prompt the student to choose the camp to withdraw from.
	 */
	public void runMenu(Student studentObject) {
		System.out.println("Choose the camp you want to withdraw from: ");
		ArrayList<Camp> camps = studentObject.registeredCamps();
		if(camps.isEmpty()) {
			System.out.println("You have no registered camps");
			return;
		}
		for(int i=0;i<camps.size();i++){
			System.out.println((i+1) + ": " + camps.get(i).getCampName());
		}
		System.out.print("Your choice: ");
		ConsoleReaderInteger cr = new ConsoleReaderInteger();
		try{
			int choice = cr.readFromConsole(1, camps.size());
			studentObject.withdrawFromCamp(camps.get(choice-1));
			System.out.println("Withdrawn successfully!");
		} catch (InputException e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @return the menu description.
	 */
	public String getMenuDescription () {
		return "Withdraw from a camp";
	}
}
