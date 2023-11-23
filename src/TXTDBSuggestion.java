import java.io.BufferedReader;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Date;

public class TXTDBSuggestion extends TXTDB<Suggestion,Integer> {
    public TXTDBSuggestion(Suggestion suggestion) {
        super(suggestion);
    }

    @Override
    public String getFileName() {
        return "suggestion" + obj.getID() + ".txt";
    }

    @Override
    public String getWriteData() throws DBException {
        TXTDBEnquiry enquiryDB = new TXTDBEnquiry(obj);
        String output = enquiryDB.getWriteData();
        if(obj.isProcessed()){
            output += obj.getApprovalStatus() + "\n";
        }
        return output;
    }

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

}
