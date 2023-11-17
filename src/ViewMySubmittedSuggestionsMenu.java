import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ViewMySubmittedSuggestionsMenu extends IMenu<CampCommittee> {
	
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
			System.out.printf("Last Updated on: %s\n", printDate(suggestion.getUpdatedOn()));
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

	static String printDate(Date d){
		return d.getDate() + "/" + d.getMonth() + "/" + (d.getYear() + 1900);
	}

	public String getMenuDescription() {
		return "View my submitted suggestions";
	}
}
