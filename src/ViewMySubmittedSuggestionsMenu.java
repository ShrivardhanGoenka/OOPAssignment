import java.util.ArrayList;
/**
 * The {@code ViewMySubmittedSuggestionsMenu} class provides the execution logics of the menu for viewing the submitted suggestion to the staff.
 */
public class ViewMySubmittedSuggestionsMenu implements IMenu<CampCommittee> {

	/**
	 * Executes the menu logics for viewing the submitted suggestion.
	 */
	public void runMenu(CampCommittee committeeObject) {
		ArrayList<Suggestion> suggestions =new ArrayList<>(committeeObject.viewSuggestions());
		if(suggestions.isEmpty()){
			System.out.println("No suggestions");
			return;
		}
		SortableSuggestionCommittee sortableSuggestion = new SortableSuggestionCommittee();
		sortableSuggestion.runMenu(suggestions);
		System.out.printf("----------<View All Suggestions Submitted for Camp %s>-----------\n", committeeObject.getCamp().getCampName());
		CustomPrinterSuggestion printer = new CustomPrinterSuggestion();
		printer.print(suggestions);
	}

	/**
	 * @return String of the menu description.
	 */
	public String getMenuDescription() {
		return "View my submitted suggestions";
	}
}
