import java.util.ArrayList;
public interface Sortable<T> {
	public ArrayList<ComparableAttribute<T>> getSortableAttributes();
	void sortArrayList(ArrayList<T> arrayList, ComparableAttribute<T> attribute);
}