import java.util.ArrayList;
import java.util.Date;
public class Camp {
    CampInformation campInformation;
    CampConstraints campConstraints;
    ArrayList<String> attendees;
    ArrayList<String> committeeMembers;

    // Constructor for new camp by staff
    public Camp(int campID, String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo, String location, int totalSlots, int campCommitteeSlots, String description, String staffID){
        campInformation = new CampInformation(campID, campName, campDates, registrationDeadline, schoolOpenTo, location, totalSlots, campCommitteeSlots, description, staffID);
        campConstraints = new CampConstraints(totalSlots, campCommitteeSlots, registrationDeadline, schoolOpenTo, new ArrayList<String>());
    }

    // Constructor for existing camp from db
    public Camp(int campID, String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo, String location, int totalSlots, int campCommitteeSlots, String description, String staffID, ArrayList<String> withdrawn, ArrayList<String> attendees, ArrayList<String> committeeMembers){
        campInformation = new CampInformation(campID, campName, campDates, registrationDeadline, schoolOpenTo, location, totalSlots, campCommitteeSlots, description, staffID);
        campConstraints = new CampConstraints(totalSlots, campCommitteeSlots, registrationDeadline, schoolOpenTo, withdrawn);
        this.attendees = new ArrayList<String>(attendees);
        this.committeeMembers = new ArrayList<String>(committeeMembers);
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

}
