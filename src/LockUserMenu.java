import java.util.ArrayList;

/**
 * The LockUserMenu class provides the execution logics of the admin menu for lock the user's account.
 */
public class LockUserMenu implements IMenu<Admin> {
	/**
	 * Executes the menu logics for locking user's account.
	 */
	public void runMenu(Admin adminObject) {
			System.out.println("Choose the user to lock the account");
		    ArrayList<User> userList = new ArrayList<>(RegistryFactory.studentRegistry.getAllEntries());
		    userList.addAll(userList.size(), RegistryFactory.committeeRegistry.getAllEntries());
		    userList.addAll(userList.size(), RegistryFactory.staffRegistry.getAllEntries());
		    for (int i=0; i<userList.size();i++) {
				System.out.printf("%d: %s\n", i+1, userList.get(i).getUserID());
		    }
		    int choice;
			System.out.print("Enter your choice: ");
			ConsoleReaderInteger consoleReaderInteger = new ConsoleReaderInteger();
		    try {
		    	choice = consoleReaderInteger.readFromConsole(1, userList.size());
				if (choice >= userList.size() || choice < 0) {
					System.out.println("Invalid choice");
					return;
				}
				User userObject = userList.get(choice-1);
				adminObject.lockUser(userObject);
				System.out.println("Account locked successfully!\n");
			}
			catch (InputException e){
				System.out.print(e.getMessage());
			}
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Lock User's account";
	}
}
