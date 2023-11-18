public class Staff extends User{
    public Staff(String userID, String password, String email, String faculty, boolean isLocked){
        super(userID, password, email, faculty, isLocked);
    }

    @Override
    public void printProfile() {
        super.printProfile();
        System.out.println("Domain: Staff");
    }

    @Override
    public String DBWriter(){
        String output = super.DBWriter();
        output += "Staff\n";
        return output;
    }
}
