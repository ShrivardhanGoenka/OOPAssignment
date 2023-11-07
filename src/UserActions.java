public interface UserActions{
    void changePassword(String newPassword);
    boolean login(String password);
    void logout();
    String getEmail();
    String getUserID();
    String getFaculty();
}