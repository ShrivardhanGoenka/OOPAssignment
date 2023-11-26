import java.util.ArrayList;
import java.util.Date;

/** 
 * The {@code CampConstraints} provides the rules and constraints checking for camp registration.
 * The constraints include avaiable slot, registration deadline, and unavailable dates.
 */
public class CampConstraints {
    /**
     * Number of total slots for camp attendees and committees.
     */
    private int availableSlots;

    /**
     * Number of slots reserved for camp committees.
     */
    private int availableCommitteeSlots;

    /**
     * Deadline for camp registration.
     */
    private Date registrationDeadline;

    /**
     * The allowed school to attend the camp.
     * Use "*" for allowing all schools.
     */
    private String facultyOpenTo;

    /**
     * List of ID of user withdrawn from the camp.
     */
    private ArrayList<String> withdrawn;

    /**
     * List of dates the camp is scheduled.
     */
    private ArrayList<Date> campDates;

    /**
     * Constructor for CampConstraints object with specified constraints:
     *
     * @param availableSlots 			Number of total slots for camp attendees and committees.
     * @param availableCommitteeSlots 		Number of slots reserved for camp committees.
     * @param registrationDeadline 		Deadline for camp registration.
     * @param schoolOpenTo 			The school allowed to attend in the camp.
     * @param withdrawn 			List of ID of user withdrawn from the camp.
     * @param campDates 			List of Dates the camp is scheduled.
     */
    public CampConstraints(int availableSlots, int availableCommitteeSlots, Date registrationDeadline, String schoolOpenTo, ArrayList<String> withdrawn, ArrayList<Date> campDates) {
        this.availableSlots = availableSlots;
        this.availableCommitteeSlots = availableCommitteeSlots;
        this.registrationDeadline = registrationDeadline;
        this.facultyOpenTo = schoolOpenTo;
        this.withdrawn = withdrawn;
        this.campDates = campDates;
    }
    
    /**
     * Checks the eligibility of a specified attendee to register for the camp.
     *
     * @param userID 				The ID of the attendee.
     * @param blockedDates 			List of dates the attendee is not available.
     * @param faculty 				Faculty of the attendee.
     * @throws CampException 			if the registration condition fails.
     */
    public void checkAttendeeRegistration(String userID, ArrayList<Date> blockedDates, String faculty) throws CampException{
        if(availableSlots <= 0){
            throw new CampException("Camp is full!");
        }
        Date dateToday = new Date();
        if(dateToday.after(registrationDeadline)){
            throw new CampException("Registration deadline has passed!");
        }
        for(Date blockedDate : blockedDates){
            for(Date campDate : campDates){
                if(blockedDate.equals(campDate)){
                    throw new CampException("Date clash!");
                }
            }
        }
        if(!facultyOpenTo.equals("*") && !facultyOpenTo.equalsIgnoreCase(faculty)){
            throw new CampException("Camp is not open to your school!");
        }
        if(withdrawn.contains(userID)) {
            throw new CampException("User has already withdrawn from this camp!");
        }
    }

    /**
     * Checks the eligibility of a specified committee to register for the camp.
     *
     * @param userID 				The ID of the committee member.
     * @param blockedDates 			List of dates the committee member is not available.
     * @param faculty 				Faculty of the committee member.
     * @throws CampException 			if the registration condition fails.
     */
    public void checkCommitteeRegistration(String userID, ArrayList<Date> blockedDates, String faculty) throws CampException{
        checkAttendeeRegistration(userID, blockedDates, faculty);
        if(availableCommitteeSlots <= 0){
            throw new CampException("Camp committee is full!");
        }
    }

    /**
     * Updates the slots when an attendee is added.
     */
    public void addRegistration(){
        availableSlots--;
    }

    /**
     * Updates the slots when a member is added.
     */
    public void addCommitteeMember(){
        availableSlots--;
        availableCommitteeSlots--;
    }
    
    /**
     * Withdraws an attendee from the camp.
     *
     * @param userID 				The ID of the user to withdraw.
     */
    public void withdrawAttendee(String userID){
        availableSlots++;
        withdrawn.add(userID);
    }

    /**
     * Updates the total attendee slots.
     *
     * @param difference 		The difference in number of total slots requested to change.
     * @return true 			if the change is successful, otherwise false.
     */
    public boolean changeAttendeeSlots(int difference){
        if (difference > availableSlots) return false;
        availableSlots -= difference;
        return true;
    }

    /**
     * Updates the number of committee slots.
     *
     * @param difference 		The difference in number of committee slots requested to change.
     * @return true 			if the change is successful, otherwise false.
     */
    public boolean changeCampCommitteeSlots(int difference){
        if (difference > availableCommitteeSlots) return false;
        availableCommitteeSlots -= difference;
        return true;
    }

    /**
     * Returns to formatted string containing list of withdrawn attendees' userID
     * Each userID is separated by comma.
     *
     * @return {@code String}
     */
    public String returnWithdrawnString(){
        String output = "";
        for(String userID : withdrawn){
            output += userID + ",";
        }
        if(!output.isEmpty()) output = output.substring(0, output.length()-1);
        return output;
    }
}
