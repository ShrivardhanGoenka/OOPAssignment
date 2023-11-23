/**
 * The {@code UserException} class handles the Exception Error related to the user action.
 */
public class UserException extends Exception{
    /**
     * Constructor for Exception with Exception message
     *
     * @param message  		The error message
     */
    public UserException(String message){
        super(message);
    }

    /**
     * Constructor for empty Exception message
     */
    public UserException(){
        super();
    }
}
