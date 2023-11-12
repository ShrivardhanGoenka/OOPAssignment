import java.util.Date;

public class Enquiry {
    private int ID;
    private String stringValue;
    private boolean isProcessed;
    private String reply;
    private String submittedBy;
    private String repliedBy;
    private Date submittedOn;
    private Date updatedOn;
    private Date repliedOn;
    private int campID;

    public Enquiry(int ID,String stringValue, String submittedBy, Date submittedOn, Date updatedOn, int campID){
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

    void edit(String newString){
        if(isProcessed) return;
        this.stringValue = newString;
        updatedOn = new Date();
    }

    boolean isProcessed(){
        return isProcessed;
    }

    void reply(String reply, String repliedBy, Date repliedOn){
        isProcessed = true;
        this.reply = reply;
        this.repliedBy = repliedBy;
        this.repliedOn = repliedOn;
    }

    public String getStringValue() {
        return stringValue;
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

    public int getID() {
        return ID;
    }

    public int getCampID() {
        return campID;
    }
}
