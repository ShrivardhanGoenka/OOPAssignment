import java.util.ArrayList;
import java.util.Comparator;

/**
 * The {@code SortableSuggestionStaff} class extends the {@code SortableSuggestionCommittee} class and provides sortable attributes
 * specific to staff-related sorting attributes for {@code Suggestion} objects.
 */
public class SortableSuggestionStaff extends SortableSuggestionCommittee {
    /**
     * Provides addtional staff-specific sortable attributes for {@code Suggestion} objects in addition to committee-specific attribute.
     *
     * @return {@code ArrayList} of {@code ComparableAttribute<Suggestion>} representing sortable attributes for staff-specific sorting
     */
    @Override
    public ArrayList<ComparableAttribute<Suggestion>> getSortableAttributes() {
        ArrayList<ComparableAttribute<Suggestion>> sortableAttributes = super.getSortableAttributes();
        sortableAttributes.add(new ComparableAttribute<Suggestion>("Submitted by", Comparator.comparing(Suggestion::getSubmittedBy)));
        return sortableAttributes;
    }
}
