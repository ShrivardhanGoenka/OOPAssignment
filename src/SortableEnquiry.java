import java.util.ArrayList;
import java.util.Comparator;

public class SortableEnquiry implements Sortable<Enquiry>{
    public ArrayList<ComparableAttribute<Enquiry>> getSortableAttributes(){
        ArrayList<ComparableAttribute<Enquiry>> sortableAttributes = new ArrayList<ComparableAttribute<Enquiry>>();
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Enquiry Content", Comparator.comparing(Enquiry::getStringValue)));
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Submitted on", Comparator.comparing(Enquiry::getSubmittedOn)));
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Submitted by", Comparator.comparing(Enquiry::getSubmittedBy)));
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Processed?", Comparator.comparing(Enquiry::isProcessed)));
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Reply", Comparator.comparing(Enquiry::getReply)));
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Replied on", Comparator.comparing(Enquiry::getRepliedOn)));
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Replied by", Comparator.comparing(Enquiry::getRepliedBy)));
        sortableAttributes.add(new ComparableAttribute<Enquiry>("Last Updated on", Comparator.comparing(Enquiry::getUpdatedOn)));
        return sortableAttributes;
    }
    public void sortArrayList(ArrayList<Enquiry> arrayList, ComparableAttribute<Enquiry> attribute){
        arrayList.sort(attribute.getComparator());
    }
}
