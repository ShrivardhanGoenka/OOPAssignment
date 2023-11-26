import java.util.Date;
import java.util.ArrayList;
/**
 * The Main class to runs the camp management system
 */
public class Main {

    /**
     * Main function to drives the program
     * @param args 	Input arguments
     */
    public static void main(String[] args){
		try{
			DBReader.Initialise();
		} catch (Exception e){
			System.out.println("Fatal Error in reading Database. Please contact the administrator.");
			System.out.println(e.getMessage());
		}

        while (true)
			try {
				String userID = UserLoginDriver.authenticateUser();
				if (RegistryFactory.committeeRegistry.getEntry(userID) != null) {
					Driver.accountMenu(RegistryFactory.committeeRegistry.getEntry(userID), MenuFactory.getCommitteeMenu());
				} else if (RegistryFactory.studentRegistry.getEntry(userID) != null){
					Driver.accountMenu(RegistryFactory.studentRegistry.getEntry(userID), MenuFactory.getStudentMenu());
				}
				else if(RegistryFactory.staffRegistry.getEntry(userID) != null){
					Driver.accountMenu(RegistryFactory.staffRegistry.getEntry(userID), MenuFactory.getStaffMenu());
				} else if (RegistryFactory.adminRegistry.getEntry(userID) != null){
					Driver.accountMenu(RegistryFactory.adminRegistry.getEntry(userID), MenuFactory.getAdminMenu());
				}
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
    }


