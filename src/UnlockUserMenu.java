import java.util.ArrayList;
/**
 * The UnlockUserMenu class provides the execution logics of the admin menu for unlock the user's account.
 */
public class UnlockUserMenu implements IMenu<Admin> {
	/**
	 * Executes the menu logics for unlocking user's account.
	 */
	public void runMenu(Admin adminObject) {
			System.out.println("Choose the user to unlock the account");
		    ArrayList<User> userList = new ArrayList<>(RegistryFactory.studentRegistry.getAllEntries());
		    userList.addAll(userList.size(), RegistryFactory.committeeRegistry.getAllEntries());
		    userList.addAll(userList.size(), RegistryFactory.staffRegistry.getAllEntries());
		    for (int i=0; i<userList.size();i++) {
			System.out.printf("%d: %s\n", i+1, userList.get(i).getUserID());
		    }
		    int choice;
			ConsoleReaderInteger consoleReaderInteger = new ConsoleReaderInteger();
		    try {
				System.out.print("Enter your choice: ");
		    	choice = consoleReaderInteger.readFromConsole(1, userList.size());
		    } catch (InputException e) {
		    	System.out.println(e.getMessage());
		    	return;
		    }
			try {
				User userObject = userList.get(choice-1);
				userObject.unlockAccount();
				System.out.println("Account unlocked successfully!\n");
			}
			catch (Exception e){
				System.out.print(e.getMessage());
			}
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Unlock User's account";
	}
}
