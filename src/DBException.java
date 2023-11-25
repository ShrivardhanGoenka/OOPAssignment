/**
 * The {@code DBException} class provides exception in case there is error modifying the database.
 */
public class DBException extends Exception{

    /**
     * Constructs an Exception for handling error related to database
     * @param message 				The error message
     */
    public DBException(String message){
        super(message);
    }
}
