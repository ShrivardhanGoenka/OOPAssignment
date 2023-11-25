import java.util.ArrayList;
import java.util.Comparator;
/**
 * The {@code SortableEnquiryCommittee} class extends the {@code SortableEnquiryStudent} class 
 * It provides additional commitee-specific sortable attributes for the object of type {@code Enquiry}
 */
public class SortableEnquiryCommittee extends SortableEnquiryStudent{
    /**
     * Provides committee-specific sortable attributes.
     *
     * @return {@code ArrayList} of {@code ComparableAttribute<Enquiry>} representing sortable attributes for Enquiry object
     */
    public ArrayList<ComparableAttribute<Enquiry>> getSortableAttributes(){
        ArrayList<ComparableAttribute<Enquiry>> sortableAttributes = super.getSortableAttributes();
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Submitted by", Comparator.comparing(Enquiry::getSubmittedBy)));
        return sortableAttributes;
    }
}
