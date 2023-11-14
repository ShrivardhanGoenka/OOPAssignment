/**
 * The {@code User} class represents a user in the system and implements the {@code UserActions} interface.
 * It provides methods to manage user actions such as changing passwords, logging in, controling user login, retreiving user information, and logging out.
 */
public class User implements UserActions{
    private String userID;
    private String password;
    private String email;
    private String faculty;
    private boolean isLoggedIn;
    private boolean isLocked;
    private int loginAttempts;
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
     * @param newPassword 			The new password to set.
     * @throws UserException 			If the user is not logged in.
     */
    @Override
    public void changePassword(String newPassword) throws UserException{
        if(isLoggedIn == false) throw new UserException("User not logged in");
        this.password = newPassword;
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
     * Prints the user's profile information.
     */
    public void printProfile(){
        System.out.println("UserID: " + userID);
        System.out.println("Email Address: " + email);
        System.out.println("Faculty: " + faculty);
    }
}
