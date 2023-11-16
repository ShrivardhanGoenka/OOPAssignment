import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;

public class StudentDriver {

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
				printMenuPage2(userID);
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

    public static void printMenuPage2(String userID) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Student studentObject = Registry.studentMap.get(userID);
	ArrayList<IMenu> allMenus = new ArrayList<>();
	allMenus.add(new ViewCampStudentMenu(studentObject)); // checked
	allMenus.add(new RegisterCampMenu(studentObject)); 
	allMenus.add(new ViewMyRegisteredCampMenu(studentObject)); // checked
	allMenus.add(new ViewMySubmittedEnquiriesMenu(studentObject)); // checked
	allMenus.add(new RaiseEnquiryMenu(studentObject)); // checked
	allMenus.add(new EditEnquiryMenu(studentObject)); // checked
	allMenus.add(new DeleteEnquiryMenu(studentObject)); // checked
	allMenus.add(new WithdrawCampMenu(studentObject));
	allMenus.add(new PrintProfileMenu(studentObject)); // checked
	allMenus.add(new ChangePasswordMenu(studentObject)); // checked
        System.out.println("--------------------------------------------------------");
        int choice = 0;
        while(true) {
            System.out.println("------------------------------------");
            System.out.println("Welcome " + userID);
	    for (int i=0;i<allMenus.size();i++) {
		    System.out.printf("%d. %s\n", i+1, allMenus.get(i).getMenuDescription());
	    }
            System.out.print("\nEnter your choice: ");
            choice = Integer.parseInt(br.readLine());
	    if (choice > allMenus.size()|| choice < 1) {
		System.out.printf("Invalid choice\n");
            }
	    allMenus.get(choice-1).runMenu();
        }
    }
}
