import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * The {@code IConsoleReader} interface defines a contract for reading input of type {@code T} from the console.
 */
public interface IConsoleReader<T> {
    /** The buffer reader to read input from console */
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    /**
     * Reads input of type {@code T} from the console.
     *
     * @return the user's input of type {@code T} from the console
     * @throws InputException If there is an error reading the input or if the input is invalid
     */
    public T readFromConsole() throws InputException;
}
