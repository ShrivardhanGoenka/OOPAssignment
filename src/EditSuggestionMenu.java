import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The {@code EditSuggestionMenu} class provides the execution logics of the menu for editing user's submitted suggestion.
 */
public class EditSuggestionMenu extends IMenu<CampCommittee> {
	/** 
	 * A buffer reader to handle the user input.
	 */
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Executes the menu logics for editing suggestions.
	 * The logics are as follows:
	 * 1. The program prompt the user to choose the suggestion to edit.
	 * 2. The user enter the new suggestion message.
	 */
	public void runMenu(CampCommittee committeeObject) {
                    System.out.println("Choose the suggestion you want to edit: ");
                    ArrayList<Suggestion> suggestions = committeeObject.getUnprocessedSuggestions();
                    if(suggestions.isEmpty()) {
                        System.out.println("You have no open suggestions");
                        return;
                    }
                    for(int i=0;i<suggestions.size();i++){
                        System.out.println((i+1) + ": " + suggestions.get(i).getStringValue());
                    }
                    System.out.print("Your choice: ");
		    try {
			    int tchoice = Integer.parseInt(br.readLine());
			    if(tchoice < 1 || tchoice > suggestions.size()){
				System.out.println("Invalid choice");
				return;
			    }
			    System.out.print("Enter edit: ");
			    String tsuggestion = br.readLine();
			    suggestions.get(tchoice-1).edit(tsuggestion);
			    System.out.println("Suggestion edited successfully!");
		    } catch (IOException e) {
			e.printStackTrace();
		    }
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Edit a suggestion";
	}
}
