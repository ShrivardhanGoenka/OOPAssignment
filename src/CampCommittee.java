import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CampCommittee extends Student {

    Camp camp;
    HashMap<Integer, Suggestion> submittedSuggestions;
    public CampCommittee(String userID, String password, String email, String faculty, boolean isLocked, Camp camp) {
        super(userID, password, email, faculty, isLocked);
        this.camp = camp;
        submittedSuggestions = new HashMap<>();
    }

    public CampCommittee(String userId, String password, String email, String faculty, boolean isLocked, HashMap<Integer, Enquiry> enquiryMap, HashMap<Integer, Camp> registeredCamps, ArrayList<Date> blockedDates, Camp camp, HashMap<Integer, Suggestion> submittedSuggestions) {
        super(userId, password, email, faculty, isLocked, enquiryMap, registeredCamps, blockedDates);
        this.camp = camp;
        this.submittedSuggestions = submittedSuggestions;
    }

    public void printProfile() {
        super.printProfile();
        System.out.println("Domain: Student");
        System.out.println("Camp committee member of camp: " + camp.getCampName());
    }

    public ArrayList<Suggestion> viewSuggestions(){
        return new ArrayList<>(submittedSuggestions.values());
    }



}
