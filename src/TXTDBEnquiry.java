import java.io.BufferedReader;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Date;

public class TXTDBEnquiry extends TXTDB<Enquiry,Integer>{
    public TXTDBEnquiry() {
        super();
    }
    public TXTDBEnquiry(Enquiry enquiry) {
        super(enquiry);
    }
    @Override
    public String getFileName() {
        return "enquiry" + obj.getID() + ".txt";
    }

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

    @Override
    public Enquiry getObjectFromData(Integer id, String data) throws DBException {
        try{
            BufferedReader reader = new BufferedReader(new StringReader(data));

            String enquiryString = reader.readLine();
            Date submittedOn = Parsers.parseDate(reader.readLine());
            Date updatedOn = Parsers.parseDate(reader.readLine());
            String submittedBy = reader.readLine();
            int campId = Integer.parseInt(reader.readLine());
            int isProcessed = Integer.parseInt(reader.readLine());
            if(isProcessed == 0) {
                reader.close();
                return new Enquiry(id, enquiryString, submittedBy, submittedOn, updatedOn, campId);
            }
            String reply = reader.readLine();
            Date repliedOn = Parsers.parseDate(reader.readLine());
            String repliedBy = reader.readLine();
            reader.close();
            return new Enquiry(id, enquiryString, submittedBy, submittedOn, reply, repliedBy, repliedOn, updatedOn, campId);
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
    @Override
    public Integer getID(String id){
        return Integer.parseInt(id.substring(7));
    }
}
