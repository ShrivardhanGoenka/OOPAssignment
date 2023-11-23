import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;
public class ConsoleReaderDateList implements IConsoleReader<ArrayList<Date>>{

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
