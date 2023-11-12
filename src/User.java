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
    @Override
    public void changePassword(String newPassword) throws UserException{
        if(isLoggedIn == false) throw new UserException("User not logged in");
        this.password = newPassword;
    }

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

    @Override
    public void logout() throws UserException{
        if(isLoggedIn == false) throw new UserException("User not logged in");
        isLoggedIn = false;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getUserID() {
        return userID;
    }

    @Override
    public String getFaculty() {
        return faculty;
    }

    public void printProfile(){
        System.out.println("UserID: " + userID);
        System.out.println("Email Address: " + email);
        System.out.println("Faculty: " + faculty);
    }
}
