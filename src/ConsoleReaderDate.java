import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * The {@code ConsoleReaderDate} provides the method for reading and parsing the date from the input in the console.
 * The {@code ConsoleReaderDate} realizes {@code IConsoleReader} interface with type Date
 */
public class ConsoleReaderDate implements IConsoleReader<Date>{
    /**
     * Reads date String from the console input and converts the date to Date object.
     *
     * @return The Date object read from the console.
     * @throws InputException If there is an error parsing the input or the input is not in the expected date format or the input is empty.
     */
    public Date readFromConsole() throws InputException {
        try{
            String input = br.readLine();
            if(input==null || input.isEmpty()){
                throw new InputException("Input cannot be empty");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            return sdf.parse(input);
        }catch(IOException e){
            throw new InputException("Error reading input");
        } catch(ParseException e){
            throw new InputException("Invalid date format");
        }
    }
}
