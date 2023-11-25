/**
 * The {@code TXTDBAdmin} class extends the {@code TXTDB} class and is specifically designed for handling text-based
 * database operations for {@code Admin} objects.
 */
public class TXTDBAdmin extends TXTDB<Admin,String>{
    /**
     * Constructs an empty {@code TXTDBAdmin} object.
     */
    public TXTDBAdmin() {
        super();
    }
    /**
     * Constructs a {@code TXTDBAdmin} object with an exsiting {@code Admin} object from the database.
     *
     * @param obj                   The initial {@code Admin} object
     */
    public TXTDBAdmin(Admin obj) {
        super(obj);
    }

    /**
     * Gets the file name associated with the the admin's userID
     *
     * @return {@code String} representing the file name
     */
    @Override
    public String getFileName() {
        return obj.getUserID() + ".txt";
    }

    /**
     * Gets the formatted data for writing to the database file by calling {@code TXTDBUser}.
     *
     * @return {@code String} representing the formatted data
     * @throws DBException              If an error occurs during the data formatting process
     */
    @Override
    public String getWriteData() throws DBException {
        TXTDBUser user = new TXTDBUser(obj);
        return user.getWriteData();
    }

    /**
     * Converts data from a text representation to {@code Admin} object.
     *
     * @param string                    The userID 
     * @param data                      The text representation of the admin information 
     * @return {@code Admin} object representing the converted data
     * @throws DBException              If an error occurs during the data conversion process
     */
    @Override
    public Admin getObjectFromData(String string, String data) throws DBException {
        TXTDBUser user = new TXTDBUser(null);
        User userObj = user.getObjectFromData(string, data);
        return new Admin(userObj.getUserID(), userObj.getPassword(), userObj.getEmail(), userObj.getFaculty(), userObj.isLocked());
    }

    /**
     * Retrieves the formatted unique identifier of the user
     * @param id                        The unique identifier to format
     * @return {@code String}
     */
    @Override
    public String getID(String id){
        return id;
    }
}
