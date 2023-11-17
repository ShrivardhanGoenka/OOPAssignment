import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class DeleteSuggestionMenu extends IMenu<CampCommittee> {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

	public String getMenuDescription() {
		return "Delete Suggestion";
	}
}
