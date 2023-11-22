public class Admin extends User{

    /**
     * The Constructors for a new user.
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


}
