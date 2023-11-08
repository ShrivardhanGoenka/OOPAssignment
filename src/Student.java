import java.util.HashMap;
public class Student extends User{
    private HashMap<Integer, Enquiry> enquiryMap;
    public Student(String userID, String password, String email, String faculty, boolean isLocked){
        super(userID, password, email, faculty, isLocked);
    }



}
