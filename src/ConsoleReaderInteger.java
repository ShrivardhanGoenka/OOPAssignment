import java.io.IOException;
public class ConsoleReaderInteger implements IConsoleReader<Integer>{
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

    public Integer readFromConsole(int a, int b) throws InputException {
        int input = readFromConsole();
        if(input < a || input > b){
            throw new InputException("Input must be between " + a + " and " + b);
        }
        return input;
    }
}
