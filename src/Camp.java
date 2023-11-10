import java.util.ArrayList;
import java.util.Date;

/**
* {@code Camp} class represents a camp with a specific constraints and information
* It extends the {@link CampInformation}
*
* @see  	{@link CampInformation}
* @see        	{@link CampConstrants} 
*/

public class Camp extends CampInformation {

    // Fields
    /** The constraints for the camp*/
    CampConstraints campConstraints;

    /** List of attendees for the camp*/
    ArrayList<String> attendees;

    /** List of committee members for the camp*/
    ArrayList<String> committeeMembers;

    // Constructors 
    /**
     * Constructs a new camp by staff
     * @param campID 			The ID of the camp.
     * @param campName 			The name of the camp.
     * @param campDates  		The dates of the camp.
     * @param registrationDeadline 	The deadline for registration.
     * @param schoolOpenTo 		The schools in which students are allowed to attend the camp.
     * @param location 			The location of the camp.
     * @param totalSlots 		The total slots for the attendees of the camp.
     * @param campCommitteeSlots 	The total slots for the camp committee.
     * @param description 		The description of the camp.
     * @param staffID 			The staff ID of the committee organizing the camp.
     */
    public Camp(int campID, String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo, String location, int totalSlots, int campCommitteeSlots, String description, String staffID){
        super(campID, campName, campDates, registrationDeadline, schoolOpenTo, location, totalSlots, campCommitteeSlots, description, staffID);
        campConstraints = new CampConstraints(totalSlots, campCommitteeSlots, registrationDeadline, schoolOpenTo, new ArrayList<String>());
    }

    // Constructors for an existing camp from database.
    /**
     * @param campID 			The ID of the camp.
     * @param campName 			The name of the camp.
     * @param campDates 		The dates of the camp.
     * @param registrationDeadline 	The deadline for registrations.
     * @param schoolOpenTo 		The schools in which students are allowed to attend the camp.
     * @param location 			The location of the camp.
     * @param totalSlots 		The total slots for the attendees of the camp.
     * @param campCommitteeSlots 	The total slots for the camp committee.
     * @param description 		The description of the camp.
     * @param staffID 			The staff ID of the committee organizing the camp.
     * @param withdrawn 		List of withdrawn registrants.
     * @param attendees 		List of attendees.
     * @param committeeMembers 		List of committee members.
     */
    public Camp(int campID, String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo, String location, int totalSlots, int campCommitteeSlots, String description, String staffID, ArrayList<String> withdrawn, ArrayList<String> attendees, ArrayList<String> committeeMembers){
        super(campID, campName, campDates, registrationDeadline, schoolOpenTo, location, totalSlots, campCommitteeSlots, description, staffID);
        campConstraints = new CampConstraints(totalSlots, campCommitteeSlots, registrationDeadline, schoolOpenTo, withdrawn);
        this.attendees = new ArrayList<String>(attendees);
        this.committeeMembers = new ArrayList<String>(committeeMembers);
    }

    // Methods
    /**
     * Checks if a user is already registered for the camp.
     *
     * @param userID 			The ID of user to check.
     * @throw {@link CampException} error if the user is already registered.
     */
    public void checkUserAlreadyRegistered(String userID) throws CampException{
        if(attendees.contains(userID) || committeeMembers.contains(userID)){
            throw new CampException("User already registered!");
        }
    }

    /**
     * Registers a new attendee for the camp.
     *
     * @params userID 			The ID of user to be registered.
     * @param blockedDates 		The dates the attendee is not available.
     * @param faculty 			The faculty of the attendee.
     * @throw {@link CampException} if the registration fails.
     */
    public void registerAttendee(String userID, ArrayList<Date> blockedDates, String faculty) throws CampException{
        campConstraints.checkAttendeeRegistration(userID, blockedDates, faculty);
        campConstraints.addRegistration();
        attendees.add(userID);
    }

    /**
     * Registers a committee member for the camp.
     *
     * @param userID 			The ID of user to be registered.
     * @param blockedDates 		The dates the committee member is not available.
     * @param faculty 			The faculty of the committee member.
     * @throw {@link CampException} if the registration fails.
     */
    public void registerCommitteeMember(String userID, ArrayList<Date> blockedDates, String faculty) throws CampException{
        campConstraints.checkCommitteeRegistration(userID, blockedDates, faculty);
        campConstraints.addCommitteeMember();
        committeeMembers.add(userID);
    }
}
