import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class Student extends User{
    private HashMap<Integer, Enquiry> enquiryMap;
    private HashMap<Integer, Camp> registeredCamps;
    private ArrayList<Date> blockedDates;
    public Student(String userID, String password, String email, String faculty, boolean isLocked){
        super(userID, password, email, faculty, isLocked);
        enquiryMap = new HashMap<>();
        registeredCamps = new HashMap<>();
        blockedDates = new ArrayList<>();
    }

    public Student(String userId, String password, String email, String faculty, boolean isLocked, HashMap<Integer, Enquiry> enquiryMap, HashMap<Integer, Camp> registeredCamps, ArrayList<Date> blockedDates) {
        super(userId, password, email, faculty, isLocked);
        this.enquiryMap = enquiryMap;
        this.registeredCamps = registeredCamps;
        this.blockedDates = blockedDates;
    }

    public void printProfile() {
        super.printProfile();
        System.out.println("Domain: Student");
    }

    public void registerCamp(Camp camp){
        try {
            camp.registerAttendee(this.getUserID(), blockedDates, this.getFaculty());
            registeredCamps.put(camp.getCampID(), camp);
            ArrayList<Date> datelist = camp.getCampDates();
            blockedDates.addAll(datelist);
        }
        catch (CampException e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Camp> registeredCamps(){
        ArrayList<Camp> camps = new ArrayList<>();
        for(Map.Entry<Integer, Camp> i: registeredCamps.entrySet() ) {
            camps.add(i.getValue());
        }
        return camps;
    }

    public void raiseEnquiry(String enquiry, int campID){
        Enquiry newEnquiry = new Enquiry(Registry.nextEnquiryID, enquiry, this.getUserID(), new Date(), new Date(), campID);
        enquiryMap.put(newEnquiry.getID(), newEnquiry);
        Registry.nextEnquiryID++;
        Registry.enquiryMap.put(newEnquiry.getID(), newEnquiry);
        Registry.campMap.get(campID).addCampEnquiry(newEnquiry);
    }

    public ArrayList<Enquiry> submittedEnquiries(){
        ArrayList<Enquiry> enquiries = new ArrayList<>();
        for(Map.Entry<Integer, Enquiry> i: enquiryMap.entrySet() ) {
            enquiries.add(i.getValue());
        }
        return enquiries;
    }

    public ArrayList<Enquiry> getUnprocessedEnquiries(){
        ArrayList<Enquiry> enquiries = new ArrayList<>();
        for(Map.Entry<Integer, Enquiry> i: enquiryMap.entrySet() ) {
            if(i.getValue().isProcessed()) continue;
            enquiries.add(i.getValue());
        }
        return enquiries;
    }
    public void deleteEnquiry(int enquiryId){
        int camp = enquiryMap.get(enquiryId).getCampID();
        Registry.campMap.get(camp).deleteCampEnquiry(enquiryId);
        //Enquiry e = enquiryMap.get(enquiryId);
        enquiryMap.remove(enquiryId);
    }

    public void withdrawFromCamp(Camp camp){
        camp.withdrawAttendee(this.getUserID());
        ArrayList<Date> campdates = camp.getCampDates();
        for (Date campdate : campdates) {
            int j;
            for (j = 0; j < this.blockedDates.size(); j++) {
                Date temp = this.blockedDates.get(j);
                if(campdate.getDate() == temp.getDate() && campdate.getYear() == temp.getYear() && campdate.getMonth() == temp.getMonth())break;
            }
            this.blockedDates.remove(j);
        }
    }


//    public void withdrawCamp(int campID){
//        Camp camp = registeredCamps.get(campID);
//        camp.withdrawAttendee(this.getUserID());
//        registeredCamps.remove(campID);
//        ArrayList<Date> datelist = camp.getCampDates();
//        blockedDates.removeAll(datelist);
//    }

}
