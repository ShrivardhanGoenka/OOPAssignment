/**
 * The {@code CampException} class handles the Exception Error related to the camp registry and action.
 */
public class CampException extends Exception{
    /**
     * Constructor for empty Exception message
     */
    public CampException() {
        super();
    }

    /**
     * Constructor for Exception with Exception message
     *
     * @param message  		The error message.
     */
    public CampException(String message) {
        super(message);
    }
}
