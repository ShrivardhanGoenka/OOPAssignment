// javadoc
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code Student} represents the attendees of the camp.
 */
public class Student extends User implements DatabaseWritable {
    /**
     * {@code HashMap<Integer, Enquiry> of the enquiryID and Enquiry.
     */
    private HashMap<Integer, Enquiry> enquiryMap;
    /**
     * {@code HashMap<Integer, Camp> of the campID and Camp.
     */
    private HashMap<Integer, Camp> registeredCamps;
    /**
     * {@code ArrayList<Date>} of the dates this student are not avaiable.
     */
    private ArrayList<Date> blockedDates;

    /**
     * Constructs a Student with empty enquiries, camps registered, and unavailable dates.
     *
     * @param userID 		The id of the student.
     * @param email 		The email address of the student.
     * @param faculty 		The faculty the student is from.
     * @param isLocked 		true if the user is locked, otherwise false.
     */
    public Student(String userID, String password, String email, String faculty, boolean isLocked){
        super(userID, password, email, faculty, isLocked);
        enquiryMap = new HashMap<>();
        registeredCamps = new HashMap<>();
        blockedDates = new ArrayList<>();
    }

    /**
     * Constructs a Student with enquiries, registered, and unavailable dates from the exisiting database.
     *
     * @param userID 		The id of the student.
     * @param email 		The email address of the student.
     * @param faculty 		The faculty the student is from.
     * @param isLocked 		true if the user is locked, otherwise false.
     * @param enquiryMap 	The {@code HashMap<Integer, Enquiry>} of enquiryID and enquiry this user created.
     * @param registeredCamps 	The {@code HashMap<Integer, Camp>} of campID and camp this user registered.
     * @param blockedDates 	The {@code ArrayList<Date>} of dates this user is not available.
     */
    public Student(String userID, String password, String email, String faculty, boolean isLocked, HashMap<Integer, Enquiry> enquiryMap, HashMap<Integer, Camp> registeredCamps, ArrayList<Date> blockedDates) {
        super(userID, password, email, faculty, isLocked);
        this.enquiryMap = enquiryMap;
        this.registeredCamps = registeredCamps;
        this.blockedDates = blockedDates;
    }

    /**
     * Prints the profile of the student
     */
    public void printProfile() {
        super.printProfile();
        System.out.println("Domain: Student");
    }

    /**
     * Registers the student to a Camp
     * @param camp 		The camp to register to.
     */
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

    /**
     * Retrieves the list of registered camps.
     *
     * @return 			{@code ArrayList<Camp>} of camps this student registered.
     */
    public ArrayList<Camp> registeredCamps(){
        ArrayList<Camp> camps = new ArrayList<>();
        for(Map.Entry<Integer, Camp> i: registeredCamps.entrySet() ) {
            camps.add(i.getValue());
        }
        return camps;
    }

    /**
     * Make an enquiry to the camp committees.
     *
     * @param enquiry 		The message of enquiry to create.
     * @param campID 		The ID of the camp to sent enquiry to.
     */
    public void raiseEnquiry(String enquiry, int campID){
        Enquiry newEnquiry = new Enquiry(Registry.nextEnquiryID, enquiry, this.getUserID(), new Date(), new Date(), campID);
        enquiryMap.put(newEnquiry.getID(), newEnquiry);
        Registry.nextEnquiryID++;
        Registry.enquiryMap.put(newEnquiry.getID(), newEnquiry);
        Registry.campMap.get(campID).addCampEnquiry(newEnquiry);
    }

    /**
     * Retrieves the list of submitted enquiries this student submit.
     * @return 			{@code ArrayList<Enquiry>} of enquiries this student created.
     */
    public ArrayList<Enquiry> submittedEnquiries(){
        ArrayList<Enquiry> enquiries = new ArrayList<>();
        for(Map.Entry<Integer, Enquiry> i: enquiryMap.entrySet() ) {
            enquiries.add(i.getValue());
        }
        return enquiries;
    }

    /**
     * Retrieves the list of unreplied enquiries this student created.
     * @return 			{@code ArrayList<Enquiry> of enquiries from this student that has not been replied.
     */
    public ArrayList<Enquiry> getUnprocessedEnquiries(){
        ArrayList<Enquiry> enquiries = new ArrayList<>();
        for(Map.Entry<Integer, Enquiry> i: enquiryMap.entrySet() ) {
            if(i.getValue().isProcessed()) continue;
            enquiries.add(i.getValue());
        }
        return enquiries;
    }

    /**
     * Deletes the enquiry that this student created.
     * @param enquiryID 	The Integer ID of the enquiry to delete.
     */
    public void deleteEnquiry(int enquiryID){
        int camp = enquiryMap.get(enquiryID).getCampID();
        Registry.campMap.get(camp).deleteCampEnquiry(enquiryID);
        //Enquiry e = enquiryMap.get(enquiryId);
        enquiryMap.remove(enquiryID);
    }

    /**
     * Withdraws this student from the camp.
     * @param camp 		The {@code Camp} to withdraw from.
     */
    public void withdrawFromCamp(Camp camp){
        camp.withdrawAttendee(this.getUserID());
        ArrayList<Date> campdates = camp.getCampDates();
        for (Date campdate : campdates) {
            int j;
            for (j = 0; j < this.blockedDates.size(); j++) {
                Date temp = this.blockedDates.get(j);
                if(campdate.getDate() == temp.getDate() && campdate.getYear() == temp.getYear() && campdate.getMonth() == temp.getMonth()) break;
            }
            this.blockedDates.remove(j);
        }
	    registeredCamps.remove(camp.getCampID());
    }

    /**
     * Returns the dates the student is not available.
     * @return {@code ArrayList<Date>}
     */
    public ArrayList<Date> getBlockedDates() {
        return blockedDates;
    }

    /**
     * Saves the current information of this student back to the database (txt file).
     */
    @Override
    public String DBWriter(){
        String output = super.DBWriter();
        String datesblocked = "";
        for (Date blockedDate : blockedDates) {
            datesblocked += DBInterface.returnDateVal(blockedDate) + ",";
        }
        if(!datesblocked.isEmpty())
            datesblocked = datesblocked.substring(0, datesblocked.length()-1);
        output += datesblocked + "\n";
        String campsregistered = "";
        for (Map.Entry<Integer, Camp> i: registeredCamps.entrySet() ) {
            campsregistered += i.getKey() + ",";
        }
        if(!campsregistered.isEmpty())
            campsregistered = campsregistered.substring(0, campsregistered.length()-1);
        output += campsregistered + "\n";
        String enquiries = "";
        for (Map.Entry<Integer, Enquiry> i: enquiryMap.entrySet() ) {
            enquiries += i.getKey() + ",";
        }
        if(!enquiries.isEmpty())
            enquiries = enquiries.substring(0, enquiries.length()-1);
        output += enquiries + "\n";
        return output;
    }

    /**
     * Gets the filename in the database to save this student's information to.
     */
    public String getFileName(){
        return this.getUserID() + ".txt";
    }
}
