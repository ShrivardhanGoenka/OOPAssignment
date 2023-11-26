import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* The Camp class represents a camp with a specific information
* The Camp compose of {@link CampConstraints} and references to attendee, camp committee, staff, enquiry, and suggestion.
* The Camp class provide functionality for making changes to the camp, which includes submitting enquiries, suggestions, editing the camp details, and retrieving camp information.
* It extends the {@link CampInformation} class.
*/
public class Camp extends CampInformation {

    // Fields
    /** The constraints for this camp*/
    private CampConstraints campConstraints;

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
     * @param campID 			    The ID of the camp.
     * @param campName 			    The name of the camp.
     * @param campDates  		    The dates of the camp.
     * @param registrationDeadline 	The deadline for registration.
     * @param schoolOpenTo 		    The schools in which students are allowed to attend the camp.
     * @param location 			    The location of the camp.
     * @param totalSlots 		    The total slots for the attendees of the camp.
     * @param campCommitteeSlots 	The total slots for the camp committee.
     * @param description 		    The description of the camp.
     * @param staffID 			    The staff ID of the committee organizing the camp.
     * @param isCampVisible             A boolean indicating whether the student can see this camp or not.
     */
    public Camp(int campID, String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo, String location, int totalSlots, int campCommitteeSlots, String description, String staffID, boolean isCampVisible){
        super(campID, campName, campDates, registrationDeadline, schoolOpenTo, location, totalSlots, campCommitteeSlots, description, staffID, isCampVisible);
        campConstraints = new CampConstraints(totalSlots, campCommitteeSlots, registrationDeadline, schoolOpenTo, new ArrayList<>(), campDates);
        attendees = new ArrayList<>();
        committeeMembers = new ArrayList<>();
        campEnquiries = new HashMap<>();
        campSuggestions = new HashMap<>();
    }
    /** Constructs a new camp with existing data from the database.
     * @param campID 	    		The ID of the camp.
     * @param campName 	    		The name of the camp.
     * @param campDates     		The dates of the camp.
     * @param registrationDeadline 	The deadline for registrations.
     * @param schoolOpenTo  		The allowed schools from which attendees are allowed to register for the camp.
     * @param location 	    		The location of the camp.
     * @param totalSlots    		The total slots for the attendees and committees of the camp.
     * @param campCommitteeSlots 	The reserved slots for the camp committee.
     * @param description   		The description of the camp.
     * @param staffID 	    		The staff ID of the committee organizing the camp.
     * @param withdrawn 	    	List of withdrawn registrants.
     * @param attendees 	    	List of attendees.
     * @param committeeMembers 		List of committee members.
     * @param isCampVisible 		{@code true} if the camp is visible to student, otherwise {@code false}.
     * @param campEnquiries 		The HashMap of enquiryID and {@code Enquiry}.
     * @param campSuggestions 		The HashMap of suggestionID and {@code Suggestion}.
     */
    public Camp(int campID, String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo, String location, int totalSlots, int campCommitteeSlots, String description, String staffID, ArrayList<String> withdrawn, ArrayList<String> attendees, ArrayList<String> committeeMembers, boolean isCampVisible, HashMap<Integer,Enquiry> campEnquiries, HashMap<Integer,Suggestion> campSuggestions){
        super(campID, campName, campDates, registrationDeadline, schoolOpenTo, location, totalSlots, campCommitteeSlots, description, staffID, isCampVisible);
        campConstraints = new CampConstraints(totalSlots-attendees.size()-committeeMembers.size(), campCommitteeSlots-committeeMembers.size(), registrationDeadline, schoolOpenTo, withdrawn, campDates);
        this.attendees = new ArrayList<>(attendees);
        this.committeeMembers = new ArrayList<>(committeeMembers);
        this.campEnquiries = campEnquiries;
        this.campSuggestions = campSuggestions;
    }

    /**
     * Checks if a user is already registered for the camp.
     *
     * @param userID 			    The ID of user to check.
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
        if(attendees.contains(userID) || committeeMembers.contains(userID)){
            throw new CampException("User already registered!");
        }
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
        if(attendees.contains(userID) || committeeMembers.contains(userID)){
            throw new CampException("User already registered!");
        }
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
     * Adds the suggestion to the camp.
     *
     * @param suggestion 			The suggestion.
     */
    public void addCampSuggestion(Suggestion suggestion){
        campSuggestions.put(suggestion.getID(), suggestion);
    }

    /**
     * Deletes the suggestion from the camp.
     *
     * @param suggestionID 			The ID of suggestion to delete.
     *
     */
    public void deleteCampSuggestion(Integer suggestionID){
        campSuggestions.remove(suggestionID);
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

    /**
     * Retrieves the string of userID withdrawn from the camp.
     * Each userID is separated by ","
     * @return {@code String} of formatted withdrawn userIDs
     */
    public String getWithdrawnString(){
        return campConstraints.returnWithdrawnString();
    }

    /**
     * Hides the camp so that the student cannot see this camp in their all camp list.
     * @throws CampException            If the camp cannot be hidden (Ex. There is student already registered for this camp)
     */
    @Override
    public void hide() throws CampException{
        if(!attendees.isEmpty() || !committeeMembers.isEmpty()){
            throw new CampException("Students are already registered");
        }
        else{
            super.show();
        }
    }

    /**
     * Change the faculty that this camp opens to.
     * @param facultyOpenTo             The {@code String} of the new faculty to change to ("*" indicating the camp is open to all faculty)
     * @throws CampException            If the faculty cannot be modified (Ex. There is student from other faculty already registered for this camp)
     */
    @Override
    public void setFacultyOpenTo(String facultyOpenTo) throws CampException{
        if(facultyOpenTo.equals("*")) {
		super.setFacultyOpenTo(facultyOpenTo);
		return;
	}
        for(String attendee: attendees){
            User user = RegistryFactory.studentRegistry.getEntry(attendee);
            if(!user.getFaculty().equals(facultyOpenTo)){
                throw new CampException("There are students registered that are not from " + facultyOpenTo);
            }
        }
        for (String committeeMember: committeeMembers){
            User user = RegistryFactory.committeeRegistry.getEntry(committeeMember);
            if(!user.getFaculty().equals(facultyOpenTo)){
                throw new CampException("There are committee members that are not from " + facultyOpenTo);
            }
        }
        super.setFacultyOpenTo(facultyOpenTo);
    }

    /**
     * Deletes the camp, and Updates the deletion in the Registry.
     * @throws CampException                    If the camp cannot be deleted. Ex. there is student already registered for the camp.
     */
    public void deleteCamp() throws CampException{
        if (!attendees.isEmpty() || !committeeMembers.isEmpty()){
            throw new CampException("There are students registered for this camp");
        }
        for(Enquiry enquiry: campEnquiries.values()){
            Student student;
            if(RegistryFactory.studentRegistry.getEntry(enquiry.getSubmittedBy()) != null){
                student = RegistryFactory.studentRegistry.getEntry(enquiry.getSubmittedBy());
            }
            else{
                student = RegistryFactory.studentRegistry.getEntry(enquiry.getRepliedBy());
            }
            student.deleteEnquiry(enquiry.getID());
        }
        RegistryFactory.campRegistry.removeEntry(getCampID());
    }
}
