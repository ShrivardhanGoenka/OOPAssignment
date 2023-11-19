import java.util.Map;
import java.util.ArrayList;
public class ViewSuggestionMenu extends IMenu<Staff> {
    @Override
    public String getMenuDescription() {
        return "View all Suggestions";
    }

    @Override
    public void runMenu(Staff userObject) throws CampException {
        System.out.println("<------------------All Suggestions------------------>");
        ArrayList<Suggestion> suggestions = userObject.getSuggestions();
        if(suggestions.isEmpty()){
            System.out.println("No suggestions");
            return;
        }
        for(Suggestion suggestion: suggestions){
            System.out.println("Suggestion: " + suggestion.getStringValue());
            System.out.println("Camp: " + Registry.campMap.get(suggestion.getCampID()).getCampName());
            System.out.println("Status: " + (suggestion.isProcessed()? "Processed":"Unprocessed"));
            System.out.println("Submitted on: " + DBInterface.returnDateVal(suggestion.getSubmittedOn()));
            if(suggestion.isProcessed()){
                System.out.println("Processed on: " + DBInterface.returnDateVal(suggestion.getRepliedOn()));
                System.out.println("Processed by: " + suggestion.getRepliedBy());
                System.out.println("Reply: " + suggestion.getReply());
                System.out.println("Approval Status: " + (suggestion.getApprovalStatus() == 1?"Approved":"Rejected"));
            }
            System.out.println("---------------------------------------------------");
        }
    }
}
