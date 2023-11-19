// javadoc
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The ViewMySubmittedSuggestionsMenu class provides the execution logics of the menu for viewing the submitted suggestion to the staff.
 */
public class ViewMySubmittedSuggestionsMenu extends IMenu<CampCommittee> {
	
	/** 
	 * A buffer reader to handle the user input.
	 */
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Executes the menu logics for viewing the submitted suggestion.
	 */
	public void runMenu(CampCommittee committeeObject) {
		HashMap<Integer, Suggestion> suggestionMap = committeeObject.submittedSuggestions;
		System.out.printf("----------<View All Suggestions Submitted for Camp %s>-----------\n", committeeObject.getCamp().getCampName());
		for (Map.Entry<Integer, Suggestion> suggestionPair : suggestionMap.entrySet()) {
			Suggestion suggestion = suggestionPair.getValue();

			System.out.printf("Suggestion: %s\n", suggestion.getStringValue());
			System.out.printf("Submitted by: %s\n", suggestion.getSubmittedBy());
			System.out.printf("Submitted on: %s\n", suggestion.getSubmittedOn());
			if (suggestion.getReply()!="") {
				System.out.printf("Reply: %s\n", suggestion.getReply());
				System.out.printf("Replied by: %s on: %s\n", suggestion.getRepliedBy(), suggestion.getRepliedOn());
			}
			System.out.printf("Last Updated on: %s\n", DBInterface.returnDateVal(suggestion.getUpdatedOn()));
			String status = "Pending";
			if (suggestion.getApprovalStatus() == 1) { 
				status = "Approved"; 
			} else if (suggestion.getApprovalStatus() == 2) {
				status = "Rejected";
			}
			System.out.printf("Status: %s\n", status);
			System.out.println("---------------------");
		}
	}
	
	/**
	 * Returns the print format for the Date
	 * @param d 		The Date.
	 * @return String
	static String printDate(Date d){
		return d.getDate() + "/" + d.getMonth() + "/" + (d.getYear() + 1900);
	}

	/**
	 * @return String of the menu description.
	 */
	public String getMenuDescription() {
		return "View my submitted suggestions";
	}
}
