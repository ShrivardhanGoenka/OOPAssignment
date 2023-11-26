import java.util.ArrayList;
/**
 * The {@code ViewSuggestionMenu} class provides the execution logics of the menu for viewing all suggestions submitted.
 */
public class ViewSuggestionMenu implements IMenu<Staff> {

	/**
	 * @return String of the menu description.
	 */
    @Override
    public String getMenuDescription() {
        return "View all Suggestions";
    }

	/**
	 * Executes the menu logics for viewing all suggestions submitted by camp committee.
	 * The user will be able to sort the list of suggestion based on user input attribute of suggestion chosen.
	 * @param userObject 		The staff that runs the menu.
	 */
    @Override
    public void runMenu(Staff userObject)  {

        ArrayList<Suggestion> suggestions = userObject.getSuggestions();
        if(suggestions.isEmpty()){
            System.out.println("No suggestions");
            return;
        }
        SortableSuggestionCommittee sortableSuggestion = new SortableSuggestionCommittee();
        sortableSuggestion.runMenu(suggestions);
        System.out.println("<------------------All Suggestions------------------>");
        CustomPrinterSuggestion printer = new CustomPrinterSuggestion();
        printer.print(suggestions);
    }
}
