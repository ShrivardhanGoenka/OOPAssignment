import java.util.ArrayList;

/**
 * The {@code EditSuggestionMenu} class provides the execution logics of the menu for editing user's submitted suggestion.
 */
public class EditSuggestionMenu implements IMenu<CampCommittee> {
	/**
	 * Executes the menu logics for editing suggestions.
	 * The logics are as follows:
	 * 1. The program prompt the user to choose the suggestion to edit.
	 * 2. The user enter the new suggestion message.
     * @param committeeObject           The camp committee that runs the menu
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
		ConsoleReaderInteger consoleReaderInteger = new ConsoleReaderInteger();
		ConsoleReaderString consoleReaderString = new ConsoleReaderString();
		try {
			System.out.print("Your choice: ");
			int choice = consoleReaderInteger.readFromConsole(1, suggestions.size());
			System.out.print("Enter edit: ");
			String suggestion = consoleReaderString.readFromConsole();
			suggestions.get(choice-1).edit(suggestion);
			System.out.println("Suggestion edited successfully!");
		} catch (InputException e) {
			System.out.println(e.getMessage());
		}
	}

	/** 
	 * @return the menu description
	 */
	public String getMenuDescription() {
		return "Edit a suggestion";
	}
}
