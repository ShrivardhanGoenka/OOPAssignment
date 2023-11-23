import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * The ChangePasswordMenu class provides the execution logics of the menu for changing user password.
 */
public class ChangePasswordMenu extends IMenu<User> {

	/** 
	 * A buffer reader to handle the user input.
	 */
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Executes the menu logics for changing password.
	 * The logics are as follows:
	 * 1. The program prompt the user to enter new password.
	 * 2. The program shows the message to indicate whether the change is successful.
	 */
	public void runMenu(User userObject) {
                    System.out.print("Enter your new password: ");
                    try {
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
		return "Change your Password";
	}
}
