import java.util.ArrayList;
public class Sortable{

	@FunctionalInterface
	public interface ObjectComparator<T> {
		int compare(T object1, T object2);
	}

	public static <T> void sortObjects(ArrayList<T> objects, ObjectComparator<T> comparator) {
		objects.sort(comparator::compare);
	}
}
