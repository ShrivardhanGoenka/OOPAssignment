//implemented by all classes which can be written to the database
/**
 * The {@code DatabaseWritable} is implemented by all classes that can write to the database.
 * The superclass need to provide the implementation of DBWriter and getFileName methods.
 */
public interface DatabaseWritable {
    /**
     * Returns a String in the same format that is stored in the database.
     */
    String DBWriter();

    /**
     * Retrieves the file name of the database to write to.
     */
    String getFileName();
}
