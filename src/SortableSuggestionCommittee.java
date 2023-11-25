import java.util.ArrayList;
import java.util.Comparator;

/**
 * The {@code SortableSuggestionCommittee} class extends the {@code Sortable} class and provides sortable attributes
 * specific to committee-related sorting attributes for {@code Suggestion} objects.
 */
public class SortableSuggestionCommittee extends Sortable<Suggestion> {

    /**
     * Provides committee-specific sortable attributes for {@code Suggestion} objects.
     *
     * @return {@code ArrayList} of {@code ComparableAttribute<Suggestion>} representing sortable attributes for committee-specific sorting
     */
    @Override
    public ArrayList<ComparableAttribute<Suggestion>> getSortableAttributes() {
        ArrayList<ComparableAttribute<Suggestion>> sortableAttributes = new ArrayList<ComparableAttribute<Suggestion>>();
        sortableAttributes.add(new ComparableAttribute<Suggestion>("Suggestion Content", Comparator.comparing(Suggestion::getStringValue)));
        sortableAttributes.add(new ComparableAttribute<Suggestion>("Submitted on", Comparator.comparing(Suggestion::getSubmittedOn)));
        sortableAttributes.add(new ComparableAttribute<Suggestion>("Processed?", Comparator.comparing(Suggestion::isProcessed)));
        sortableAttributes.add(new ComparableAttribute<Suggestion>("Reply", Comparator.comparing(e -> (e.getReply() == null || e.getReply().isEmpty()) ? null : e.getReply(),
                Comparator.nullsLast(Comparator.naturalOrder()))));
        sortableAttributes.add(new ComparableAttribute<Suggestion>("Replied on", Comparator.comparing(Suggestion::getRepliedOn, Comparator.nullsLast(Comparator.naturalOrder()))));
        sortableAttributes.add(new ComparableAttribute<Suggestion>("Replied by", Comparator.comparing(e -> (e.getRepliedBy() == null || e.getRepliedBy().isEmpty()) ? null : e.getRepliedBy(),
                Comparator.nullsLast(Comparator.naturalOrder()))));
        sortableAttributes.add(new ComparableAttribute<Suggestion>("Last Updated on", Comparator.comparing(Suggestion::getUpdatedOn)));
        sortableAttributes.add(new ComparableAttribute<Suggestion>("Approved?", Comparator.comparing(Suggestion::getApprovalStatus)));
        return sortableAttributes;
    }
}
