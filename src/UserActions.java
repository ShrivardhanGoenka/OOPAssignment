public interface UserActions{
    void changePassword(String newPassword) throws UserException;
    boolean login(String password) throws UserException;
    void logout() throws UserException;
    String getEmail();
    String getUserID();
    String getFaculty();

}