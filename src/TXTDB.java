/**
 * The {@code TXTDB} abstract class is a base class for handling text-based database operations. 
 * It provides methods for getting file names, formatting data for writing to database, and converting data from text to object representations.
 * @param <T> The type of object
 * @param <ID> The type of identifier associated with the object in the registry HashMap
 */
public abstract class TXTDB<T,ID> {
    /**
     * The object representing the data stored in the database.
     */
    protected T obj;

    /**
     * Constructs an empty {@code TXTDB}.
     */
    public TXTDB(){
        obj = null;
    }

    /**
     * Constructs {@code TXTDB} object with an initial object data.
     *
     * @param obj               The initial object data
     */
    public TXTDB(T obj){
        this.obj = obj;
    }

    /**
     * Gets the file name to read/write from/to the database
     *
     * @return {@code String} representing the file name
     */
    public abstract String getFileName();

    /**
     * Gets the formatted data for writing to database
     *
     * @return {@code String} representing the formatted data
     * @throws DBException      If an error occurs during the data formatting process
     */
    public abstract String getWriteData() throws DBException;

    /**
     * Converts data from a text representation to an object of type {@code T} using the provided identifier.
     *
     * @param id                The identifier associated with the data
     * @param data              The name of the data
     * @return object of type {@code T} representing the converted data
     * @throws DBException      If an error occurs during the data conversion process
     */
    public abstract T getObjectFromData(ID id, String data) throws DBException;

    /**
     * Converts a {@code String} id to the specified id type {@code ID}.
     *
     * @param id            The identifier.
     * @return object of type {@code ID} representing the converted identifier
     */
    public abstract ID getID(String id);

}
