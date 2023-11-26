import java.util.ArrayList;

/**
 * The {@code Driver} class provides the method to control the account menu, and the UI of each menu.
 */
public class Driver {
    /**
     * Controls the menu flows of the user's account, and Handles the user's input from console.
     * @param <T> 		A generic type of object extended from User
     * @param user 		The user object of type {@code T} that runs the menu
     * @param allMenus 		The menus available to this user account based on user's role
     */
    public static <T extends User> void accountMenu(T user, ArrayList<IMenu> allMenus) {
        if(allMenus == null) return;
        int choice = 0;
        while(user.isLoggedIn()) {
            try {
                System.out.println("------------------------------------");
                System.out.println("Welcome " + user.getUserID());
                for (int i=0;i<allMenus.size();i++) {
                    System.out.printf("%d. %s\n", i+1, allMenus.get(i).getMenuDescription());
                }
                System.out.print("\nEnter your choice: ");
                ConsoleReaderInteger consoleReaderInteger = new ConsoleReaderInteger();
                choice = consoleReaderInteger.readFromConsole(1, allMenus.size());
                allMenus.get(choice - 1).runMenu(user);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
