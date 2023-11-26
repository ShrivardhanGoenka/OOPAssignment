import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * The {@code CampCommittee} represents a student who is a committee member of the camp.
 * The camp committee will have all the functionality of {@code Student}.
 * The extra functionality of camp committee from {@code Student} are viewing/submitting/editing/deleting suggestion, replying to student's enquiries, and generating the camp report (the report will contains the information of who have registered for the camp).
 * It extends the {@link Student} class and includes extra functionality specific to camp committee.
 */

public class CampCommittee extends Student  {

    /**
     * Represents Camp that this committee is associated.
     */
    private Camp camp;

    /**
     * Represents suggestions submitted and their entry IDs.
     */
    private HashMap<Integer, Suggestion> submittedSuggestions;

    /**
     * Represents the point that this person currently have
     * For this camp management system, the point will increase when the user make a reply to an enquiry from camp attendee.
     */
    private Integer point;

    /**
     * Constructs a new {@code CampCommittee} with an empty submittedSuggestions.
     *
     * @param userID 			The ID of the committee member.
     * @param password 			The password of the committee member.
     * @param email 			The email address of the committee member.
     * @param faculty 			The faculty of the committee member.
     * @param isLocked 			A boolean indicating if the account is locked.
     * @param camp 			    The camp associated with the committee member.
     */
    public CampCommittee(String userID, String password, String email, String faculty, boolean isLocked, Camp camp) {
        super(userID, password, email, faculty, isLocked);
        this.camp = camp;
	this.point = 0;
        submittedSuggestions = new HashMap<>();
    }

    /**
     * Constructs a new {@code CampCommittee} with non-empty submittedSuggestions.
     *
     * @param userID 			    The ID of the committee member.
     * @param password 			    The password of the committee member.
     * @param email 			    The email address of the committee member.
     * @param faculty 			    The faculty of the committee member.
     * @param isLocked 			    A boolean indicating if the account is locked.
     * @param enquiryMap 		    A map of enquiries of the committee member.
     * @param registeredCamps 		A map of registered camp of the committee member.
     * @param blockedDates 	    	A list of dates the committee member is not available.
     * @param camp 			        The camp associated with the committee member.
     * @param submittedSuggestions 	A map of submitted suggestions from the committee member.
     * @param point 			    The committee point of the committee member.
     */
    public CampCommittee(String userID, String password, String email, String faculty, boolean isLocked, HashMap<Integer, Enquiry> enquiryMap, HashMap<Integer, Camp> registeredCamps, ArrayList<Date> blockedDates, Camp camp, HashMap<Integer, Suggestion> submittedSuggestions, Integer point) {
        super(userID, password, email, faculty, isLocked, enquiryMap, registeredCamps, blockedDates);
        this.camp = camp;
	    this.point = point;
        this.submittedSuggestions = submittedSuggestions;
    }

    /**
     * Prints the profile information of the committee member.
     */
    public void printProfile() {
        super.printProfile();
        System.out.println("Camp committee member of camp: " + camp.getCampName());
        System.out.println("Points: " + point);
    }

    /**
     * Returns ArrayList of suggestions submitted.
     *
     * @return {@code ArrayList<Suggestion>}
     */
    public ArrayList<Suggestion> viewSuggestions(){
        return new ArrayList<>(submittedSuggestions.values());
    }

    /**
     * Returns camp that the committee oversees.
     *
     * @return {@code Camp}
     */
    public Camp getCamp() {
        return camp;
    }

    /**
     * Returns enquiries about the camp from attendees.
     *
     * @return {@code HashMap<Integer, Enquiry>} of enquiryID and Enquiry
     */
    public HashMap<Integer, Enquiry> getAttendeeEnquiryMap() {
	    return camp.getCampEnquiries();
    }

    /**
     * Returns enquiries about the camp from attendees that has not yet been replied.
     *
     * @return {@code ArrayList<Enquiry>}
     */
    public ArrayList<Enquiry> getUnprocessedAttendeeEnquiry() {
        ArrayList<Enquiry> enquiryList = new ArrayList<>(camp.getCampEnquiries().values());
        int i = 0;
        while (i<enquiryList.size()) {
            if (enquiryList.get(i).isProcessed()) {
                enquiryList.remove(i--);
            }
            i++;
        }
        return enquiryList;
    }

    /**
     * Makes a reply to the input enquiry
     *
     * @param enquiry 			The enquiry to reply to.
     * @param message 			The enquiry message to sent.
     * @param userID 			The userID of this committee member that make a reply.
     *
     */
    public void replyToAttendeeEnquiry(Enquiry enquiry, String message, String userID) {
	    enquiry.reply(message, userID, new Date());
        point++;
    }

    /**
     * Submits a new suggestion about the camp to the staff.
     *
     * @param suggestion 		The suggestion message to sent.
     * @param campID 			The ID of the camp to display this suggestion.
     */
    public void submitSuggestion(String suggestion, Integer campID) {
        Suggestion newSuggestion = new Suggestion(PrimaryKeyCounter.nextSuggestionID++, suggestion, this.getUserID(), new Date(), new Date(), campID);
        submittedSuggestions.put(newSuggestion.getID(), newSuggestion);
        RegistryFactory.suggestionRegistry.addEntry(newSuggestion,newSuggestion.getID());
        camp.addCampSuggestion(newSuggestion);
        point++;
    }

    /**
     * Retrieves the suggestion that has not yet been processed by the staff.
     *
     * @return {@code ArrayList<Suggestion>}
     */
    public ArrayList<Suggestion> getUnprocessedSuggestions(){
        ArrayList<Suggestion> suggestions = new ArrayList<>();
        for(Map.Entry<Integer, Suggestion> i: submittedSuggestions.entrySet() ) {
            if(i.getValue().getApprovalStatus()!=0) continue;
            suggestions.add(i.getValue());
        }
        return suggestions;
    }

    /**
     * Deletes the unprocessed suggestion
     *
     * @param suggestionID 		The ID of suggestion to delete.
     */
    public void deleteSuggestion(int suggestionID) {
        int campID = submittedSuggestions.get(suggestionID).getCampID();
        RegistryFactory.campRegistry.getEntry(campID).deleteCampSuggestion(suggestionID);
        //Enquiry e = enquiryMap.get(enquiryId);
        submittedSuggestions.remove(suggestionID);
        RegistryFactory.suggestionRegistry.removeEntry(suggestionID);
        point--;
    }

    /**
     * Update the point of the user when the suggestion is accepted
     */
    public void suggestionAccepted(){
        point++;
    }

    /**
     * Formats the text message to write to the database
     * @return {@code String}
     */
    @Override
    public String DBWriter(){
        String output = super.DBWriter();
        output += camp.getCampID() + "\n";
        String suggestionString = "";
        for (Map.Entry<Integer, Suggestion> i : submittedSuggestions.entrySet()) {
            suggestionString += i.getKey() + ",";
        }
        if(!suggestionString.isEmpty())
            suggestionString = suggestionString.substring(0, suggestionString.length() - 1);
        output += suggestionString + "\n";
        output += point + "\n";
        return output;
    }

    /**
     * Retrieves the point that the camp committee have
     * @return {@code Integer} 
     */
    public Integer getPoint() {
        return point;
    }
}
