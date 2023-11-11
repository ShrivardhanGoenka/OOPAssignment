import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
public class Registry {
    static HashMap<String, Student> studentMap = new HashMap<String, Student>();
    static HashMap<String, Staff> staffMap = new HashMap<String, Staff>();
    static HashMap<Integer, Camp> campMap = new HashMap<>();
    static HashMap<Integer, Enquiry> enquiryMap = new HashMap<>();
    static HashMap<Integer, Suggestion> suggestionList = new HashMap<>();
    static int nextCampID;
    static int nextEnquiryID;
    static int nextSuggestionID;
}
