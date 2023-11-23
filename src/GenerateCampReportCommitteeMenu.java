import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GenerateCampReportCommitteeMenu extends IMenu<CampCommittee> {
	public void runMenu(CampCommittee committeeObject) {
		Camp camp = committeeObject.getCamp();
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
		databaseWriter.writeToDatabase(camp, filter, committeeObject.getUserID());
	}

	/**
	 * @return the menu description
	 */
	public String getMenuDescription () {
		return "Generate Camp Report";
	}
}
