public class User implements UserActions{

    @Override
    public void changePassword(String newPassword) {

    }

    @Override
    public boolean login(String password) {
        return false;
    }

    @Override
    public void logout() {

    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getUserID() {
        return null;
    }

    @Override
    public String getFaculty() {
        return null;
    }
}
