import java.util.Date;
/**
 * The {@code Enquiry} class represents the enquiry sent from student to camp committee or staff.
 * The Enquiry will be specific to each camp. The student can submit the enquiry, view their enquiry, delete their enquiry.
 * The camp committee can view all enquiries submitted by all students, and reply to the student's enquiry.
 * The Enquiry will also holds the date information of when it is submitted, updated, and replied.
 */
public class Enquiry  {
    /** The ID of the Enquiry (The ID will be unique) */
    private int ID;
    /** The string message of the enquiry */
    private String stringValue;
    /** A boolean indicating whether the Enquiry is already processed */
    private boolean isProcessed;
    /** The reply message to the enquiry */
    private String reply;
    /** The userID of the student that submit the enquiry */
    private String submittedBy;
    /** The userID of camp attendee that reply to the enquiry */
    private String repliedBy;
    /** The Date when the enquiry was submitted */
    private Date submittedOn;
    /** The Date when the enquiry was last updated */
    private Date updatedOn;
    /** The Date when the enquiry was replied */
    private Date repliedOn;
    /** The ID of the camp that this enquiry is associated */
    private int campID;

    /**
     * Constructor for the Enquiry object (new empty Enquiry)
     * @param ID                The ID of the enquiry
     * @param stringValue       The String message of the enquiry
     * @param submittedBy       The userID of the user submitting the enquiry
     * @param submittedOn       The Date of the enquiry submission
     * @param updatedOn         The Date of the last enquiry update
     * @param campID            The ID of the camp this enquiry is sent to
     */
    public Enquiry(int ID, String stringValue, String submittedBy, Date submittedOn, Date updatedOn, int campID){
        this.ID = ID;
        this.stringValue = stringValue;
        this.submittedBy = submittedBy;
        this.submittedOn = submittedOn;
        this.updatedOn = updatedOn;
        isProcessed = false;
        reply = "";
        repliedBy = "";
        repliedOn = null;
        this.campID = campID;
    }

    /**
     * Constructor for the Enquiry object (from the database)
     * @param ID                The ID of the enquiry
     * @param stringValue       The String message of the enquiry
     * @param submittedBy       The userID of the user submitting the enquiry
     * @param submittedOn       The Date of the enquiry submission
     * @param reply             The reply message to this enquiry
     * @param repliedBy         The userID of the camp committee replying to this enquiry.
     * @param repliedOn         The Date when the reply was made
     * @param updatedOn         The Date of the last enquiry update
     * @param campID            The ID of the camp this enquiry is sent to
     */
    public Enquiry(int ID,String stringValue, String submittedBy, Date submittedOn, String reply, String repliedBy, Date repliedOn, Date updatedOn, int campID){
        this.ID = ID;
        this.stringValue = stringValue;
        this.submittedBy = submittedBy;
        this.submittedOn = submittedOn;
        isProcessed = true;
        this.reply = reply;
        this.repliedBy = repliedBy;
        this.repliedOn = repliedOn;
        this.updatedOn = updatedOn;
        this.campID = campID;
    }

    /**
     * Edits the enquiry message.
     * The student will not be able to edit the enquiry that is already processed.
     * The {@code updateOn} attributes will be updated.
     * @param newString             The new enquiry message.
     */
    public void edit(String newString){
        if(isProcessed) return;
        this.stringValue = newString;
        updatedOn = new Date();
    }

    /**
     * Returns A boolean indicating whether the Enquiry has been processed.
     * @return {@code boolean}
     */
    public boolean isProcessed(){
        return isProcessed;
    }

    /**
     * Make a reply to this enquiry
     * @param reply                 The reply message
     * @param repliedBy             The userID of the camp committee that makes a reply
     * @param repliedOn             The Date when the reply is made
     */
    public void reply(String reply, String repliedBy, Date repliedOn){
        isProcessed = true;
        this.reply = reply;
        this.repliedBy = repliedBy;
        this.repliedOn = repliedOn;
    }

    /**
     * Returns the enquiry message
     * @return {@code String}
     */
    public String getStringValue() {
        return stringValue;
    }

    /**
     * Returns the reply message
     * @return {@code String}
     */
    public String getReply() {
        return reply;
    }

    /**
     * Returns the userID of student that submit the enquiry
     * @return {@code String}
     */
    public String getSubmittedBy() {
        return submittedBy;
    }

    /**
     * Returns the userID of camp committee that make a reply
     * @return {@code String}
     */
    public String getRepliedBy() {
        return repliedBy;
    }

    /**
     * Returns the Date when the enquiry was submitted
     * @return {@code Date}
     */
    public Date getSubmittedOn() {
        return submittedOn;
    }

    /**
     * Returns the Date when the enquiry was last updated
     * @return {@code Date}
     */
    public Date getUpdatedOn() {
        return updatedOn;
    }

    /**
     * Returns the Date when the enquiry was replied
     * @return {@code Date}
     */
    public Date getRepliedOn() {
        return repliedOn;
    }

    /**
     * Returns the ID of the enquiry
     * @return {@code int}
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns the ID of the camp this enquiry is associated
     * @return {@code int}
     */
    public int getCampID() {
        return campID;
    }
}
