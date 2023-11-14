import java.util.ArrayList;
import java.util.Date;

/**
 * {@code CampEditing} provides an abstract for all required editing functionality of the Camp.
 * This includes editing camp details, adding, removing and retrieving suggestions, adding, removing and retrieving enquiry.
 */
public interface CampEditing {

    /**
     * Edits the camp details.
     *
     * @param campName  			The name of the camp.
     * @param campDates 			The ArrayList of the camp dates scheduled.
     * @param registrationDeadline 		The registration deadline of the camp.
     * @param schoolOpenTo 			The faculty from which the users are allowed to register.
     * @param location 				The location of the camp.
     * @param totalSlots 			The total number of attendees slots.
     * @param campCommitteeSlots 		The number of slots reserved for committee.
     * @param description 			The description of the camp.
     */
    void editCampDetails(String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo,
                         String location, int totalSlots, int campCommitteeSlots, String description);

    /**
     * Adds the suggestion for the camp.
     *
     * @param suggestion 			The suggestion added.
     */
    void addSuggestion(Suggestion suggestion);

    /**
     * Retreives the suggestion of the camp.
     */
    Suggestion[] getSuggestions();

    /**
     * Removes the suggestion associated to the ID specified.
     *
     * @param suggestionID 			The ID of suggestion to remove.
     */
    void removeSuggestion(int suggestionID);


    /**
     * Adds the enquiry for the camp.
     *
     * @param enquiry 				The enquiry added.
     */
    void addEnquiry(Enquiry enquiry);

    /**
     * Retreives the enquiry of the camp.
     */
    Enquiry[] getEnquiries();

    /**
     * Removes the enquiry associated to the ID specified.
     *
     * @param enquiryID 			The ID of enquiry to remove.
     */
    void removeEnquiry(int enquiryID);
}
