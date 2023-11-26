/**
 * The ViewMySubmittedSuggestionsMenu class provides the execution logics of the menu for submitting a suggestion to the staff.
 */
public class SubmitSuggestionMenu implements IMenu<CampCommittee> {
	/**
	 * Executes the menu logics for camp committee to submit a suggestion to the staff.
	 * @param committeeObject 		The camp committee that runs the menu.
	 */
	public void runMenu(CampCommittee committeeObject) {
		try {
			System.out.printf("Enter your suggestion for the camp \"%s\" :", committeeObject.getCamp().getCampName());
			ConsoleReaderString crs = new ConsoleReaderString();
			String suggestion = crs.readFromConsole();
			committeeObject.submitSuggestion(suggestion, committeeObject.getCamp().getCampID());
			System.out.println("Suggestion raised successfully!");
		} catch (InputException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @return the menu description.
	 */
	public String getMenuDescription() {
		return "Submit a suggestion to staff";
	}
}
