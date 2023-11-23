/**
 * The abstract class {@code IMenu} provides a template for common functionality for all menu pages in this camp management applciation.
 * The required class to be implemented includes getMenuDescription and runMenu.
 */
public abstract class IMenu<T extends User>{
	/**
	 * Provides a description message of the menu.
	 * @return The String description of the menu.
	 */
	public abstract String getMenuDescription();

	/**
	 * Runs the menu.
	 * This includes displaying the menu specific instruction message, handling user input, and execute the menu logic.
	 */
	public abstract void runMenu(T userObject) throws CampException;

	/**
	 * Checks if the user has a permission to run the menu. 
	 * This checks whether the user is logged in to excute the menu.
	 *
	 * @return 			A boolean flag indicating whether the user calling the menu is logged in.
	 */
	public final boolean isUserLogin(T userObject){
		return userObject.isLoggedIn();
	}
}
