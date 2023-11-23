import java.util.ArrayList;
public class ProcessSuggestionMenu extends IMenu<Staff>{

    @Override
    public String getMenuDescription() {
        return "Process Suggestions";
    }

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
            System.out.println("Camp: " + Registry.campMap.get(suggestion.getCampID()).getCampName());
            System.out.println("Submitted on: " + DBInterface.returnDateVal(suggestion.getSubmittedOn()));
            System.out.println("---------------------------------------------------");
        }
        System.out.print("Enter the suggestion ID to process or 0 to exit: ");
        ConsoleReaderInteger cri = new ConsoleReaderInteger();
        ConsoleReaderString crs = new ConsoleReaderString();
        int choice = 0;
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
            return;
        }
    }
}
