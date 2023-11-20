import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public abstract class Sortable<T> {
	public abstract ArrayList<ComparableAttribute<T>> getSortableAttributes();
	void sortArrayList(ArrayList<T> arrayList, ComparableAttribute<T> attribute){
		arrayList.sort(attribute.getComparator());
	}

	ArrayList<T> runMenu(ArrayList<T> list){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<ComparableAttribute<T>> sortableAttributes = getSortableAttributes();
		System.out.println("Please select the attribute to sort");
		for (int i=0;i<sortableAttributes.size();i++) {
			System.out.printf("%d. %s\n", i+1, sortableAttributes.get(i).getAttributeName());
		}
		int choice = 0;
		try {
			choice = Integer.parseInt(br.readLine());
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("Invalid option, using default sorting option");
		}
		if (choice < 1 || choice > sortableAttributes.size()) {
			System.out.println("Invalid option, using default sorting option");
			return list;
		}

		ComparableAttribute<T> attribute = sortableAttributes.get(choice-1);
		sortArrayList(list, attribute);
		return list;
	}
}