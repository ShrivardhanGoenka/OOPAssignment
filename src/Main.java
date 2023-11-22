public class Main {

    public static void main(String[] args){

		A obj = new AB();
		obj.print(1);
		AB obj2 = new AB();
		obj2.print(1);
		System.exit(1);

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
				if (Registry.committeeMap.containsKey(userID)) {
					Driver.accountMenu(Registry.committeeMap.get(userID), MenuFactory.getCommitteeMenu());
				} else if (Registry.studentMap.containsKey(userID)){
					Driver.accountMenu(Registry.studentMap.get(userID), MenuFactory.getStudentMenu());
				}
				else if(Registry.staffMap.containsKey(userID)){
					Driver.accountMenu(Registry.staffMap.get(userID), MenuFactory.getStaffMenu());
				}
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
    }


