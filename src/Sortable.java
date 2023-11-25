import java.util.ArrayList;
/**
 * The {@code Sortable} abstract class is used for sorting an {@code ArrayList} of objects of type {@code T}.
 * Subclasses are required to implement {@code getSortableAttributes} method
 * to provide a list of attributes to be used for sorting.
 *
 * @param <T> The type of object to sort
 */
public abstract class Sortable<T> {
    /**
     * Abstract method to provide a list of sortable attributes for objects of type {@code T}.
     *
     * @return {@code ArrayList} of {@code ComparableAttribute<T>} representing sortable attributes
     */
	public abstract ArrayList<ComparableAttribute<T>> getSortableAttributes();
    /**
     * Sorts an ArrayList of objects of type {@code T} based on the provided {@code ComparableAttribute}.
     *
     * @param arrayList                 The ArrayList to be sorted
     * @param attribute                 The attribute to use in the sorting
     */
	public void sortArrayList(ArrayList<T> arrayList, ComparableAttribute<T> attribute){
		arrayList.sort(attribute.getComparator());
	}

    /**
     * Runs a menu to select and apply sorting on an ArrayList of objects of type {@code T}.
     *
     * @param list                      The ArrayList to be sorted
     */
	public void runMenu(ArrayList<T> list){
		ArrayList<ComparableAttribute<T>> sortableAttributes = getSortableAttributes();
		System.out.println("Please select the attribute to sort");
		for (int i=0;i<sortableAttributes.size();i++) {
			System.out.printf("%d. %s\n", i+1, sortableAttributes.get(i).getAttributeName());
		}
		ConsoleReaderInteger cr = new ConsoleReaderInteger();
		int choice;
		try {
			choice = cr.readFromConsole(1, sortableAttributes.size());
		} catch (InputException e){
			System.out.println(e.getMessage());
			return;
		}
		ComparableAttribute<T> attribute = sortableAttributes.get(choice-1);
		sortArrayList(list, attribute);
	}
}
