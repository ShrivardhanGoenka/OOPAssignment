import java.io.BufferedReader;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class TXTDBStaff extends TXTDB<Staff,String> {

    public TXTDBStaff(Staff obj) {
        super(obj);
    }

    @Override
    public String getFileName() {
        return obj.getUserID() + ".txt";
    }

    @Override
    public String getWriteData() throws DBException {
        TXTDBUser userDB = new TXTDBUser(obj);
        String output = userDB.getWriteData();
        ArrayList<Integer> campIDs = new ArrayList<>();
        for(Camp camp: obj.createdCamps()){
            campIDs.add(camp.getCampID());
        }
        output += Parsers.integerListToString(campIDs) + "\n";
        return output;
    }

    @Override
    public Staff getObjectFromData(String userID, String data) throws DBException {
        try{
            BufferedReader reader = new BufferedReader(new StringReader(data));
            String password = reader.readLine();
            String email = reader.readLine();
            String faculty = reader.readLine();
            boolean isLocked = reader.readLine().equals("locked");
            ArrayList<Integer> createdCamps = Parsers.parseIntegerList(reader.readLine());
            HashMap<Integer,Camp> createdCampsMap = new HashMap();
            for(int i: createdCamps){
                createdCampsMap.put(i, Registry.campMap.get(i));
            }
            reader.close();
            return new Staff(userID, password, email, faculty, isLocked, createdCampsMap);

        }catch(NumberFormatException e){
            throw new DBException("Invalid number in file for enquiry " + userID);
        }
        catch (Exception e){
            throw new DBException("Error in reading enquiry data for enquiry " + userID);
        }
    }
}
