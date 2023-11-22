import java.io.IOException;
public class ConsoleReaderString implements IConsoleReader<String>{
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
