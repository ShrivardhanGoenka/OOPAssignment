import java.util.Comparator;
/**
 * The {@code ComparableAttribute} class provides the comparator for specific type specified by the subclass, the name of the associated attribute to compare.
 * This class provides the necessary method for sorting menu.
 *
 * @param <T> The type of attributes to compare.
 */
public class ComparableAttribute<T>{
    /**
     * The name of the attribute associated with this comparator.
     */
    private String attributeName;

    /**
     * The comparator for attribute of type T.
     */
    private Comparator<T> comparator;

    /**
     * Constructor from the specified attribute name and comparator.
     *
     * @param attributeName The name of the attribute.
     * @param comparator The comparator for elements of type T.
     */
    public ComparableAttribute(String attributeName, Comparator<T> comparator){
        this.attributeName = attributeName;
        this.comparator = comparator;
    }

    /**
     * Retrieves the name of the attribute associated with this comparator.
     *
     * @return The attribute name.
     */
    public String getAttributeName(){
        return attributeName;
    }

    /**
     * Returns the comparator for this attribute.
     *
     * @return The comparator for attributes of type T.
     */
    public Comparator<T> getComparator(){
        return comparator;
    }
}
