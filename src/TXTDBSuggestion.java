import java.io.BufferedReader;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Date;

/**
 * The {@code TXTDBSuggestion} class extends the {@code TXTDB} class and is specifically designed for handling text-based
 * database operations for {@code Suggestion} objects.
 */
public class TXTDBSuggestion extends TXTDB<Suggestion,Integer> {
    /**
     * Constructs an empty {@code TXTDBSuggestion} object.
     */
    public TXTDBSuggestion() {
        super();
    }
    /**
     * Constructs a {@code TXTDBSuggestion} object with an exsiting {@code Suggestion} object from the database.
     *
     * @param suggestion 			The initial {@code Suggestion} object
     */
    public TXTDBSuggestion(Suggestion suggestion) {
        super(suggestion);
    }

    /**
     * Gets the file name associated with the the suggestionID
     *
     * @return {@code String} representing the file name
     */
    @Override
    public String getFileName() {
        return "suggestion" + obj.getID() + ".txt";
    }

    /**
     * Gets the formatted suggestion data for writing to the database file.
     *
     * @return {@code String} representing the formatted data
     * @throws DBException              If an error occurs during the data formatting process
     */
    @Override
    public String getWriteData() throws DBException {
        TXTDBEnquiry suggestionDB = new TXTDBEnquiry(obj);
        String output = suggestionDB.getWriteData();
        if(obj.isProcessed()){
            output += obj.getApprovalStatus() + "\n";
        }
        return output;
    }

    /**
     * Converts data from a text representation to {@code Suggestion} object.
     *
     * @param id                        The suggestionID 
     * @param data                      The string of the suggestion information 
     * @return {@code Suggestion} object representing the converted data
     * @throws DBException              If an error occurs during the data conversion process
     */
    @Override
    public Suggestion getObjectFromData(Integer id, String data) throws DBException {
        try{
            BufferedReader reader = new BufferedReader(new StringReader(data));

            String suggestionString = reader.readLine();
            Date submittedOn = Parsers.parseDate(reader.readLine());
            Date updatedOn = Parsers.parseDate(reader.readLine());
            String submittedBy = reader.readLine();
            int campId = Integer.parseInt(reader.readLine());
            int isProcessed = Integer.parseInt(reader.readLine());
            if(isProcessed == 0) {
                reader.close();
                return new Suggestion(id, suggestionString, submittedBy, submittedOn, updatedOn, campId);
            }
            String reply = reader.readLine();
            Date repliedOn = Parsers.parseDate(reader.readLine());
            String repliedBy = reader.readLine();
            int approvalStatus = Integer.parseInt(reader.readLine());
            reader.close();
            return new Suggestion(id, suggestionString, submittedBy, submittedOn, reply, repliedBy, repliedOn, updatedOn, campId, approvalStatus);
        }
        catch(NumberFormatException e){
            throw new DBException("Invalid number in file for suggestion " + id);
        }
        catch(ParseException e){
            throw new DBException("Invalid Date in file for suggestion " + id);
        }
        catch (Exception e){
            throw new DBException("Error in reading suggestion data for suggestion " + id);
        }
    }
    /**
     * Retrieves the formatted unique identifier of the suggestion
     * @param id                The unique identifier to format
     * @return {@code String}
     */
    @Override
    public Integer getID(String id){
        return Integer.parseInt(id.substring(10));
    }
}
