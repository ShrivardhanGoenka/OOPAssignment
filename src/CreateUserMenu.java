/**
 * The {@link CreateUserMenu} class provides the execution logics of the admin menu for creating new user account.
 */
public class CreateUserMenu implements IMenu<Admin> {
	/**
	 * Executes the menu logics for locking user's account.
	 */
	public void runMenu(Admin adminObject) {
	    try {
			ConsoleReaderString crs = new ConsoleReaderString();
			System.out.print("Enter the userID of new user: ");
			String userID = crs.readFromConsole();
			System.out.print("Enter the password of new user: ");
			String password = crs.readFromConsole();
			System.out.print("Enter the email of new user: ");
			String email = crs.readFromConsole();
			System.out.print("Enter the faculty of new user: ");
			String faculty = crs.readFromConsole();
			System.out.print("Enter the domain of the new user: ");
			System.out.print("1. Student ");
			System.out.print("2. Staff ");
			ConsoleReaderInteger consoleReaderInteger = new ConsoleReaderInteger();
			Integer choice = consoleReaderInteger.readFromConsole(1, 2);
			switch(choice) {
				case 1:
					adminObject.createStudent(userID, password, email, faculty);
					break;
				case 2:
					adminObject.createStaff(userID, password, email, faculty);
					break;
			}
	    } catch (InputException e) {
            System.out.println("Invalid choice");
	    }
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Create new user account";
	}
}
