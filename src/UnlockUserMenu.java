import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The UnlockUserMenu class provides the execution logics of the admin menu for unlock the user's account.
 */
public class UnlockUserMenu extends IMenu<Admin> {

	/** 
	 * A buffer reader to handle the user input.
	 */
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Executes the menu logics for unlocking user's account.
	 */
	public void runMenu(Admin adminObject) {
                    System.out.println("Choose the user to unlock the account");
		    ArrayList<User> userList = new ArrayList<User>(Registry.studentMap.values());
		    userList.addAll(userList.size(), Registry.committeeMap.values());
		    userList.addAll(userList.size(), Registry.staffMap.values());
		    for (int i=0; i<userList.size();i++) {
			System.out.printf("%d: %s\n", i+1, userList.get(i).getUserID());
		    }
		    int choice;
                    System.out.printf("Enter your choice: ");
		    try {
		    	choice = Integer.parseInt(br.readLine());
			if (choice >= userList.size() || choice < 0) {
			    System.out.println("Invalid choice");
			    return;
			}
		    } catch (Exception e) {
		    	System.out.println("Invalid choice");
		    	return;
		    }
                    try {
			User userObject = userList.get(choice-1);
                        userObject.unlockAccount();
                        System.out.println("Account unlocked successfully!\n");
                    }
                    catch (Exception e){
                        System.out.print(e.getMessage());
                    }
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Unlock User's account";
	}
}
