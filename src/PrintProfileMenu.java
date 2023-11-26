/**
 * The {@code PrintProfileMenu} class provides the execution logics of the menu for printing the user profile.
 */
public class PrintProfileMenu implements IMenu<User> {

	/**
	 * Prints the profile of the user to System.out.
	 *
	 * @param userObject 			The student object that runs the menu.
	 */
	public void runMenu(User userObject) {
		System.out.println();
		userObject.printProfile();
	}

	/**
	 * @return the menu description.
	 */
	public String getMenuDescription () {
		return "View my Profile";
	}
}
