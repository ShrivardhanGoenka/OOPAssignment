/**
 * The {@code UserActions} provides an abstract for all required action for the User class
 * This includes changing password, logging in, logging out, and getting each attribute.
 */
public interface UserActions{
    /**
     * Changes the user's password to the specified new password.
     *
     * @param newPassword 			The new password to set.
     * @throws UserException 			If an error occurs during the password change process.
     */
    public void changePassword(String newPassword) throws UserException;

    /**
     * Authenticates the user by verifying the provided password.
     *
     * @param password 				The provided password to verify.
     * @return 					{@code true} if the login is successful, {@code false} otherwise.
     * @throws UserException 			If an error occurs during the login process.
     */
    public boolean login(String password) throws UserException;

    /**
     * Logs the user out of the system.
     *
     * @throws UserException 			If an error occurs during the logout process.
     */
    public void logout() throws UserException;

    /**
     * Retrieves the email of the user.
     *
     * @return 					The user's email.
     */
    public String getEmail();

    /**
     * Retrieves the ID of the user.
     *
     * @return 					The user's ID.
     */
    public String getUserID();

    /**
     * Retrieves the faculty of the user.
     *
     * @return 					The user's faculty information.
     */
    public String getFaculty();

    /**
     * Retrieves if the account is locked.
     *
     * @return 					{@code true} if the account is locked, {@code false} otherwise.
     */
    public boolean isLocked();


    /***
     * Retrieves the password of the user.
     *
     * @return  				The password of the user
     */
    public String getPassword();
}
