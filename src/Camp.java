import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
* The Camp class represents a camp with a specific constraints and information
* It extends the {@link CampInformation} class.
*
* @see  	{@link CampInformation}
* @see        	{@link CampConstrants} 
*/
public class Camp extends CampInformation {

    // Fields
    /** The constraints for this camp*/
    CampConstraints campConstraints;

    // Constructor for new camp by staff
    /** List of registered attendees for this camp*/
    private ArrayList<String> attendees;

    /** List of committee members for this camp*/
    private ArrayList<String> committeeMembers;

    /** Map of enquiry ID and enquiry */
    private HashMap<Integer, Enquiry> campEnquiries;

    /** Map of suggestion ID and suggestion */
    private HashMap<Integer, Suggestion> campSuggestions;

    // Constructors
    /** Constructs a new empty camp by staff
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
        super(campID, campName, campDates, registrationDeadline, schoolOpenTo, location, totalSlots, campCommitteeSlots, description, staffID, true);
        campConstraints = new CampConstraints(totalSlots, campCommitteeSlots, registrationDeadline, schoolOpenTo, new ArrayList<String>(), campDates);
        attendees = new ArrayList<>();
        committeeMembers = new ArrayList<>();
        campEnquiries = new HashMap<>();
    }

    // Constructors for an existing camp from database.
    /**
     * @param campID 			The ID of the camp.
     * @param campName 			The name of the camp.
     * @param campDates 		The dates of the camp.
     * @param registrationDeadline 	The deadline for registrations.
     * @param schoolOpenTo 		The allowed schools from which attendees are allowed to register for the camp.
     * @param location 			The location of the camp.
     * @param totalSlots 		The total slots for the attendees and committees of the camp.
     * @param campCommitteeSlots 	The reserved slots for the camp committee.
     * @param description 		The description of the camp.
     * @param staffID 			The staff ID of the committee organizing the camp.
     * @param withdrawn 		List of withdrawn registrants.
     * @param attendees 		List of attendees.
     * @param committeeMembers 		List of committee members.
     */
    public Camp(int campID, String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo, String location, int totalSlots, int campCommitteeSlots, String description, String staffID, ArrayList<String> withdrawn, ArrayList<String> attendees, ArrayList<String> committeeMembers, boolean isCampVisible, HashMap<Integer,Enquiry> campEnquiries, HashMap<Integer,Suggestion> campSuggestions){
        super(campID, campName, campDates, registrationDeadline, schoolOpenTo, location, totalSlots, campCommitteeSlots, description, staffID, isCampVisible);
        campConstraints = new CampConstraints(totalSlots, campCommitteeSlots, registrationDeadline, schoolOpenTo, withdrawn, campDates);
        this.attendees = new ArrayList<>(attendees);
        this.committeeMembers = new ArrayList<>(committeeMembers);
        this.campEnquiries = campEnquiries;
        this.campSuggestions = campSuggestions;
    }

    // Methods
    /**
     * Checks if a user is already registered for the camp.
     *
     * @param userID 			The ID of user to check.
     * @throws CampException 		If the user is already registered.
     */
    public void checkUserAlreadyRegistered(String userID) throws CampException{
        if(attendees.contains(userID) || committeeMembers.contains(userID)){
            throw new CampException("User already registered!");
        }
    }

    /**
     * Registers a new attendee for the camp.
     *
     * @param userID 			The ID of user to be registered.
     * @param blockedDates 		The dates the attendee is not available.
     * @param faculty 			The faculty of the attendee.
     * @throws CampException 		if the registration fails.
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
     * @throws CampException 		if the registration fails.
     */
    public void registerCommitteeMember(String userID, ArrayList<Date> blockedDates, String faculty) throws CampException{
        campConstraints.checkCommitteeRegistration(userID, blockedDates, faculty);
        campConstraints.addCommitteeMember();
        committeeMembers.add(userID);
    }

    /**
     * Changes the total attendee slots.
     *
     * @param newSlots 			The new number of available slots for attendees and committees.
     * @throws CampException 		if {@code newSlots} is less than already registered slots.
     */
    public void setTotalSlots(int newSlots) throws CampException{
        int difference = totalSlots - newSlots;
        if (campConstraints.changeAttendeeSlots(difference)) {
            totalSlots = newSlots;
        }
        else{
            throw new CampException("The registrations exceed the number of slots.");
        }
    }

    /**
     * Changes the reserved slots for committees.
     *
     * @param newSlots 			The new number of reserved slots for committees.
     * @throws CampException 		if {@code newSlots} is less than already registered slots.
     */
    public void setCampCommitteeSlots(int newSlots) throws CampException{
        int difference = campCommitteeSlots - newSlots;
        if (campConstraints.changeCampCommitteeSlots(difference)) {
            campCommitteeSlots = newSlots;
        }
        else{
            throw new CampException("The registrations exceed the number of slots.");
        }
    }

    /**
     * Adds the enquiry to the camp.
     *
     * @param enquiry 			The enquiry.
     */
    public void addCampEnquiry(Enquiry enquiry){
        campEnquiries.put(enquiry.getID(), enquiry);
    }


    /** 
    * Removes the enquiry to campEnquiries 
    *
    * @param enquiryID 			The ID of enquiry to remove.
    */
    public void deleteCampEnquiry(int enquiryID){campEnquiries.remove(enquiryID);}

    /**
     * Withdraws the attendee
     *
     * @param userID 			The ID of user to withdraw.
     */
    public void withdrawAttendee(String userID){
        int i = 0;
        for(String u: attendees){
            if(userID.equalsIgnoreCase(u)) break;
            i++;
        }
        attendees.remove(i);
        campConstraints.withdrawAttendee(userID);
    }

    /**
     * Retreives id list of attendees.
     *
     * @return {@code attendees} 		The ArrayList of attendees' userID
     */
    public ArrayList<String> getAttendees(){
        return attendees;
    }

    /**
     * Retreives id list of committees. 
     *
     * @return {@code committeeMembers} 	The ArrayList of ' userID
     */
    public ArrayList<String> getCommitteeMembers(){
        return committeeMembers;
    }

    /**
     * Retreives the campEnquiries
     *
     * @return {@code campEnquiries}  		The HashMap of enquiryID and {@code Enquiry}.
     */
    public HashMap<Integer, Enquiry> getCampEnquiries(){
        return campEnquiries;
    }

    /**
     * Retreives the campSuggestions
     *
     * @return {@code campSuggestions}  		The HashMap of enquiryID and {@code Suggestion}.
     */
    public HashMap<Integer, Suggestion> getCampSuggestions(){
        return campSuggestions;
    }

    /** 
    * Removes the enquiry to campEnquiries 
    *
    * @param enquiryID 			The ID of enquiry to remove.
    */
    public void deleteCampSuggestion(int enquiryID){campSuggestions.remove(enquiryID);}
}
