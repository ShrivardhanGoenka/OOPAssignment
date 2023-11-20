import java.util.Comparator;

public class ComparableAttribute<T>{
    String attributeName;
    Comparator<T> comparator;
    public ComparableAttribute(String attributeName, Comparator<T> comparator){
        this.attributeName = attributeName;
        this.comparator = comparator;
    }
    public String getAttributeName(){
        return attributeName;
    }
    public Comparator<T> getComparator(){
        return comparator;
    }
}
