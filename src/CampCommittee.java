import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CampCommittee extends Student {

    /**
     * The {@CampCommittee} represents a student who is a committee member of the camp.
     * It extends the {@link Student} class and includes extra functionality specific to camp committee.
     */

    /**
     * Represents Camp that this committee is associated.
     */
    Camp camp;

    /**
     * Represents suggestions submitted and their entry IDs.
     */
    HashMap<Integer, Suggestion> submittedSuggestions;

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
    public CampCommittee(String userId, String password, String email, String faculty, boolean isLocked, HashMap<Integer, Enquiry> enquiryMap, HashMap<Integer, Camp> registeredCamps, ArrayList<Date> blockedDates, Camp camp, HashMap<Integer, Suggestion> submittedSuggestions) {
        super(userId, password, email, faculty, isLocked, enquiryMap, registeredCamps, blockedDates);
        this.camp = camp;
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
     * Returns ArrayList of suggestions submitted by the committee member.
     *
     * @returns An ArrayList of suggestions.
     */
    public ArrayList<Suggestion> viewSuggestions(){
        return new ArrayList<>(submittedSuggestions.values());
    }

}
