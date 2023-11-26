/**
 * The {@code DatabaseWritable} is implemented by all classes that can write to the database.
 * The implementing class need to provide the implementation of DBWriter and getFileName methods.
 */
public interface DatabaseWritable {
    /**
     * Returns a String in the same format that is stored in the database.
     * @return {@code String} representing the formatted text to write
     */
    public String DBWriter();

    /**
     * Retrieves the file name of the database to write to.
     * @return {@code String} representing the filename to write to
     */
    public String getFileName();
}
