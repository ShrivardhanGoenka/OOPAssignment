import java.util.ArrayList;
import java.util.Comparator;

public class SortableEnquiryCommittee extends SortableEnquiryStudent{
    public ArrayList<ComparableAttribute<Enquiry>> getSortableAttributes(){
        ArrayList<ComparableAttribute<Enquiry>> sortableAttributes = super.getSortableAttributes();
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Submitted by", Comparator.comparing(Enquiry::getSubmittedBy)));
        return sortableAttributes;
    }
}
