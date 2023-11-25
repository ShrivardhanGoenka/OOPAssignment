public class Admin extends User{

    /**
     * The Constructors for a new Admin user.
     *
     * @param userID   The unique userID.
     * @param password The password of this account.
     * @param email    The email address of this user.
     * @param faculty  The faculty this user is from.
     * @param isLocked A boolean indicating whether the account is locked.
     */
    public Admin(String userID, String password, String email, String faculty, boolean isLocked) {
        super(userID, password, email, faculty, isLocked);
    }

    public void unlockUser(User userObject) {
	userObject.unlockAccount();
    }

    public void lockUser(User userObject) {
	userObject.unlockAccount();
    }

    public void changeUserPassword(User userObject, String password) {
		try{
			userObject.changePassword(password);
		} catch(UserException e) {
			e.printStackTrace();
		}
    }

    /**
     * Prints the user's profile information to System.out.
     */
    public void printProfile(){
		super.printProfile();
        System.out.println("Domain: admin" );
    }

    public void createStudent(String userID, String password, String email, String faculty){
	if (checkValidUserID(userID)) {
		Student student = new Student(userID, password, email, faculty, false);
		RegistryFactory.studentRegistry.addEntry(student, userID);
	} else {
		System.out.println("Unable to create new user as userID has already been used");
	}
    }

    public void createStaff(String userID, String password, String email, String faculty){
	if (checkValidUserID(userID)) {
		Staff staff = new Staff(userID, password, email, faculty, false);
		RegistryFactory.staffRegistry.addEntry(staff, userID);
	} else {
		System.out.println("Unable to create new user as userID has already been used");
	}
    }
	private boolean checkValidUserID(String userID){
		if(RegistryFactory.studentRegistry.getEntry(userID) != null){
			return false;
		}
		if(RegistryFactory.staffRegistry.getEntry(userID) != null){
			return false;
		}
		if(RegistryFactory.committeeRegistry.getEntry(userID) != null){
			return false;
		}
		if(RegistryFactory.adminRegistry.getEntry(userID) != null){
			return false;
		}
		return true;
	}
}
