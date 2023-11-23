import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The {@code Driver} class provides the method to control the account menu, and the UI of each menu.
 */
public class Driver {
    public static <T extends User> void accountMenu(T user, ArrayList<IMenu> allMenus) throws Exception{
        if(allMenus == null) return;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;
        while(user.isLoggedIn()) {
            System.out.println("------------------------------------");
            System.out.println("Welcome " + user.getUserID());
            for (int i=0;i<allMenus.size();i++) {
                System.out.printf("%d. %s\n", i+1, allMenus.get(i).getMenuDescription());
            }
            System.out.print("\nEnter your choice: ");
            choice = Integer.parseInt(br.readLine());
            if (choice > allMenus.size()|| choice < 1) {
                System.out.printf("Invalid choice\n");
                continue;
            }
            try {
                allMenus.get(choice - 1).runMenu(user);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
