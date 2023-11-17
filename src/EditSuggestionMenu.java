import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class EditSuggestionMenu extends IMenu<CampCommittee> {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void runMenu(CampCommittee committeeObject) {
                    System.out.println("Choose the enquiry you want to edit: ");
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

	public String getMenuDescription() {
		return "Edit a suggestion";
	}
}
