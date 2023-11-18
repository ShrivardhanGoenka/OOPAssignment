// javadoc
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The ViewMySubmittedSuggestionsMenu class provides the execution logics of the menu for submitting a suggestion to the staff.
 */
public class SubmitSuggestionMenu extends IMenu<CampCommittee> {

	/** 
	 * A buffer reader to handle the user input.
	 */
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Executes the menu logics for camp committe to submit a suggestion to the staff.
	 */
	public void runMenu(CampCommittee committeeObject) {
		try {
			System.out.printf("Enter your suggestion for the camp \"%s\" :", committeeObject.getCamp().getCampName());
			String suggestion = br.readLine();
			committeeObject.submitSuggestion(suggestion, committeeObject.getCamp().getCampID());
			System.out.println("Suggestion raised successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the menu description.
	 */
	public String getMenuDescription() {
		return "Submit a suggestion to staff";
	}
}
