import java.io.IOException;
/**
 * The {@code ConsoleReaderInteger} provides the method for reading and parsing the integer from the input in the console.
 * The {@code ConsoleReaderInteger} realizes {@code IConsoleReader} interface with type Integer
 */
public class ConsoleReaderInteger implements IConsoleReader<Integer>{
    /**
     * Reads a single input number from the console input and converts the number to Integer type.
     *
     * @return The Integer of number read from the console.
     * @throws InputException If there is an error parsing the Integer input or the input is empty.
     */
    public Integer readFromConsole() throws InputException {
        try{
            String input = br.readLine();
            if(input==null || input.isEmpty()){
                throw new InputException("Input cannot be empty");
            }
            return Integer.parseInt(input);
        }catch(IOException e){
            throw new InputException("Error reading input");
        }catch(NumberFormatException e){
            throw new InputException("Input must be an integer");
        }
    }
    /**
     * Reads a single input number from the console input with <b>a specified ranges (inclusive)</b> and converts the number to Integer type.
     *
     * @param a 			The lower bound of number input to parse (inclusive)
     * @param b 			The upper bound of number input to parse (inclusive)
     * @return The Integer of number read from the console.
     * @throws InputException 		If there is an error parsing the Integer input or the number is not in specified range or the input is empty.
     */
    public Integer readFromConsole(int a, int b) throws InputException {
        int input = readFromConsole();
        if(input < a || input > b){
            throw new InputException("Input must be between " + a + " and " + b);
        }
        return input;
    }
}
