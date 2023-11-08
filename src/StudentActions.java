import java.util.ArrayList;
public interface StudentActions {
    void registerForCamp(int campID);
    void withdrawFromCamp(int campID);
    void submitEnquiry(String enquiryString, int campID);
    void editEnquiry(int enquiryID, String newEnquiry);
    ArrayList<Enquiry> getEnquiries();
}
