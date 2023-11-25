/**
 * The {@code User} class represents a user in the system and implements the {@code UserActions} interface.
 * It provides methods to manage user actions such as changing passwords, logging in, controling user login, retreiving user information, and logging out.
 */
public class User implements UserActions{
    /**
     * The unique userID of this student.
     */
    private String userID;

    /** 
     * The password of this user account.
     */
    private String password;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The faculty this user is from.
     */
    private String faculty;

    /**
     * A boolean flag indicating if the user is currently logged in.
     */
    private boolean isLoggedIn;

    /**
     * A boolean flag indicating if the account is locked.
     */
    private boolean isLocked;

    /**
     * The number of unsuccessful login attempt made by this user since last successful login.
     */
    private int loginAttempts;

    /**
     * The Constructors for a new user.
     *
     * @param userID 				The unique userID.
     * @param password 				The password of this account.
     * @param email 				The email address of this user.
     * @param faculty 				The faculty this user is from.
     * @param isLocked 				A boolean indicating whether the account is locked.
     */
    public User(String userID, String password, String email, String faculty, boolean isLocked){
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.faculty = faculty;
        this.isLocked = isLocked;
        isLoggedIn = false;
        loginAttempts = 0;
    }

    /**
     * Changes the user's password to the specified new password.
     *
     * @param newPassword 			The new password to set to.
     * @throws UserException 			If the user is not logged in.
     */
    @Override
    public void changePassword(String newPassword) throws UserException{
        this.password = newPassword;
    }
    /**
     * Checks if the user is logged in
     *
     * @return 					A boolean value indicating if the user is logged in.
     */
    public boolean isLoggedIn() {
	return isLoggedIn;
    }

    /**
     * Authenticates the user by verifying the provided password.
     *
     * @param password 				The password to verify.
     * @return 					{@code true} if the login is successful, otherwise {@code false}.
     * @throws UserException 			If the user is locked, already logged in, or login attempts exceed the limit.
     */
    @Override
    public boolean login(String password) throws UserException{
        if (isLocked == true) throw new UserException("User is locked. Please contact admin");
        if(isLoggedIn == true) throw new UserException("User already logged in");
        if(this.password.equals(password)){
            isLoggedIn = true;
            loginAttempts = 0;
            return true;
        }
        else{
            loginAttempts++;
            if(loginAttempts == 3){
                isLocked = true;
                throw new UserException("User is locked. Please contact admin");
            }
        }
        return false;
    }

    /**
     * Logs the user out of the system.
     *
     * @throws UserException 			If the user is not logged in.
     */
    @Override
    public void logout() throws UserException{
        if(isLoggedIn == false) throw new UserException("User not logged in");
        isLoggedIn = false;
    }

    /**
     * Retrieves the email of the user.
     *
     * @return 					The user's email.
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the ID of the user.
     *
     * @return 					The user's ID.
     */
    @Override
    public String getUserID() {
        return userID;
    }

    /**
     * Retrieves the faculty of the user.
     *
     * @return 					The user's faculty information.
     */
    @Override
    public String getFaculty() {
        return faculty;
    }

    /**
     * Prints the user's profile information to System.out.
     */
    public void printProfile(){
        System.out.println("UserID: " + userID);
        System.out.println("Email Address: " + email);
        System.out.println("Faculty: " + faculty);
    }

    /**
     * Locks the account.
     * Admin can change {@code isLocked} state of every account.
     */
    public void unlockAccount() {
	isLocked = false;
    }

    /**
     * Unlocks the account.
     * Admin can change {@code isLocked} state of every account.
     */
    public void lockAccount() {
	isLocked = true;
    }

    /**
     * Checks if the user account is locked.
     *
     * @return A boolean indicating whether the user account is locked.
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Retrieves the use password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Formats the information of the user to a String (for writing to database).
     * @return {@code String} 			The String containing password, email, faculty, and isLocked attributes of the user.
     */
    public String DBWriter(){
        String output = "";
        output += password + "\n";
        output += email + "\n";
        output += faculty + "\n";
        output += (isLocked? "lock":"unlock") + "\n";
        return output;
    }
}
