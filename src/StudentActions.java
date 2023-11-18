// javadoc
import java.util.ArrayList;

/**
 * This StudentActions class is an interface representing the actions that can be performed by a student.
 * The method required to be implemented includes registerForCamp, withdrawFromCamp, submitEnquiry, editEnquiry, and getEnquiries.
 *
 */
public interface StudentActions {
    /**
     * Registers a student to a camp.
     *
     * @param campID 		The Integer ID of the camp to register to.
     */
    void registerForCamp(int campID);
    /**
     * Withdraws a student from a camp.
     *
     * @param campID 		The Integer ID of the camp to withdraw from.
     */
    void withdrawFromCamp(int campID);
    /**
     * Submits an enquiry to a camp committee.
     *
     * @param enquiryString 	The String of message to sent to the camp committee.
     * @param campID 		The Integer ID of the camp to sent the enquiry to.
     */
    void submitEnquiry(String enquiryString, int campID);
    /**
     * Edits an enquiry that has been sent by this student.
     *
     * @param enquiryID 	The Integer ID of the enquiry to edit.
     * @param newEnquiry 	The String of new message to change to.
     */
    void editEnquiry(int enquiryID, String newEnquiry);
    /**
     * Retrieves all enquiries this student submitted.
     * @return The {@code ArrayList<Enquiry>} of enquiries.
     */
    ArrayList<Enquiry> getEnquiries();
}
