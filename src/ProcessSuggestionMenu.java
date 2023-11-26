import java.util.ArrayList;

/**
 * The {@code ProcessSuggestionMenu} class provides the execution logics of the staff's menu for processing the suggestion.
 */
public class ProcessSuggestionMenu implements IMenu<Staff>{

	/**
	 * @return the menu description.
	 */
    @Override
    public String getMenuDescription() {
        return "Process Suggestions";
    }

	/**
	 * Executes the menu logics for processing the committee's submitted suggestion
	 * The logics are as follows:
	 * 1. The program prompt the staff to choose the suggestion to process (if any).
	 * 2. If the user enter an invalid choice, the menu will be terminated.
	 * 3. If the staff can choose to accept or reject the suggestion.
	 * 4. The staff enter the reply message.
	 * 
	 * @throws CampException 		If the suggestion processing cannot be made or the input choice is invalid
	 */
    @Override
    public void runMenu(Staff userObject) throws CampException {
        ArrayList<Suggestion> suggestions = userObject.getUnprocessedSuggestions();
        if(suggestions.isEmpty()){
            System.out.println("No suggestions to process");
            return;
        }
        System.out.println("<------------------Unprocessed Suggestions------------------>");
        int counter=1;
        for(Suggestion suggestion: suggestions){
            System.out.println("Suggestion number: " + counter++ );
            System.out.println("Suggestion: " + suggestion.getStringValue());
            System.out.println("Camp: " + RegistryFactory.campRegistry.getEntry(suggestion.getCampID()).getCampName());
            System.out.println("Submitted on: " + Parsers.dateToString(suggestion.getSubmittedOn()));
            System.out.println("---------------------------------------------------");
        }
        System.out.print("Enter the suggestion ID to process or 0 to exit: ");
        ConsoleReaderInteger cri = new ConsoleReaderInteger();
        ConsoleReaderString crs = new ConsoleReaderString();
        int choice;
        try{
            choice = cri.readFromConsole(0, suggestions.size());
            Suggestion chosen = suggestions.get(choice-1);
            System.out.print("Enter 1 to approve, 2 to reject: ");
            choice = cri.readFromConsole(1, 2);
            System.out.print("Enter reply: ");
            String reply = crs.readFromConsole();
            userObject.processSuggestion(chosen.getID(), choice == 1, reply);
            System.out.println("Suggestion processed");
        } catch(Exception e){
            System.out.println("Invalid choice");
        }
    }
}
