import java.util.Map;
import java.util.ArrayList;
//Complete
public class ViewSuggestionMenu extends IMenu<Staff> {
    @Override
    public String getMenuDescription() {
        return "View all Suggestions";
    }

    @Override
    public void runMenu(Staff userObject) throws CampException {

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
