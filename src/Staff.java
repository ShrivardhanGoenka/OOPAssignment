import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code Staff} represents the staff that manages the camp management system.
 * The Staff class will hold the information of userID, name, faculty, all enquiries/suggestions/camp information.
 * The Staff can create a new camp, view all camps/enquiries/suggestions information, and process the suggestion.
 */
public class Staff extends User implements DatabaseWritable{
    /**
     * {@code HashMap<Integer, Camp> of the campID and Camp that this staff created.
     */
    private HashMap<Integer, Camp> createdCamps;
    /**
     * Constructs a Staff with empty camp.
     *
     * @param userID 		The id of the student.
     * @param email 		The email address of the student.
     * @param faculty 		The faculty the student is from.
     * @param isLocked 		true if the user is locked, otherwise false.
     */
    public Staff(String userID, String password, String email, String faculty, boolean isLocked){
        super(userID, password, email, faculty, isLocked);
        createdCamps = new HashMap<>();
    }
    /**
     * Constructs a Staff with exisiting created camp from the database.
     *
     * @param userID 		The id of the student.
     * @param email 		The email address of the student.
     * @param faculty 		The faculty the student is from.
     * @param isLocked 		true if the user is locked, otherwise false.
     * @param createdCamps 	The {@code HashMap} of campID and Camp object created by the staff.
     */
    public Staff(String userID, String password, String email, String faculty, boolean isLocked, HashMap<Integer,Camp> createdCamps){
        super(userID, password, email, faculty, isLocked);
        this.createdCamps = createdCamps;
    }

    /**
     * Prints the profile of the staff
     */
    @Override
    public void printProfile() {
        super.printProfile();
        System.out.println("Domain: Staff");
    }

    /**
     * Saves the current information of this staff back to the database (txt file).
     */
    @Override
    public String DBWriter(){
        String output = super.DBWriter();
        String campList = "";
        for(Camp camp: createdCamps.values()){
            campList += camp.getCampID() + ",";
        }
        if(!campList.isEmpty())
            campList = campList.substring(0, campList.length()-1);
        output += campList;
        return output;
    }

    /**
     * Gets the filename in the database to save this staff's information to.
     */
    public String getFileName(){
        return getUserID() + ".txt";
    }

    /**
     * Gets the {@code ArrayList} of camp created.
     * @return {@code ArrayList<Camp>}
     */
    public ArrayList<Camp> createdCamps(){
        return new ArrayList<>(Registry.campMap.values());
    }

    /**
     * Creates a new camp
     * @param campName 			The name of the camp.
     * @param description 		The description of the camp.
     * @param openToAll 		A boolean indicating whether the camp is open to all faculty.
     * @param isVisible 		A boolean indicating whether the camp can be shown to student.
     * @param campDates 		The {@code ArrayList} of {@code Date} that the camp will be held.
     * @param location 			The location of the camp.
     * @param registrationDeadline 	The {@code Date} of deadline for the camp registration.
     * @param totalSlots 		The maximum total number of slots for attendees and committees combined.
     * @param campCommitteeSlots 	The maximum number of slotes reserved for camp committees.
     */
    public void createCamp(String campName, String description, boolean openToAll, boolean isVisible, ArrayList<Date> campDates, String location, Date registrationDeadline, int totalSlots, int campCommitteeSlots){
        Camp newCamp = new Camp(Registry.nextCampID,campName, campDates, registrationDeadline, (openToAll?"*":getFaculty()), location,totalSlots, campCommitteeSlots, description, getUserID(), isVisible);
        Registry.addCamp(newCamp);
        createdCamps.put(newCamp.getCampID(), newCamp);
    }

    /**
     * Retrieves the list of suggestions submitted to the staff.
     * @return 			{@code ArrayList} of Suggestion.
     */
    public ArrayList<Suggestion> getSuggestions(){
        ArrayList<Suggestion> suggestions = new ArrayList<>();
        for(Map.Entry<Integer,Camp> camp : createdCamps.entrySet()){
            suggestions.addAll(camp.getValue().getCampSuggestions().values());
        }
        return suggestions;
    }

    /**
     * Retrieves the list of unprocesssed suggestions.
     * @return 			{@code ArrayList} of Suggestion that has not yet been processed.
     */
    public ArrayList<Suggestion> getUnprocessedSuggestions(){
        ArrayList<Suggestion> suggestions = getSuggestions();
        ArrayList<Suggestion> unprocessedSuggestions = new ArrayList<>();
        for(Suggestion suggestion: suggestions){
            if(suggestion.getApprovalStatus() == 0){
                unprocessedSuggestions.add(suggestion);
            }
        }
        return unprocessedSuggestions;
    }

    /**
     * Approves or Rejects the submitted suggestions, and Makes a reply message.
     * @param suggestionID 			The ID of suggestion to process.
     * @param approvalStatus 			A boolean indicating whether to accept or reject the suggestion.
     * @param reply 				The reply message to show to camp committee that raise the suggestion.
     */
    public void processSuggestion(int suggestionID, boolean approvalStatus, String reply){
        Suggestion suggestion = Registry.suggestionMap.get(suggestionID);
        suggestion.reply(reply, this.getUserID(), new Date());
        if(approvalStatus) {
            suggestion.approve();
            Registry.committeeMap.get(suggestion.getSubmittedBy()).suggestionAccepted();
        }
        else suggestion.reject();
    }
    public ArrayList<Enquiry> getEnquiries(){
        ArrayList<Enquiry> enquiries = new ArrayList<>();
        for(Map.Entry<Integer,Camp> camp : createdCamps.entrySet()){
            enquiries.addAll(camp.getValue().getCampEnquiries().values());
        }
        return enquiries;
    }
    public ArrayList<Enquiry> getUnprocessedEnquiries(){
        ArrayList<Enquiry> enquiries = getEnquiries();
        ArrayList<Enquiry> unprocessedEnquiries = new ArrayList<>();
        for(Enquiry enquiry: enquiries){
            if(!enquiry.isProcessed()){
                unprocessedEnquiries.add(enquiry);
            }
        }
        return unprocessedEnquiries;
    }
    public void processEnquiry(int enquiryID, String reply){
        Enquiry enquiry = Registry.enquiryMap.get(enquiryID);
        enquiry.reply(reply, this.getUserID(), new Date());
    }
}
