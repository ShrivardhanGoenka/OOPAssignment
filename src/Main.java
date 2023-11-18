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

		Student test = new Student("test", "test", "test", "test", false);
		Registry.studentMap.put("test", test);

		dbInterface.writeToDB();

//		System.out.println(Registry.studentMap.get("student1").DBWriter() + "\n\n\n");
//		System.out.println(Registry.studentMap.get("student2").DBWriter() + "\n\n\n");
//		System.out.println(Registry.studentMap.get("student3").DBWriter() + "\n\n\n");
//		System.out.println(Registry.committeeMap.get("student4").DBWriter() + "\n\n\n");
//		System.out.println(Registry.campMap.get(1).DBWriter() + "\n\n\n");
//		System.out.println(Registry.campMap.get(2).DBWriter() + "\n\n\n");

		//dbInterface.writeToDB();
		System.exit(0);
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

