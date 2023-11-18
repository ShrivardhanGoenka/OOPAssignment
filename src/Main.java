import java.io.*;
public class Main {

    public static void main(String[] args){
        DBInterface dbInterface = new DBInterface();

        dbInterface.loadNextValues();
        dbInterface.populateSuggestions();
        dbInterface.populateEnquiries();
        dbInterface.populateCamps();
        dbInterface.populateStudents();
		dbInterface.populateCampCommittees();
		dbInterface.populateStaff();

		while (true)
			try {
				String userID = UserLoginDriver.authenticateUser();
				if (Registry.committeeMap.containsKey(userID)) { // not really a good way to check
					Driver.accountMenu(Registry.committeeMap.get(userID), MenuFactory.getCommitteeMenu());
				} else if (Registry.studentMap.containsKey(userID)){
					Driver.accountMenu(Registry.studentMap.get(userID), MenuFactory.getStudentMenu());
				}
				else if(Registry.staffMap.containsKey(userID)){
					Driver.accountMenu(Registry.staffMap.get(userID), MenuFactory.getStaffMenu());
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
    }


