import java.util.ArrayList;
import java.util.Comparator;

public class SortableSuggestionStaff extends SortableSuggestionCommittee {
    @Override
    public ArrayList<ComparableAttribute<Suggestion>> getSortableAttributes() {
        ArrayList<ComparableAttribute<Suggestion>> sortableAttributes = super.getSortableAttributes();
        sortableAttributes.add(new ComparableAttribute<Suggestion>("Submitted by", Comparator.comparing(Suggestion::getSubmittedBy)));
        return sortableAttributes;
    }
}
