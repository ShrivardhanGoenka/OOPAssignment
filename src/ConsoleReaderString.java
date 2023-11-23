import java.io.IOException;
/**
 * The {@code ConsoleReaderString} provides the method for reading and parsing the string from the input in the console.
 * The {@code ConsoleReaderString} realizes {@code IConsoleReader} interface with type String.
 */
public class ConsoleReaderString implements IConsoleReader<String>{
    /**
     * Reads a string from the console input and validates the input string.
     *
     * @return The {@code String} of input read from the console.
     * @throws InputException If there is an error reading the input or the input is empty.
     */
    public String readFromConsole() throws InputException {
        try{
            String input = br.readLine();
            if(input==null || input.isEmpty()){
                throw new InputException("Input cannot be empty");
            }
            return input;
        }catch(IOException e){
            throw new InputException("Error reading input");
        }
    }
}
