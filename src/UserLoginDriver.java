import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;

public class UserLoginDriver {

    static void printLoginMenu(){
        System.out.println("1. Login");
        System.out.println("2. Exit");
    }

    public static String authenticateUser() {
        ConsoleReaderString cr1 = new ConsoleReaderString();
		ConsoleReaderInteger cr2 = new ConsoleReaderInteger();
		while(true) {
			printLoginMenu();
			int choice;
			System.out.print("Your choice: ");
			try {
				choice = cr2.readFromConsole(1, 2);
			}catch(InputException e){
				System.out.println(e.getMessage());
				continue;
			}
			if(choice == 2){
				DBInterface db = new DBInterface();
				db.writeToDB();
				System.exit(0);
				return null;
			}
			String username, password;
			try{
				System.out.print("Username: ");
				username = cr1.readFromConsole();
				System.out.print("Password: ");
				password = cr1.readFromConsole();
				if(Registry.authenticateUser(username, password)){
					return username;
				}
				else{
					System.out.println("Invalid username or password");
				}
			} catch(InputException | UserException e){
				System.out.println(e.getMessage());
			}
        }
    }
}
