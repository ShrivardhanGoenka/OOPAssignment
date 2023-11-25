import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TXTDBCampCommittee extends TXTDB<CampCommittee,String>{
    public TXTDBCampCommittee() {
        super();
    }
    public TXTDBCampCommittee(CampCommittee obj) {
        super(obj);
    }

    @Override
    public String getFileName() {
        return obj.getUserID() + ".txt";
    }

    @Override
    public String getWriteData() throws DBException {
        TXTDBStudent studentDB = new TXTDBStudent(obj);
        String output = studentDB.getWriteData();
        ArrayList<Integer> suggestionIDs = new ArrayList<>();
        output += obj.getCamp().getCampID() + "\n";
        for(Suggestion suggestion: obj.viewSuggestions()){
            suggestionIDs.add(suggestion.getID());
        }
        output += Parsers.integerListToString(suggestionIDs) + "\n";
        output += obj.getPoint();
        return output;
    }

    @Override
    public CampCommittee getObjectFromData(String userID, String data) throws DBException {
        try{
            BufferedReader reader = new BufferedReader(new StringReader(data));
            String password = reader.readLine();
            String email = reader.readLine();
            String faculty = reader.readLine();
            String temp = reader.readLine();
            boolean isLocked = temp.equals("locked");
            String blockedDates = reader.readLine();
            ArrayList<Date> blockedDatesList = Parsers.parseDates(blockedDates);
            ArrayList<Integer> registeredCamps = Parsers.parseIntegerList(reader.readLine());
            ArrayList<Integer> submittedEnquiries = Parsers.parseIntegerList(reader.readLine());
            HashMap<Integer,Camp> registeredCampsMap = new HashMap<Integer,Camp>();
            for(int i: registeredCamps){
                registeredCampsMap.put(i, RegistryFactory.campRegistry.getEntry(i));
            }
            HashMap<Integer,Enquiry> submittedEnquiriesMap = new HashMap<Integer,Enquiry>();
            for(int i: submittedEnquiries){
                submittedEnquiriesMap.put(i, RegistryFactory.enquiryRegistry.getEntry(i));
            }
            Integer campIDCommittee = Integer.parseInt(reader.readLine());
            Camp camp = RegistryFactory.campRegistry.getEntry(campIDCommittee);
            HashMap<Integer,Suggestion> submittedSuggestionsMap = new HashMap<Integer,Suggestion>();
            ArrayList<Integer> submittedSuggestions = Parsers.parseIntegerList(reader.readLine());
            for(int i: submittedSuggestions) {
                submittedSuggestionsMap.put(i, RegistryFactory.suggestionRegistry.getEntry(i));
            }
            Integer point = Integer.parseInt(reader.readLine());
            reader.close();
            return new CampCommittee(userID, password, email, faculty, isLocked, submittedEnquiriesMap, registeredCampsMap, blockedDatesList, camp, submittedSuggestionsMap, point);
        } catch(ParseException e){
            throw new DBException("Invalid date format in file for camp committee " + userID);
        }
        catch (NumberFormatException e) {
            throw new DBException("Invalid numbers in file for camp committee " + userID);
        }
        catch (IOException e) {
            throw new DBException("Error in reading obj data for camp committee " + userID);
        }
    }

    @Override
    public String getID(String id){
        return id;
    }
}
