import java.util.Date;
/**
 * The {@code Suggestion} class represents the suggesiton sent between staff and camp committee.
 * The Suggestion will be specific to each camp. The student can submit the suggestion, view their suggestion, delete their suggestion.
 * The staff can view all suggestions submitted by camp committee that oversee the camp the staff has created, and staff can make a reply to the camp committee's suggestion by accepting or rejecting the suggestion, and submitting the reply message.
 */
public class Suggestion extends Enquiry  {
    /**
     * A number indicating whether the status of suggestion (pending, reject, accept)
     */
    private int approvalStatus;

    /**
     * Constructor for the Suggestion object (new empty Suggestion)
     * @param ID                The ID of the suggestion 
     * @param stringValue       The String message of the suggestion 
     * @param submittedBy       The userID of the user submitting the suggestion 
     * @param submittedOn       The Date of the suggestion submission
     * @param updatedOn         The Date of the last suggestion update
     * @param campID            The ID of the camp this suggestion is sent to
     */
    public Suggestion(int ID,String stringValue, String submittedBy, Date submittedOn, Date updatedOn, int campID){
        super(ID, stringValue, submittedBy, submittedOn, updatedOn, campID);
        approvalStatus = 0;
    }
    /**
     * Constructor for the Suggestion object (from database)
     * @param ID                The ID of the suggestion 
     * @param stringValue       The String message of the suggestion 
     * @param submittedBy       The userID of the user submitting the suggestion 
     * @param submittedOn       The Date of the suggestion submission
     * @param reply 		The reply message
     * @param repliedBy 	The camp committee's userID that made a reply
     * @param repliedOn 	The Date the reply was made
     * @param updatedOn         The Date of the last suggestion update
     * @param campID            The ID of the camp this suggestion is sent to
     * @param approvalStatus 	The status of the suggesiton
     */
    public Suggestion(int ID,String stringValue, String submittedBy, Date submittedOn, String reply, String repliedBy, Date repliedOn, Date updatedOn, int campID, int approvalStatus) {
        super(ID,stringValue, submittedBy, submittedOn, reply, repliedBy, repliedOn, updatedOn, campID);
        this.approvalStatus = approvalStatus;
    }

    /**
     * Retrieves the approval status of the suggestion
     * @return integer indicating the status of the suggestion
     */
    public int getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * Approves this suggestion
     */
    public void approve(){
        approvalStatus = 1;
    }

    /**
     * Rejects this suggestion
     */
    public void reject(){
        approvalStatus = 2;
    }
}
