import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;

/**
 * The ChangePasswordAdminMenu class provides the execution logics of the admin menu for changing user's password.
 */
public class ChangePasswordAdminMenu extends IMenu<Admin> {

	/** 
	 * A buffer reader to handle the user input.
	 */
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Executes the menu logics for changing password of chosen user account.
	 */
	public void runMenu(Admin adminObject) {
                    System.out.println("Choose the user to change password");
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
			if (choice > userList.size() || choice < 0) {
			    System.out.println("Invalid choice");
			    return;
			}
		    } catch (Exception e) {
		    	System.out.println("Invalid choice");
		    	return;
		    }
                    try {
			User userObject = userList.get(choice-1);
                    	System.out.printf("Enter user's new password: ");
			String newPassword = br.readLine();
                        userObject.changePassword(newPassword);
                        System.out.println("Password changed successfully!\n");
                    }
                    catch (Exception e){
                        System.out.print(e.getMessage());
                    }
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Change user's password";
	}
}
