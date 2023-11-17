import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

public class StudentMenuDriver {

    public static void accountMenu(String userID, ArrayList<IMenu> allMenus) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Student studentObject = Registry.studentMap.get(userID);
        int choice = 0;
        while(allMenus.get(0).isUserLogin(studentObject)) {
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
	    allMenus.get(choice-1).runMenu(studentObject);
        }
    }
}
