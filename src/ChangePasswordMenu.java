import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ChangePasswordMenu implements IMenu {
	private User userObject;

        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public ChangePasswordMenu(User userObject) {
		this.userObject = userObject;
	}

	public void runMenu() {
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

	public String getMenuDescription() {
		return "Change your Password";
	}
}
