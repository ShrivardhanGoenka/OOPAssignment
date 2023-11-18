import javax.swing.plaf.BorderUIResource;
import java.io.*;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.util.*;

public class DBInterface {
    static String returnDateVal(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    private <T extends DatabaseWritable, K> void writeObjects(String folder, Map<K, T> objectMap) {
        removeAllFiles(folder);
        for(Map.Entry<K, T> entry: objectMap.entrySet()){
            try(PrintWriter writer = new PrintWriter("LOGS/" + folder + "/" + entry.getValue().getFileName())){
                writer.write(entry.getValue().DBWriter());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    void writeNextValues(){
        try(PrintWriter writer = new PrintWriter("LOGS/nextValues.txt")){
            writer.write(Registry.nextCampID + "\n");
            writer.write(Registry.nextEnquiryID + "\n");
            writer.write(Registry.nextSuggestionID + "\n");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    void writeToDB(){
        writeObjects("CAMPS", Registry.campMap);
        writeObjects("ENQUIRIES", Registry.enquiryMap);
        writeObjects("SUGGESTIONS", Registry.suggestionMap);
        writeObjects("STUDENT", Registry.studentMap);
        writeObjects("COMMITTEE", Registry.committeeMap);
        writeObjects("STAFF", Registry.staffMap);
        writeNextValues();
    }

    void removeAllFiles(String folder){
        folder = "LOGS/" + folder + "/";
        File directory = new File(folder);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if(files == null) return;
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    file.delete();
                }
            }
        }
    }

    Suggestion readSuggestion(int suggestionID){
        //Suggestion suggestion = null;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("LOGS/SUGGESTIONS/suggestion" + suggestionID + ".txt"));
            String suggestionString = reader.readLine();
            Date submittedOn = parseDate(reader.readLine());
            Date updatedOn = parseDate(reader.readLine());
            String submittedBy = reader.readLine();
            int campID = Integer.parseInt(reader.readLine());
            int isProcessed = Integer.parseInt(reader.readLine());
            if(isProcessed == 0) {
                reader.close();
                return new Suggestion(suggestionID, suggestionString, submittedBy, submittedOn, updatedOn, campID);
            }
            String reply = reader.readLine();
            Date repliedOn = parseDate(reader.readLine());
            String repliedBy = reader.readLine();
            int isApproved = Integer.parseInt(reader.readLine());
            return new Suggestion(suggestionID, suggestionString, submittedBy, submittedOn, reply, repliedBy, repliedOn, updatedOn, campID, isApproved);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    ArrayList<Integer> parseIntegerList(String l){
        if(l == null || l.equals("")){
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        String[] arr = l.split(",");
        for(String s : arr){
            if(s == null || s.isEmpty()) continue;
            list.add(Integer.parseInt(s.trim()));
        }
        return list;
    }
    ArrayList<String> readDirectoryList(String dirname) throws IOException{
        String directoryPath = "LOGS/" + dirname + "/";
        ArrayList<String> dir = new ArrayList<String>();
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if(files == null) return new ArrayList<String>();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    dir.add(file.getName().substring(0, file.getName().length() - 4));
                }
            }
        } else {
            throw new IOException("Directory not found");
        }
        return dir;
    }

    Student addStudent(String userID){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("LOGS/STUDENT/" + userID + ".txt"));
            String password = reader.readLine();
            String email = reader.readLine();
            String faculty = reader.readLine();
            String temp = reader.readLine();
            boolean isLocked = temp.equals("locked");
            String blockedDates = reader.readLine();
            ArrayList<Date> blockedDatesList = parseDates(blockedDates);
            ArrayList<Integer> registeredCamps = parseIntegerList(reader.readLine());
            ArrayList<Integer> submittedEnquiries = parseIntegerList(reader.readLine());
            HashMap<Integer,Camp> registeredCampsMap = new HashMap<Integer,Camp>();
            for(int i: registeredCamps){
                registeredCampsMap.put(i, Registry.campMap.get(i));
            }
            HashMap<Integer,Enquiry> submittedEnquiriesMap = new HashMap<Integer,Enquiry>();
            for(int i: submittedEnquiries){
                submittedEnquiriesMap.put(i, Registry.enquiryMap.get(i));
            }
            reader.close();
            return new Student(userID, password, email, faculty, isLocked, submittedEnquiriesMap, registeredCampsMap, blockedDatesList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    void populateStudents(){
        ArrayList<String> students = new ArrayList<String>();
        try{
            students = readDirectoryList("STUDENT");
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        for(String student : students){
            Registry.studentMap.put(student, addStudent(student));
        }
    }

    CampCommittee addCommittee(String userID){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("LOGS/COMMITTEE/" + userID + ".txt"));
            String password = reader.readLine();
            String email = reader.readLine();
            String faculty = reader.readLine();
            String temp = reader.readLine();
            boolean isLocked = temp.equals("locked");
            String blockedDates = reader.readLine();
            ArrayList<Date> blockedDatesList = parseDates(blockedDates);
            ArrayList<Integer> registeredCamps = parseIntegerList(reader.readLine());
            ArrayList<Integer> submittedEnquiries = parseIntegerList(reader.readLine());
            HashMap<Integer,Camp> registeredCampsMap = new HashMap<Integer,Camp>();
            for(int i: registeredCamps){
                registeredCampsMap.put(i, Registry.campMap.get(i));
            }
            HashMap<Integer,Enquiry> submittedEnquiriesMap = new HashMap<Integer,Enquiry>();
            for(int i: submittedEnquiries){
                submittedEnquiriesMap.put(i, Registry.enquiryMap.get(i));
            }
	    Integer campIDCommittee = Integer.parseInt(reader.readLine());
	    Camp camp = Registry.campMap.get(campIDCommittee);
            HashMap<Integer,Suggestion> submittedSuggestionsMap = new HashMap<Integer,Suggestion>();
	    ArrayList<Integer> submittedSuggestions = parseIntegerList(reader.readLine());
	    for(int i: submittedSuggestions) {
		submittedSuggestionsMap.put(i, Registry.suggestionMap.get(i));
	    }
	    Integer point = Integer.parseInt(reader.readLine());
            reader.close();
            return new CampCommittee(userID, password, email, faculty, isLocked, submittedEnquiriesMap, registeredCampsMap, blockedDatesList, camp, submittedSuggestionsMap, point);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    void populateCampCommittees(){
	ArrayList<String> campCommittees = new ArrayList<String>();
	try{
		campCommittees = readDirectoryList("COMMITTEE");
	} catch(IOException e) {
		System.out.println(e.getMessage());
		System.exit(1);
	}

	for(String campCommittee : campCommittees) {
		Registry.committeeMap.put(campCommittee, addCommittee(campCommittee));
	}
    }

    Camp readCampDetails(int campID){
        Camp camp = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("LOGS/CAMPS/camp" + campID + ".txt"));

            // Read camp details line by line
            String campName = reader.readLine();
            String dates = reader.readLine();
            String registrationClosingDate = reader.readLine();
            String userGroup = reader.readLine();
            String location = reader.readLine();
            int totalSlots = Integer.parseInt(reader.readLine());
            int campCommitteeSlots = Integer.parseInt(reader.readLine());
            String description = reader.readLine();
            String staffInCharge = reader.readLine();
            boolean visibility = (reader.readLine().equals("visible"));
            // Parse the dates and registration deadline
            ArrayList<Date> campDates = parseDates(dates);
            Date regDeadline = parseDate(registrationClosingDate);
            // Parse the attendees and committee members
            String list = reader.readLine();
            ArrayList<String> attendees;
            if (list == null || list.isEmpty()) attendees = new ArrayList<>();
            else attendees = new ArrayList<>(List.of(list.split(",")));
            list = reader.readLine();
            ArrayList<String> committee;
            if (list == null || list.isEmpty()) committee = new ArrayList<>();
            else committee = new ArrayList<>(List.of(list.split(",")));
            ArrayList<Integer> enquiries = parseIntegerList(reader.readLine());
            HashMap<Integer, Enquiry> campenquiries = new HashMap<>();
            for(int i: enquiries){
                if(Registry.enquiryMap.get(i) != null ) campenquiries.put(i, Registry.enquiryMap.get(i));
            }
            ArrayList<Integer> suggestions = parseIntegerList(reader.readLine());
            HashMap<Integer,Suggestion> campSuggestions = new HashMap<>();
            for(int i: suggestions){
                if(Registry.suggestionMap.get(i) != null ) campSuggestions.put(i, Registry.suggestionMap.get(i));
            }
            ArrayList<String> withdrawn = new ArrayList<String>(List.of(reader.readLine().split(",")));
            // Create the CampInformation object
            camp = new Camp(campID, campName, campDates, regDeadline, userGroup, location, totalSlots, campCommitteeSlots, description, staffInCharge, withdrawn, attendees, committee, visibility, campenquiries, campSuggestions );

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return camp;
    }

    Enquiry readEnquiry(int enquiryID){
        Enquiry enquiry = null;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("LOGS/ENQUIRIES/enquiry" + enquiryID + ".txt"));

            String enquiryString = reader.readLine();
            Date submittedOn = parseDate(reader.readLine());
            Date updatedOn = parseDate(reader.readLine());
            String submittedBy = reader.readLine();
            int campId = Integer.parseInt(reader.readLine());
            int isProcessed = Integer.parseInt(reader.readLine());
            if(isProcessed == 0) {
                reader.close();
                enquiry = new Enquiry(enquiryID, enquiryString, submittedBy, submittedOn, updatedOn, campId);
                return enquiry;
            }
            String reply = reader.readLine();
            Date repliedOn = parseDate(reader.readLine());
            String repliedBy = reader.readLine();
            enquiry = new Enquiry(enquiryID, enquiryString, submittedBy, submittedOn, reply, repliedBy, repliedOn, updatedOn, campId);
            reader.close();
            return enquiry;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return enquiry;
    }

    // Helper method to parse dates in the format: dd/MM/yyyy,dd/MM/yyyy
    private static ArrayList<Date> parseDates(String dates) {
        if(dates == null || dates.equals("")) return new ArrayList<Date>();
        ArrayList<Date> dateList = new ArrayList<>();
        try {
            String[] dateArr = dates.split(",");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            for (String dateStr : dateArr) {
                Date date = dateFormat.parse(dateStr.trim());
                dateList.add(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateList;
    }

    // Helper method to parse a date in the format: dd/MM/yyyy
    private static Date parseDate(String dateStr) {
        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = dateFormat.parse(dateStr.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }

    void populateCamps(){
        ArrayList<String> camps = new ArrayList<String>();
        try{
            camps = readDirectoryList("CAMPS");
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        for(String camp : camps){
            Registry.campMap.put(Integer.parseInt(camp.substring(4)), readCampDetails(Integer.parseInt(camp.substring(4))));
            //Registry.campList.add(readCampDetails(Integer.parseInt(camp.substring(4))));
        }
    }

    void populateSuggestions(){
        ArrayList<String> suggestions = new ArrayList<String>();
        try{
            suggestions = readDirectoryList("SUGGESTIONS");
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        for(String suggestion : suggestions){
            //System.out.println(suggestion );
            Registry.suggestionMap.put(Integer.parseInt(suggestion.substring(10)), readSuggestion(Integer.parseInt(suggestion.substring(10))));
        }
    }

    void populateEnquiries(){
        ArrayList<String> enquiries = new ArrayList<String>();
        try{
            enquiries = readDirectoryList("ENQUIRIES");
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        for(String enquiry : enquiries){
            //System.out.println(enquiry );
            Registry.enquiryMap.put(Integer.parseInt(enquiry.substring(7)), readEnquiry(Integer.parseInt(enquiry.substring(7))));
        }
    }

    void loadNextValues() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("LOGS/nextValues.txt"));
            Registry.nextCampID = Integer.parseInt(reader.readLine());
            Registry.nextEnquiryID = Integer.parseInt(reader.readLine());
            Registry.nextSuggestionID = Integer.parseInt(reader.readLine());
            reader.close();
        }
        catch(Exception e){
            System.out.println("Error in loading next values");
            System.exit(1);
        }
    }
    Staff readStaff(String userID){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("LOGS/STAFF/" + userID + ".txt"));
            String password = reader.readLine();
            String email = reader.readLine();
            String faculty = reader.readLine();
            String temp = reader.readLine();
            boolean isLocked = temp.equals("locked");
            ArrayList<Integer> createdCamps = parseIntegerList(reader.readLine());
            HashMap<Integer,Camp> createdCampsMap = new HashMap<Integer,Camp>();
            for(int i: createdCamps){
                createdCampsMap.put(i, Registry.campMap.get(i));
            }
            reader.close();
            return new Staff(userID, password, email, faculty, isLocked, createdCampsMap);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    void populateStaff(){
        ArrayList<String> staff = new ArrayList<String>();
        try{
            staff = readDirectoryList("STAFF");
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        for(String s : staff){
            Registry.staffMap.put(s, readStaff(s));
        }
    }
}
