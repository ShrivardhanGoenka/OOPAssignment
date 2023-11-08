import java.util.Date;

public class Enquiry {
    private int enquiryID;
    private String enquiryString;
    private boolean isProcessed;
    private String reply;
    private String submittedBy;
    private String repliedBy;
    private Date submittedOn;
    private Date updatedOn;
    private Date repliedOn;
    private int campID;

    public Enquiry(int enquiryID,String enquiryString, String submittedBy, Date submittedOn, int campID){
        this.enquiryID = enquiryID;
        this.enquiryString = enquiryString;
        this.submittedBy = submittedBy;
        this.submittedOn = submittedOn;
        this.updatedOn = submittedOn;
        isProcessed = false;
        reply = "";
        repliedBy = "";
        repliedOn = null;
        this.campID = campID;
    }

    public Enquiry(int enquiryID,String enquiryString, String submittedBy, Date submittedOn, String reply, String repliedBy, Date repliedOn, Date updatedOn, int campID){
        this.enquiryID = enquiryID;
        this.enquiryString = enquiryString;
        this.submittedBy = submittedBy;
        this.submittedOn = submittedOn;
        isProcessed = true;
        this.reply = reply;
        this.repliedBy = repliedBy;
        this.repliedOn = repliedOn;
        this.updatedOn = updatedOn;
        this.campID = campID;
    }

    void editEnquiry(String newEnquiry){
        if(isProcessed) return;
        this.enquiryString = newEnquiry;
        updatedOn = new Date();
    }

    boolean isProcessed(){
        return isProcessed();
    }

    void replyToEnquiry(String reply, String repliedBy, Date repliedOn){
        isProcessed = true;
        this.reply = reply;
        this.repliedBy = repliedBy;
        this.repliedOn = repliedOn;
    }

    public String getEnquiryString() {
        return enquiryString;
    }

    public String getReply() {
        return reply;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public String getRepliedBy() {
        return repliedBy;
    }

    public Date getSubmittedOn() {
        return submittedOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public Date getRepliedOn() {
        return repliedOn;
    }
}
