import java.util.Date;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class ConsoleReaderDate implements IConsoleReader<Date>{
    public Date readFromConsole() throws InputException {
        try{
            String input = br.readLine();
            if(input==null || input.isEmpty()){
                throw new InputException("Input cannot be empty");
            }
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(input);
            return date;
        }catch(IOException e){
            throw new InputException("Error reading input");
        } catch(ParseException e){
            throw new InputException("Invalid date format");
        }
    }
}
