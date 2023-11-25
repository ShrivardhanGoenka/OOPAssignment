import java.util.ArrayList;
import java.util.Comparator;

/**
 * The {@code SortableEnquiryStudent} class provides student-specific sortable attributes for the object of type {@code Enquiry}
 */
public class SortableEnquiryStudent extends Sortable<Enquiry>{
    /**
     * Provides student-specific sortable attributes.
     *
     * @return {@code ArrayList} of {@code ComparableAttribute<Enquiry>} representing sortable attributes for Enquiry object
     */
    public ArrayList<ComparableAttribute<Enquiry>> getSortableAttributes(){
        ArrayList<ComparableAttribute<Enquiry>> sortableAttributes = new ArrayList<ComparableAttribute<Enquiry>>();
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Enquiry Content", Comparator.comparing(Enquiry::getStringValue)));
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Submitted on", Comparator.comparing(Enquiry::getSubmittedOn)));
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Processed?", Comparator.comparing(Enquiry::isProcessed)));
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Reply", Comparator.comparing(e -> (e.getReply() == null || e.getReply().isEmpty()) ? null : e.getReply(),
                Comparator.nullsLast(Comparator.naturalOrder()))));
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Replied on", Comparator.comparing(Enquiry::getRepliedOn, Comparator.nullsLast(Comparator.naturalOrder()))));
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Replied by", Comparator.comparing(e -> (e.getRepliedBy() == null || e.getRepliedBy().isEmpty()) ? null : e.getRepliedBy(),
                Comparator.nullsLast(Comparator.naturalOrder()))));
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Last Updated on", Comparator.comparing(Enquiry::getUpdatedOn)));
        return sortableAttributes;
    }
}
