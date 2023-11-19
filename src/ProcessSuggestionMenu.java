import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;
        try{
            choice = Integer.parseInt(br.readLine());
        } catch(Exception e){
            System.out.println("Invalid choice");
            return;
        }
        if(choice == 0 || choice > suggestions.size()) return;
        Suggestion chosen = suggestions.get(choice-1);
        System.out.print("Enter 1 to approve, 2 to reject or 0 to exit: ");
        try{
            choice = Integer.parseInt(br.readLine());
        } catch(Exception e){
            System.out.println("Invalid choice");
            return;
        }
        if(choice < 1 || choice > 2) return;
        System.out.print("Enter reply: ");
        String reply = "";
        try{
            reply = br.readLine();
        } catch(Exception e){
            System.out.println("Invalid input");
            return;
        }
        userObject.processSuggestion(chosen.getID(), choice == 1, reply);
        System.out.println("Suggestion processed");
    }
}
