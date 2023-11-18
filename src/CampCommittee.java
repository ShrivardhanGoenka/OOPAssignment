// javadoc
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

<<<<<<< HEAD
public class CampCommittee extends Student implements DatabaseWritable {
=======
/**
* The {@CampCommittee} represents a student who is a committee member of the camp.
* It extends the {@code Student} class and includes extra functionalities specific to camp committee.
* This includes making, deleting, viewing and editing the suggestions to staff, and managing the attendees' enquiries.
*/
public class CampCommittee extends Student {
>>>>>>> f5288b8 (feat: add javadoc)

    /**
     * Represents Camp that this committee is associated.
     */
    Camp camp;

    /**
     * Represents suggestions submitted and their entry IDs.
     */
    HashMap<Integer, Suggestion> submittedSuggestions;

    Integer point;

    /**
     * Constructs a new {@code CampCommittee} with an empty submittedSuggestions.
     *
     * @param userID 			The ID of the committee member.
     * @param password 			The password of the committee member.
     * @param email 			The email address of the committee member.
     * @param faculty 			The faculty of the committee member.
     * @param isLocked 			A boolean indicating if the account is locked.
     * @param camp 			The camp associated with the committee member.
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
     * @param userID 			The ID of the committee member.
     * @param password 			The password of the committee member.
     * @param email 			The email address of the committee member.
     * @param faculty 			The faculty of the committee member.
     * @param isLocked 			A boolean indicating if the account is locked.
     * @param enquiryMap 		A map of enquiries of the committee member.
     * @param registeredCamps 		A map of registered camp of the committee member.
     * @param blockedDates 		A list of dates the committee member is not available.
     * @param camp 			The camp associated with the committee member.
     * @param submittedSuggestions 	A map of submitted suggestions from the committee member.
     */
    public CampCommittee(String userId, String password, String email, String faculty, boolean isLocked, HashMap<Integer, Enquiry> enquiryMap, HashMap<Integer, Camp> registeredCamps, ArrayList<Date> blockedDates, Camp camp, HashMap<Integer, Suggestion> submittedSuggestions, Integer point) {
        super(userId, password, email, faculty, isLocked, enquiryMap, registeredCamps, blockedDates);
        this.camp = camp;
	this.point = point;
        this.submittedSuggestions = submittedSuggestions;
    }

    /**
     * Prints the profile information of the committee member.
     */
    public void printProfile() {
        super.printProfile();
        System.out.println("Domain: Student");
        System.out.println("Camp committee member of camp: " + camp.getCampName());
    }

    /**
     * Returns ArrayList of suggestions submitted.
     *
     * @returns An ArrayList of suggestions.
     */
    public ArrayList<Suggestion> viewSuggestions(){
        return new ArrayList<>(submittedSuggestions.values());
    }

    /**
     * Returns the camp this committee oversee.
     *
     * @return The {@code Camp} this {@code CampCommittee} oversee.
     */
    public Camp getCamp() {
	return camp;
    }

    /**
     * Returns the enquiries to the camp this committee oversees. 
     *
     * @return {@code HashMap<Integer, Enquiry>} of the enquiryID and Enquiry.
     */
    public HashMap<Integer, Enquiry> getAttendeeEnquiryMap() {
	    return camp.getCampEnquiries();
    }

    /**
     * Returns the unreplied enquiries to the camp this committee oversees.
     *
     * @return {@code ArrayList<Enquiry>} of the unreplied enquiry.
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
     * Makes a reply to an Enquiry.
     *
     * @param enquiry 			The Enquiry to reply to.
     * @param message 			The String of the reply message.
     * @param userID 			The String of the committee's userID that make a reply.
     */
    public void replyToAttendeeEnquiry(Enquiry enquiry, String message, String userID) {
	    enquiry.reply(message, userID, new Date());
    }

    /**
     * Submits a suggestion to staff.
     *
     * @param suggestion 		The String of suggestion message.
     * @param campID 			The ID of the camp to submit to.
     */
    public void submitSuggestion(String suggestion, Integer campID) {
        Suggestion newSuggestion = new Suggestion(Registry.nextSuggestionID, suggestion, this.getUserID(), new Date(), new Date(), campID);
        submittedSuggestions.put(newSuggestion.getID(), newSuggestion);
        Registry.nextSuggestionID++;
        Registry.suggestionMap.put(newSuggestion.getID(), newSuggestion);
    }

    /**
     * Retrieves unprocessed suggestions.
     *
     * @return {@code ArrayList<Suggestion>} of suggestions.
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
     * Deletes the unprocessed suggestion this committee submited.
     *
     * @param suggestionID 		The ID of the suggestion to delete.
     */
    public void deleteSuggestion(int suggestionID) {
        int campID = submittedSuggestions.get(suggestionID).getCampID();
        Registry.campMap.get(campID).deleteCampSuggestion(suggestionID);
        //Enquiry e = enquiryMap.get(enquiryId);
        submittedSuggestions.remove(suggestionID);
    }

<<<<<<< HEAD
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

=======
    /**
     * Generates a report of the camp this committee oversee.
     *
     */
>>>>>>> f5288b8 (feat: add javadoc)
    public void generateReport() {
	System.out.println("To Generate report (not yet implemented)");
    }

}
