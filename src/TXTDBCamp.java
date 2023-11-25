import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TXTDBCamp extends TXTDB<Camp,Integer>{
    public TXTDBCamp() {
        super();
    }
    public TXTDBCamp(Camp obj) {
        super(obj);
    }
    @Override
    public String getFileName() {
        return "camp" + obj.getCampID() + ".txt";
    }

    @Override
    public String getWriteData() throws DBException {
        try {
            String output = "";
            output += obj.getCampName() + "\n";
            output += Parsers.datesToString(obj.getCampDates()) + "\n";
            output += Parsers.dateToString(obj.getRegistrationDeadline()) + "\n";
            output += obj.getFacultyOpenTo() + "\n";
            output += obj.getLocation() + "\n";
            output += obj.getTotalSlots() + "\n";
            output += obj.getCampCommitteeSlots() + "\n";
            output += obj.getDescription() + "\n";
            output += obj.getStaffID() + "\n";
            output += obj.isVisible() ? "visible\n" : "notvisible\n";
            output += Parsers.stringListToString(obj.getAttendees()) + "\n";
            output += Parsers.stringListToString(obj.getCommitteeMembers()) + "\n";
            output += Parsers.integerListToString(new ArrayList<>(obj.getCampEnquiries().keySet())) + "\n";
            output += Parsers.integerListToString(new ArrayList<>(obj.getCampSuggestions().keySet())) + "\n";
            output += obj.getWithdrawnString() + "\n";
            return output;
        }catch (Exception e){
            throw new DBException("Error in writing obj data");
        }
    }

    @Override
    public Camp getObjectFromData(Integer id,String data) throws DBException{
        Camp obj = null;
        BufferedReader reader = new BufferedReader(new StringReader(data));

        try {
            String objName = reader.readLine();
            String dates = reader.readLine();
            String registrationClosingDate = reader.readLine();
            String userGroup = reader.readLine();
            String location = reader.readLine();
            int totalSlots = Integer.parseInt(reader.readLine());
            int objCommitteeSlots = Integer.parseInt(reader.readLine());
            String description = reader.readLine();
            String staffInCharge = reader.readLine();
            boolean visibility = (reader.readLine().equals("visible"));

            // Parse the dates and registration deadline
            ArrayList<Date> objDates;
            Date regDeadline;
            objDates = Parsers.parseDates(dates);
            regDeadline = Parsers.parseDate(registrationClosingDate);

            // Parse the attendees and committee members
            String list = reader.readLine();
            ArrayList<String> attendees = Parsers.parseStringList(list);

            list = reader.readLine();
            ArrayList<String> committee = Parsers.parseStringList(list);

            ArrayList<Integer> enquiries = Parsers.parseIntegerList(reader.readLine());
            HashMap<Integer, Enquiry> objEnquiries = new HashMap<>();
            for (int i : enquiries) {
                if (RegistryFactory.enquiryRegistry.getEntry(i) != null) {
                    objEnquiries.put(i, RegistryFactory.enquiryRegistry.getEntry(i));
                }
            }

            ArrayList<Integer> suggestions = Parsers.parseIntegerList(reader.readLine());
            HashMap<Integer, Suggestion> objSuggestions = new HashMap<>();
            for (int i : suggestions) {
                if (RegistryFactory.suggestionRegistry.getEntry(i) != null) {
                    objSuggestions.put(i, RegistryFactory.suggestionRegistry.getEntry(i));
                }
            }

            ArrayList<String> withdrawn = new ArrayList<>(List.of(reader.readLine().split(",")));

            // Create the CampInformation object
            obj = new Camp(id, objName, objDates, regDeadline, userGroup, location, totalSlots, objCommitteeSlots, description, staffInCharge, withdrawn, attendees, committee, visibility, objEnquiries, objSuggestions);

            reader.close();
        } catch(ParseException e){
            throw new DBException("Invalid date format in file");
        }
        catch (NumberFormatException e) {
            throw new DBException("Invalid numbers in file");
        }
        catch (IOException e) {
            throw new DBException("Error in reading obj data");
        }

        return obj;
    }
    @Override
    public Integer getID(String id){
        return Integer.parseInt(id.substring(4));
    }
}
