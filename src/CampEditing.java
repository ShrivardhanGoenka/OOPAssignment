import java.util.ArrayList;
import java.util.Date;

public interface CampEditing {
    void editCampDetails(String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo,
                         String location, int totalSlots, int campCommitteeSlots, String description);
    void addSuggestion(Suggestion suggestion);
    Suggestion[] getSuggestions();
    void removeSuggestion(int suggestionID);
    void addEnquiry(Enquiry enquiry);
    Enquiry[] getEnquiries();
    void removeEnquiry(int enquiryID);
}
