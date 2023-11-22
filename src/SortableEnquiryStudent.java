import java.util.ArrayList;
import java.util.Comparator;

public class SortableEnquiryStudent extends Sortable<Enquiry>{
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
