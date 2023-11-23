import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The {@code DeleteSuggestionMenu} class provides the execution logics of the menu for deleting user's submitted suggestion.
 */
public class DeleteSuggestionMenu extends IMenu<CampCommittee> {
	/** 
	 * A buffer reader to handle the user input.
	 */
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Executes the menu logics for deleing suggestions.
	 * The logics are as follows:
	 * 1. The program prompt the user to choose the suggestion to delete.
	 * 2. The program shows the message to indicate whether the deletion is successful.
	 * 3. If the user enter the invalid choice, the user will have to enter the input again.
	 */
	public void runMenu(CampCommittee committeeObject) {
	    System.out.println("Choose the suggestion you want to delete: ");
	    ArrayList<Suggestion> suggestion = committeeObject.getUnprocessedSuggestions();
	    if(suggestion.isEmpty()) {
		System.out.println("You have no open suggestion");
		return;
	    }
	    for(int i=0;i<suggestion.size();i++){
		System.out.println((i+1) + ": " + suggestion.get(i).getStringValue());
	    }
	    System.out.print("Your choice: ");
	    try {
		    int tchoice = Integer.parseInt(br.readLine());
		    if(tchoice < 1 || tchoice > suggestion.size()){
			System.out.println("Invalid choice");
			return;
		    }
		    committeeObject.deleteSuggestion(suggestion.get(tchoice-1).getID());
		    System.out.println("Suggestion deleted successfully!");
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Delete Suggestion";
	}
}
