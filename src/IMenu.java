/**
 * The interface {@code IMenu} provides a contract for common functionality for all menu pages in this camp management applciation.
 * The required class to be implemented includes getMenuDescription and runMenu.
 */
public interface IMenu<T extends User>{
	/**
	 * Provides a description message of the menu.
	 * @return The String description of the menu.
	 */
	String getMenuDescription();

	/**
	 * Runs the menu.
	 * This includes displaying the menu specific instruction message, handling user input, and execute the menu logic.
	 * @param userObject 				The user that runs the menu
	 * @throws CampException 			If the camp cannot be modified based on user's input
	 */
	void runMenu(T userObject) throws CampException;
}
