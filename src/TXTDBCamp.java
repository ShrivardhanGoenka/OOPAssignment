import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.*;

public class TXTDBCamp extends TXTDB<Camp,String>{
    Camp camp;
    public TXTDBCamp(Camp camp) {
        this.camp = camp;
    }
    @Override
    public String getFileName() {
        return "camp" + camp.getCampID() + ".txt";
    }

    @Override
    public String getWriteData() throws DBException {
        try {
            String output = "";
            output += camp.getCampName() + "\n";
            output += Parsers.datesToString(camp.getCampDates()) + "\n";
            output += DBInterface.returnDateVal(camp.getRegistrationDeadline()) + "\n";
            output += camp.getFacultyOpenTo() + "\n";
            output += camp.getLocation() + "\n";
            output += camp.getTotalSlots() + "\n";
            output += camp.getCampCommitteeSlots() + "\n";
            output += camp.getDescription() + "\n";
            output += camp.getStaffID() + "\n";
            output += camp.isVisible() ? "visible\n" : "notvisible\n";
            output += Parsers.stringListToString(camp.getAttendees()) + "\n";
            output += Parsers.stringListToString(camp.getCommitteeMembers()) + "\n";
            output += Parsers.integerListToString(new ArrayList<>(camp.getCampEnquiries().keySet())) + "\n";
            output += Parsers.integerListToString(new ArrayList<>(camp.getCampSuggestions().keySet())) + "\n";
            output += camp.getWithdrawnString() + "\n";
            return output;
        }catch (Exception e){
            throw new DBException("Error in writing camp data");
        }
    }

    @Override
    public Camp getObjectFromData(String id,String data) throws DBException{
        Camp camp = null;
        BufferedReader reader = new BufferedReader(new StringReader(data));
        int campID;
        try {
            campID = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new DBException("Invalid camp ID in file");
        }

        try {
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
            ArrayList<Date> campDates;
            Date regDeadline;
            campDates = Parsers.parseDates(dates);
            regDeadline = Parsers.parseDate(registrationClosingDate);

            // Parse the attendees and committee members
            String list = reader.readLine();
            ArrayList<String> attendees = Parsers.parseStringList(list);

            list = reader.readLine();
            ArrayList<String> committee = Parsers.parseStringList(list);

            ArrayList<Integer> enquiries = Parsers.parseIntegerList(reader.readLine());
            HashMap<Integer, Enquiry> campEnquiries = new HashMap<>();
            for (int i : enquiries) {
                if (Registry.enquiryMap.get(i) != null) {
                    campEnquiries.put(i, Registry.enquiryMap.get(i));
                }
            }

            ArrayList<Integer> suggestions = Parsers.parseIntegerList(reader.readLine());
            HashMap<Integer, Suggestion> campSuggestions = new HashMap<>();
            for (int i : suggestions) {
                if (Registry.suggestionMap.get(i) != null) {
                    campSuggestions.put(i, Registry.suggestionMap.get(i));
                }
            }

            ArrayList<String> withdrawn = new ArrayList<>(List.of(reader.readLine().split(",")));

            // Create the CampInformation object
            camp = new Camp(campID, campName, campDates, regDeadline, userGroup, location, totalSlots, campCommitteeSlots, description, staffInCharge, withdrawn, attendees, committee, visibility, campEnquiries, campSuggestions);

            reader.close();
        } catch(ParseException e){
            throw new DBException("Invalid date format in file");
        }
        catch (NumberFormatException e) {
            throw new DBException("Invalid numbers in file");
        }
        catch (IOException e) {
            throw new DBException("Error in reading camp data");
        }

        return camp;
    }

}
