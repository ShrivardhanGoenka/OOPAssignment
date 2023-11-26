/**
 * The UserLogoutMenu class provides the execution logics of the logging out menu.
 */
public class UserLogoutMenu implements IMenu<User> {

	/**
	 * Logout the user from the application.
	 */
	public void runMenu(User userObject) {
		try{
			userObject.logout();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @return the menu description.
	 */
	public String getMenuDescription () {
		return "Logout";
	}
}
