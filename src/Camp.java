import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Camp extends CampInformation {
    CampConstraints campConstraints;
    private ArrayList<String> attendees;
    private ArrayList<String> committeeMembers;
    private HashMap<Integer, Enquiry> campEnquiries;
    private HashMap<Integer, Suggestion> campSuggestions;

    // Constructor for new camp by staff
    public Camp(int campID, String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo, String location, int totalSlots, int campCommitteeSlots, String description, String staffID){
        super(campID, campName, campDates, registrationDeadline, schoolOpenTo, location, totalSlots, campCommitteeSlots, description, staffID, true);
        campConstraints = new CampConstraints(totalSlots, campCommitteeSlots, registrationDeadline, schoolOpenTo, new ArrayList<String>(), campDates);
        attendees = new ArrayList<>();
        committeeMembers = new ArrayList<>();
        campEnquiries = new HashMap<>();
    }
    // Constructor for existing camp from db
    public Camp(int campID, String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo, String location, int totalSlots, int campCommitteeSlots, String description, String staffID, ArrayList<String> withdrawn, ArrayList<String> attendees, ArrayList<String> committeeMembers, boolean isCampVisible, HashMap<Integer,Enquiry> campEnquiries, HashMap<Integer,Suggestion> campSuggestions){
        super(campID, campName, campDates, registrationDeadline, schoolOpenTo, location, totalSlots, campCommitteeSlots, description, staffID, isCampVisible);
        campConstraints = new CampConstraints(totalSlots, campCommitteeSlots, registrationDeadline, schoolOpenTo, withdrawn, campDates);
        this.attendees = new ArrayList<>(attendees);
        this.committeeMembers = new ArrayList<>(committeeMembers);
        this.campEnquiries = campEnquiries;
        this.campSuggestions = campSuggestions;
    }

    public void checkUserAlreadyRegistered(String userID) throws CampException{
        if(attendees.contains(userID) || committeeMembers.contains(userID)){
            throw new CampException("User already registered!");
        }
    }
    public void registerAttendee(String userID, ArrayList<Date> blockedDates, String faculty) throws CampException{
        campConstraints.checkAttendeeRegistration(userID, blockedDates, faculty);
        campConstraints.addRegistration();
        attendees.add(userID);
    }
    public void registerCommitteeMember(String userID, ArrayList<Date> blockedDates, String faculty) throws CampException{
        campConstraints.checkCommitteeRegistration(userID, blockedDates, faculty);
        campConstraints.addCommitteeMember();
        committeeMembers.add(userID);
    }

    public void setTotalSlots(int newSlots) throws CampException{
        int difference = totalSlots - newSlots;
        if (campConstraints.changeAttendeeSlots(difference)) {
            totalSlots = newSlots;
        }
        else{
            throw new CampException("The registrations exceed the number of slots.");
        }
    }

    public void setCampCommitteeSlots(int newSlots) throws CampException{
        int difference = campCommitteeSlots - newSlots;
        if (campConstraints.changeCampCommitteeSlots(difference)) {
            campCommitteeSlots = newSlots;
        }
        else{
            throw new CampException("The registrations exceed the number of slots.");
        }
    }

    public void addCampEnquiry(Enquiry enquiry){
        campEnquiries.put(enquiry.getID(), enquiry);
    }

    public void deleteCampEnquiry(int enquiryID){campEnquiries.remove(enquiryID);}
    public void withdrawAttendee(String userid){
        int i = 0;
        for(String u: attendees){
            if(userid.equalsIgnoreCase(u)) break;
            i++;
        }
        attendees.remove(i);
        campConstraints.withdrawAttendee(userid);
    }

    public ArrayList<String> getAttendees(){
        return attendees;
    }

    public ArrayList<String> getCommitteeMembers(){
        return committeeMembers;
    }
    public HashMap<Integer, Enquiry> getCampEnquiries(){
        return campEnquiries;
    }

    public HashMap<Integer, Suggestion> getCampSuggestions(){
        return campSuggestions;
    }
}
