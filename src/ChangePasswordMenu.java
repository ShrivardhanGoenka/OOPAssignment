/**
 * The ChangePasswordMenu class provides the execution logics of the menu for changing user password.
 */
public class ChangePasswordMenu implements IMenu<User> {
	/**
	 * Executes the menu logics for changing password.
	 * The logics are as follows:
	 */
	public void runMenu(User userObject) {
		System.out.print("Enter your new password: ");
		ConsoleReaderString crs = new ConsoleReaderString();
		try {
			String newPassword = crs.readFromConsole();
			userObject.changePassword(newPassword);
			System.out.println("Password changed successfully!\n");
		}
		catch (InputException | UserException e){
			System.out.print(e.getMessage());
		}
	}
	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Change your Password";
	}
}
