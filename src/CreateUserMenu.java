import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The {@link CreateUserMenu} class provides the execution logics of the admin menu for creating new user account.
 */
public class CreateUserMenu extends IMenu<Admin> {

	/** 
	 * A buffer reader to handle the user input.
	 */
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Executes the menu logics for locking user's account.
	 */
	public void runMenu(Admin adminObject) {
	    try {
		System.out.printf("Enter the userID of new user: ");
		String userID = br.readLine();
		System.out.printf("Enter the password of new user: ");
		String password = br.readLine();
		System.out.printf("Enter the email of new user: ");
		String email = br.readLine();
		System.out.printf("Enter the faculty of new user: ");
		String faculty = br.readLine();
		System.out.printf("Enter the domain of the new user: ");
		System.out.printf("1. Student ");
		System.out.printf("2. Staff ");

		Integer choice = Integer.parseInt(br.readLine());
		switch(choice) {
			case 1: 
				adminObject.createStudent(userID, password, email, faculty);
				break;
			case 2:
				adminObject.createStaff(userID, password, email, faculty);
				break;
		}
	    } catch (Exception e) {
		System.out.println("Invalid choice");
		return;
	    }
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Create new user account";
	}
}
