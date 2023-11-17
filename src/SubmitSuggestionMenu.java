import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubmitSuggestionMenu extends IMenu<CampCommittee> {

        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

	public String getMenuDescription() {
		return "Submit a suggestion to staff";
	}
}
