import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;

public class StudentLoginDriver {

    static void printMenuPage1(){
        System.out.println("1. Login");
        System.out.println("2. Return to main menu");
        System.out.println("3. Exit");
    }

    public static void studentDriverPage1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;
        while (choice != 2 ){
            printMenuPage1();
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(br.readLine());
            switch(choice){
                case 1:
                    System.out.print("Enter your userID: ");
                    String userID = br.readLine();
                    System.out.print("Enter your password: ");
                    String password = br.readLine();
		    if (Registry.studentMap.containsKey(userID)){
			try{
			    if(Registry.studentMap.get(userID).login(password)){
				System.out.println("Login successful");
				StudentDriver.printMenuPage2(userID);
			    }
			    else{
				System.out.println("Invalid Credentials");
			    }
			}
			catch (Exception e){
			    System.out.println(e.getMessage());
			}
		    }else{
			System.out.println("Invalid Credentials");
		    }

                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
