import java.util.ArrayList;

/**
 * The {@code Driver} class provides the method to control the account menu, and the UI of each menu.
 */
public class Driver {
    public static <T extends User> void accountMenu(T user, ArrayList<IMenu> allMenus) throws Exception{
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
            if (choice > allMenus.size()|| choice < 1) {
                System.out.printf("Invalid choice\n");
                continue;
            }
            allMenus.get(choice - 1).runMenu(user);
            }
            catch(InputException e){
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
