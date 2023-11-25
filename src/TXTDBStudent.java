import java.io.BufferedReader;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TXTDBStudent extends TXTDB<Student,String>{
    public TXTDBStudent() {
        super();
    }
    public TXTDBStudent(Student obj) {
        super(obj);
    }

    @Override
    public String getFileName() {
        return obj.getUserID() + ".txt";
    }

    @Override
    public String getWriteData() throws DBException {
        TXTDBUser user = new TXTDBUser(obj);
        String output = user.getWriteData();
        output += Parsers.datesToString(obj.getBlockedDates()) + "\n";
        ArrayList<Integer> ids = new ArrayList<>();
        for(Camp camp : obj.registeredCamps()){
            ids.add(camp.getCampID());
        }
        output += Parsers.integerListToString(ids) + "\n";
        ids.clear();
        for(Enquiry enquiry : obj.submittedEnquiries()){
            ids.add(enquiry.getID());
        }
        output += Parsers.integerListToString(ids) + "\n";
        ids.clear();
        return output;
    }

    @Override
    public Student getObjectFromData(String userID, String data) throws DBException {
        try {
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
            HashMap<Integer,Camp> registeredCampsMap = new HashMap<>();
            for(int i: registeredCamps){
                registeredCampsMap.put(i, RegistryFactory.campRegistry.getEntry(i));
            }
            HashMap<Integer,Enquiry> submittedEnquiriesMap = new HashMap<Integer,Enquiry>();
            for(int i: submittedEnquiries){
                submittedEnquiriesMap.put(i, RegistryFactory.enquiryRegistry.getEntry(i));
            }
            reader.close();
            return new Student(userID, password, email, faculty, isLocked, submittedEnquiriesMap, registeredCampsMap, blockedDatesList);
        } catch(NumberFormatException e){
            throw new DBException("Invalid number in file for enquiry " + userID);
        }
        catch(ParseException e){
            throw new DBException("Invalid Date in file for enquiry " + userID);
        }
        catch (Exception e){
            throw new DBException("Error in reading enquiry data for enquiry " + userID);
        }
    }
    @Override
    public String getID(String id){
        return id;
    }
}
