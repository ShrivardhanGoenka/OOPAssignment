import java.util.ArrayList;
import java.util.Comparator;
//
/**
 * The {@code SortableCamp} class extends the abstract class {@code Sortable} and provides sorting attributes for objects of type {@link Camp}.
 * It defines how camps can be sorted based on various attributes (e.g., name, deadline, location, total number of slots).
 */
public class SortableCamp extends Sortable<Camp>{

    /**
     * Returns the list of sortable attributes specific to camps.
     *
     * @return {@code ArrayList} of {@code ComparableAttribute} instances representing different sorting attributes.
     */
    @Override
    public ArrayList<ComparableAttribute<Camp>> getSortableAttributes() {
        ArrayList<ComparableAttribute<Camp>> sortableAttributes = new ArrayList<ComparableAttribute<Camp>>();
        sortableAttributes.add(new ComparableAttribute<Camp>("Camp Name", Comparator.comparing(Camp::getCampName)));
        sortableAttributes.add(new ComparableAttribute<Camp>("Registration Deadline", Comparator.comparing(Camp::getRegistrationDeadline)));
        sortableAttributes.add(new ComparableAttribute<Camp>("Faculty Open To", Comparator.comparing(Camp::getFacultyOpenTo)));
        sortableAttributes.add(new ComparableAttribute<Camp>("Location", Comparator.comparing(Camp::getLocation)));
        sortableAttributes.add(new ComparableAttribute<Camp>("Total Slots", Comparator.comparing(Camp::getTotalSlots)));
        sortableAttributes.add(new ComparableAttribute<Camp>("Camp Committee Slots", Comparator.comparing(Camp::getCampCommitteeSlots)));
        sortableAttributes.add(new ComparableAttribute<Camp>("Camp Description", Comparator.comparing(Camp::getDescription)));
        sortableAttributes.add(new ComparableAttribute<Camp>("Camp Visibility", Comparator.comparing(Camp::isVisible)));
        sortableAttributes.add(new ComparableAttribute<Camp>("Camp Created By", Comparator.comparing(Camp::getStaffID)));
        return sortableAttributes;
    }
}
