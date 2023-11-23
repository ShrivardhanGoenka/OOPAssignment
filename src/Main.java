import java.io.IOException;

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
//		try{
//		DBReader.Initialise();
//		}catch (IOException | DBException e){
//			System.out.println(e.getMessage());
//		}
		dbInterface.populateAdmin();

        while (true)
			try {
				String userID = UserLoginDriver.authenticateUser();
				if (Registry.committeeMap.containsKey(userID)) {
					Driver.accountMenu(Registry.committeeMap.get(userID), MenuFactory.getCommitteeMenu());
				} else if (Registry.studentMap.containsKey(userID)){
					Driver.accountMenu(Registry.studentMap.get(userID), MenuFactory.getStudentMenu());
				}
				else if(Registry.staffMap.containsKey(userID)){
					Driver.accountMenu(Registry.staffMap.get(userID), MenuFactory.getStaffMenu());
				} else if (Registry.adminMap.containsKey(userID)){
					Driver.accountMenu(Registry.adminMap.get(userID), MenuFactory.getAdminMenu());
				}
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
    }


