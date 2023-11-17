import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.Date;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        DBInterface dbInterface = new DBInterface();
        dbInterface.loadNextValues();
        dbInterface.populateSuggestions();
        dbInterface.populateEnquiries();
        dbInterface.populateCamps();
        dbInterface.populateStudents();
	dbInterface.populateCampCommittees();
	while (true) { 
		try {
		    String userID = UserLoginDriver.authenticateUser();
		    if (Registry.committeeMap.containsKey(userID)) { // not really a good way to check
			CommitteeMenuDriver.accountMenu(userID, MenuFactory.getCommitteeMenu());
		    } else if (Registry.studentMap.containsKey(userID)){
			StudentMenuDriver.accountMenu(userID, MenuFactory.getStudentMenu());
		    } else {continue;}
		}catch (Exception e){
		    e.printStackTrace();
		}
	}
    }
}

