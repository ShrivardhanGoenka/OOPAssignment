/**
 * The {@code Admin} class represents the admin account of the Camp management system.
 * The {@code Admin} can lock/unlock user's account, create new user, and change the password on behalf of the user.
 */
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

    /**
     * Unlocks user account
     * @param userObject            The user to unlock
     */
    public void unlockUser(User userObject) {
        userObject.unlockAccount();
    }

    /**
     * Locks user account
     * @param userObject            The user to lock
     */
    public void lockUser(User userObject) {
        userObject.unlockAccount();
    }

    /**
     * Changes the user password
     * @param userObject            The user to lock
     * @param password              The new password to change to
     * @throws UserException        If the password change is unsuccessful
     */
    public void changeUserPassword(User userObject, String password) throws UserException {
        try{
            userObject.changePassword(password);
        } catch(UserException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the user's profile information to System.out.
     */
    public void printProfile(){
        super.printProfile();
        System.out.println("Domain: admin" );
    }

    /**
     * Creates a new student object, and Adds the new student created to the Registry.
     * @param userID                The userID of new user account
     * @param password              The password of new user account
     * @param email                 The email of the user
     * @param faculty               The faculty the new user is from
     */
    public void createStudent(String userID, String password, String email, String faculty){
        if (checkValidUserID(userID)) {
            Student student = new Student(userID, password, email, faculty, false);
            RegistryFactory.studentRegistry.addEntry(student, userID);
        } else {
            System.out.println("Unable to create new user as userID has already been used");
        }
    }

    /**
     * Creates a new staff object, and Adds the new staff created to the Registry.
     * @param userID                The userID of new staff account
     * @param password              The password of new staff account
     * @param email                 The email of the staff
     * @param faculty               The faculty the new staff is from
     */
    public void createStaff(String userID, String password, String email, String faculty){
    	if (checkValidUserID(userID)) {
        	Staff staff = new Staff(userID, password, email, faculty, false);
        	RegistryFactory.staffRegistry.addEntry(staff, userID);
    	} else {
        	System.out.println("Unable to create new user as userID has already been used");
    	}
    }

    /**
     * Checks whether the new userID is valid (the new userID must not match any old userID in the Registry)
     *
     * @param userID                The new userID of the account to be created
     * @return A boolean indicating whether the new userID is valid
     */
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
