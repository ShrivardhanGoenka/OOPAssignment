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

//    public void withdrawCamp(int campID){
//        Camp camp = registeredCamps.get(campID);
//        camp.withdrawAttendee(this.getUserID());
//        registeredCamps.remove(campID);
//        ArrayList<Date> datelist = camp.getCampDates();
//        blockedDates.removeAll(datelist);
//    }

}
