import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The {@code TXTDBStaff} class extends the {@code TXTDB} class and is specifically designed for handling text-based
 * database operations for {@code Staff} objects.
 */
public class TXTDBStaff extends TXTDB<Staff,String> {
    /**
     * Constructs an empty {@code TXTDBStaff} object.
     */
    public TXTDBStaff() {
        super();
    }
    /**
     * Constructs a {@code TXTDBStaff} object with an exsiting {@code Staff} object from the database.
     *
     * @param obj                   The initial {@code Staff} object
     */
    public TXTDBStaff(Staff obj) {
        super(obj);
    }

    /**
     * Gets the file name associated with the the staff's userID
     *
     * @return {@code String} representing the file name
     */
    @Override
    public String getFileName() {
        return obj.getUserID() + ".txt";
    }

    /**
     * Gets the formatted data for writing to the database file by calling {@code TXTDBUser}.
     *
     * @return {@code String} representing the formatted data
     * @throws DBException              If an error occurs during the data formatting process
     */
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

    /**
     * Converts data from a text representation to {@code Staff} object.
     *
     * @param userID                    The userID 
     * @param data                      The text representation of the staff information 
     * @return {@code Staff} object representing the converted data
     * @throws DBException              If an error occurs during the data conversion process
     */
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
                createdCampsMap.put(i, RegistryFactory.campRegistry.getEntry(i));
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
    /**
     * Retrieves the formatted unique identifier of the user
     * @param id                The unique identifier of the user
     * @return {@code String}
     */
    @Override
    public String getID(String id){
        return id;
    }
}
