import java.util.Date;
public class Suggestion extends Enquiry  {
    int approvalStatus;
    public Suggestion(int ID,String stringValue, String submittedBy, Date submittedOn, Date updatedOn, int campID){
        super(ID, stringValue, submittedBy, submittedOn, updatedOn, campID);
        approvalStatus = 0;
    }
    public Suggestion(int ID,String stringValue, String submittedBy, Date submittedOn, String reply, String repliedBy, Date repliedOn, Date updatedOn, int campID, int approvalStatus) {
        super(ID,stringValue, submittedBy, submittedOn, reply, repliedBy, repliedOn, updatedOn, campID);
        this.approvalStatus = approvalStatus;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void approve(){
        approvalStatus = 1;
    }

    public void reject(){
        approvalStatus = 2;
    }
}
