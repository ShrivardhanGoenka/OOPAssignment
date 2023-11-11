import java.util.ArrayList;
import java.util.Date;
public class Camp extends CampInformation {
    CampConstraints campConstraints;
    private ArrayList<String> attendees;
    private ArrayList<String> committeeMembers;

    // Constructor for new camp by staff
    public Camp(int campID, String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo, String location, int totalSlots, int campCommitteeSlots, String description, String staffID){
        super(campID, campName, campDates, registrationDeadline, schoolOpenTo, location, totalSlots, campCommitteeSlots, description, staffID, true, true);
        campConstraints = new CampConstraints(totalSlots, campCommitteeSlots, registrationDeadline, schoolOpenTo, new ArrayList<String>(), campDates);
        attendees = new ArrayList<>();
        committeeMembers = new ArrayList<>();
    }
    // Constructor for existing camp from db
    public Camp(int campID, String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo, String location, int totalSlots, int campCommitteeSlots, String description, String staffID, ArrayList<String> withdrawn, ArrayList<String> attendees, ArrayList<String> committeeMembers, boolean isCampActive, boolean isCampVisible){
        super(campID, campName, campDates, registrationDeadline, schoolOpenTo, location, totalSlots, campCommitteeSlots, description, staffID, isCampActive, isCampVisible);
        campConstraints = new CampConstraints(totalSlots, campCommitteeSlots, registrationDeadline, schoolOpenTo, withdrawn, campDates);
        this.attendees = new ArrayList<>(attendees);
        this.committeeMembers = new ArrayList<>(committeeMembers);
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
}
