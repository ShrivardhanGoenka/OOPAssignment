import java.io.BufferedReader;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Date;

/**
 * The {@code TXTDBEnquiry} class extends the {@code TXTDB} class and is specifically designed for handling text-based
 * database operations for {@code Enquiry} objects.
 */
public class TXTDBEnquiry extends TXTDB<Enquiry,Integer>{
    /**
     * Constructs an empty {@code TXTDBEnquiry} object.
     */
    public TXTDBEnquiry() {
        super();
    }
    /**
     * Constructs a {@code TXTDBEnquiry} object with an exsiting {@code Enquiry} object from the database.
     *
     * @param enquiry 			The initial {@code Enquiry} object
     */
    public TXTDBEnquiry(Enquiry enquiry) {
        super(enquiry);
    }
    /**
     * Gets the file name associated with the the enquiryID
     *
     * @return {@code String} representing the file name
     */
    @Override
    public String getFileName() {
        return "enquiry" + obj.getID() + ".txt";
    }

    /**
     * Gets the formatted enquiry data for writing to the database file.
     *
     * @return {@code String} representing the formatted data
     * @throws DBException              If an error occurs during the data formatting process
     */
    @Override
    public String getWriteData() throws DBException {
        String output = "";
        output += obj.getStringValue() + "\n";
        output += Parsers.dateToString(obj.getSubmittedOn()) + "\n";
        output += Parsers.dateToString(obj.getUpdatedOn()) + "\n";
        output += obj.getSubmittedBy() + "\n";
        output += obj.getCampID() + "\n";
        if(obj.isProcessed()){
            output += "1\n";
            output += obj.getReply() + "\n";
            output += Parsers.dateToString(obj.getRepliedOn()) + "\n";
            output += obj.getRepliedBy() + "\n";
        }
        else{
            output += "0\n";
        }
        return output;
    }

    /**
     * Converts data from a text representation to {@code Enquiry} object.
     *
     * @param id                        The enquiryID 
     * @param data                      The string of the enquiry information 
     * @return {@code Enquiry} object representing the converted data
     * @throws DBException              If an error occurs during the data conversion process
     */
    @Override
    public Enquiry getObjectFromData(Integer id, String data) throws DBException {
        try{
            BufferedReader reader = new BufferedReader(new StringReader(data));

            String enquiryString = reader.readLine();
            Date submittedOn = Parsers.parseDate(reader.readLine());
            Date updatedOn = Parsers.parseDate(reader.readLine());
            String submittedBy = reader.readLine();
            int campID = Integer.parseInt(reader.readLine());
            int isProcessed = Integer.parseInt(reader.readLine());
            if(isProcessed == 0) {
                reader.close();
                return new Enquiry(id, enquiryString, submittedBy, submittedOn, updatedOn, campID);
            }
            String reply = reader.readLine();
            Date repliedOn = Parsers.parseDate(reader.readLine());
            String repliedBy = reader.readLine();
            reader.close();
            return new Enquiry(id, enquiryString, submittedBy, submittedOn, reply, repliedBy, repliedOn, updatedOn, campID);
        }
        catch(NumberFormatException e){
            throw new DBException("Invalid number in file for enquiry " + id);
        }
        catch(ParseException e){
            throw new DBException("Invalid Date in file for enquiry " + id);
        }
        catch (Exception e){
            throw new DBException("Error in reading enquiry data for enquiry " + id);
        }
    }

    /**
     * Retrieves the formatted unique identifier of the enquiry
     * @param id                The unique identifier to format
     * @return {@code String}
     */
    @Override
    public Integer getID(String id){
        return Integer.parseInt(id.substring(7));
    }
}
