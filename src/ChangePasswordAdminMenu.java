import java.util.ArrayList;
/**
 * The {@code ChangePasswordAdminMenu} class provides the execution logics of the admin menu for changing user's password.
 */
public class ChangePasswordAdminMenu implements IMenu<Admin> {
	/**
	 * Executes the menu logics for changing password of chosen user account.
	 * @param adminObject 				The admin that runs the menu
	 */
	public void runMenu(Admin adminObject) {
			System.out.println("Choose the user to change password");
		    ArrayList<User> userList = new ArrayList<>(RegistryFactory.studentRegistry.getAllEntries());
		    userList.addAll(RegistryFactory.committeeRegistry.getAllEntries());
		    userList.addAll(RegistryFactory.staffRegistry.getAllEntries());
		    for (int i=0; i<userList.size();i++) {
				System.out.printf("%d: %s\n", i+1, userList.get(i).getUserID());
		    }
		    int choice;
			System.out.print("Enter your choice: ");
			ConsoleReaderInteger consoleReaderInteger = new ConsoleReaderInteger();
			ConsoleReaderString consoleReaderString = new ConsoleReaderString();
		    try{
				choice = consoleReaderInteger.readFromConsole(1, userList.size());
				User userObject = userList.get(choice-1);
				System.out.print("Enter user's new password: ");
				String newPassword = consoleReaderString.readFromConsole();
				userObject.changePassword(newPassword);
				System.out.println("Password changed successfully!\n");
			}catch(InputException | UserException e){
				System.out.println(e.getMessage());
			}
	}
	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Change user's password";
	}
}
