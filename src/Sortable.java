import java.util.ArrayList;
public abstract class Sortable<T> {
	public abstract ArrayList<ComparableAttribute<T>> getSortableAttributes();
	public void sortArrayList(ArrayList<T> arrayList, ComparableAttribute<T> attribute){
		arrayList.sort(attribute.getComparator());
	}

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