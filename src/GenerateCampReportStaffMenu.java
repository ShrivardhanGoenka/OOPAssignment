import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GenerateCampReportStaffMenu extends IMenu<Staff> {
	public void runMenu(Staff staffObject) {
		ArrayList<Camp> campList = staffObject.createdCamps();
		ConsoleReaderInteger cri = new ConsoleReaderInteger();
		System.out.println("Choose the camp to generate report");
		for (int i=0;i<campList.size();i++) {
			System.out.printf("%d. %s\n", i+1, campList.get(i).getCampName());
		}
		try {
			Integer choice = cri.readFromConsole(1, campList.size());
			writeCampToDatabase(campList.get(choice-1), staffObject.getUserID());
		} catch (InputException e) {
			e.printStackTrace();
		}
	}

	public void writeCampToDatabase(Camp camp, String userID) {
		ArrayList<String> filterField = new ArrayList<String>();
		filterField.add("Attendee");
		filterField.add("Committee");
		filterField.add("Enquiry");
		filterField.add("Suggestion");
		HashMap<String, Boolean> filter = new HashMap<>();
		ConsoleReaderString crs = new ConsoleReaderString();
		TXTFileReportDBWriter databaseWriter = new TXTFileReportDBWriter();
		for (String field: filterField) {
			System.out.printf("Do you want to include camp %s in the report? (y/n)\n", field);
			try {
				String choice = crs.readFromConsole();
				if (choice.equals("y")) {
					filter.put(field, true);
				} else if (choice.equals("n")) {
					filter.put(field, false);
				} else {
					System.out.println("Invalid choice");
					return;
				}
			} catch(InputException e) {
				e.printStackTrace();
			}
		}
		databaseWriter.writeToDatabase(camp, filter, userID);
	}

	/**
	 * @return the menu description
	 */
	public String getMenuDescription () {
		return "Generate Camp Report";
	}
}
