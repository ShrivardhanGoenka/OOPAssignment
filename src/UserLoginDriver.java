import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;

public class UserLoginDriver {

    static void printLoginMenu(){
        System.out.println("1. Login");
        System.out.println("2. Exit");
    }

    public static String authenticateUser() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;
        while (choice != 2 ){
            printLoginMenu();
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(br.readLine());
            switch(choice){
                case 1:
                    System.out.print("Enter your userID: ");
                    String userID = br.readLine();
                    System.out.print("Enter your password: ");
                    String password = br.readLine();

		    //need refractored
				if (Registry.studentMap.containsKey(userID)){
				try{
					if(Registry.studentMap.get(userID).login(password)){
					System.out.println("Login successful");
					return userID;
					}
					else{
					System.out.println("Invalid Credentials");
					}
				}
				catch (Exception e){
					System.out.println(e.getMessage());
				}
				} else if (Registry.committeeMap.containsKey(userID)){

				try{
					if(Registry.committeeMap.get(userID).login(password)){
					System.out.println("Login successful");
					return userID;
					}
					else{
					System.out.println("Invalid Credentials");
					}
				}
				catch (Exception e){
					System.out.println(e.getMessage());
				}
				}
				else if(Registry.staffMap.containsKey(userID)){
					try{
						if(Registry.staffMap.get(userID).login(password)){
							System.out.println("Login successful");
							return userID;
						}
						else{
							System.out.println("Invalid Credentials");
						}
					}
					catch (Exception e){
						System.out.println(e.getMessage());
					}
				}
				else{
				System.out.println("Invalid Credentials");
				}
				break;
                case 2:
					DBInterface dbInterface = new DBInterface();
					dbInterface.writeToDB();
					System.exit(0);
					return null;
                default:
                    System.out.println("Invalid choice");
            }
        }
	return null;
    }
}
