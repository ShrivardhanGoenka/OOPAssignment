public class PrintProfileMenu extends IMenu<User> {

	/**
	 * Prints the profile of the user to System.out.
	 */
	public void runMenu(User studentObject) {
		System.out.println();
		studentObject.printProfile();
	}

	/**
	 * @return the menu description.
	 */
	public String getMenuDescription () {
		return "View my Profile";
	}
}
