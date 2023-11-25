import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * The {@code ConsoleReaderDateList} provides user input reader for {@code Date} from console.
 * It realizes {@link IConsoleReader} class and implements readFromConsole() function.
 */
public class ConsoleReaderDateList implements IConsoleReader<ArrayList<Date>>{

    /**
     * Reads the date input and Converts the dates list into {@code ArrayList<Date>}.
     * @throws InputException If the input is empty or the input is not in the correct date format
     */
    @Override
    public ArrayList<Date> readFromConsole() throws InputException {
        try{
            String input = br.readLine();
            if(input==null || input.isEmpty()){
                throw new InputException("Input cannot be empty");
            }
            return Parsers.parseDates(input);
        }catch(IOException e){
            throw new InputException("Error reading input");
        }catch(ParseException e){
            throw new InputException("Invalid date format");
        }
    }
}
