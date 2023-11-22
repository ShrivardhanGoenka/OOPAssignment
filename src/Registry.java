import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
public class Registry {
    public static HashMap<String, Student> studentMap = new HashMap();
    public static HashMap<String, CampCommittee> committeeMap = new HashMap();
    public static HashMap<String, Staff> staffMap = new HashMap();
    public static HashMap<Integer, Camp> campMap = new HashMap<>();
    public static HashMap<Integer, Enquiry> enquiryMap = new HashMap<>();
    public static HashMap<Integer, Suggestion> suggestionMap = new HashMap<>();
    public static int nextCampID;
    public static int nextEnquiryID;
    public static int nextSuggestionID;

    public static ArrayList<Camp> getAllCamps(){
        return new ArrayList<>(campMap.values());
    }
    public static boolean authenticateUser(String username, String password) throws UserException{
        if (studentMap.containsKey(username)){
            return studentMap.get(username).login(password);
        }
        else if (committeeMap.containsKey(username)){
            return committeeMap.get(username).login(password);
        }
        else if (staffMap.containsKey(username)){
            return staffMap.get(username).login(password);
        }
        return false;
    }
}
