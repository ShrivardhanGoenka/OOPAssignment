import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommitteeMenuDriver {
    public static void accountMenu(String userID, ArrayList<IMenu> allMenus) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	CampCommittee committeeObject = Registry.committeeMap.get(userID);
        int choice = 0;
        while(allMenus.get(0).isUserLogin(committeeObject)) {
            System.out.println("------------------------------------");
            System.out.println("Welcome " + userID);
	    for (int i=0;i<allMenus.size();i++) {
		    System.out.printf("%d. %s\n", i+1, allMenus.get(i).getMenuDescription());
	    }
	    // to be implemented
            System.out.print("\nEnter your choice: ");
            choice = Integer.parseInt(br.readLine());
	    if (choice > allMenus.size()|| choice < 1) {
		System.out.printf("Invalid choice\n");
		continue;
            }
	    allMenus.get(choice-1).runMenu(committeeObject);
        }
    }
}
