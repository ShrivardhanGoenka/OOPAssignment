import java.io.BufferedReader;
import java.io.StringReader;

/**
 * The {@code TXTDBUser} class extends the {@code TXTDB} class and is specifically designed for handling text-based
 * database operations for {@code User} objects.
 */
public class TXTDBUser extends TXTDB<User, String>{

    /**
     * Constructs an empty {@code TXTDBUser} object.
     */
    public TXTDBUser(User obj) {
        super(obj);
    }

    /**
     * Gets the file name associated with the the user's userID
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
        String output = "";
        output += obj.getPassword() + "\n";
        output += obj.getEmail() + "\n";
        output += obj.getFaculty() + "\n";
        output += obj.isLocked() ? "lock\n" : "unlock\n";
        return output;
    }

    /**
     * Converts data from a text representation to {@code User} object.
     *
     * @param string                    The userID 
     * @param data                      The text representation of the user information 
     * @return {@code User} object representing the converted data
     * @throws DBException              If an error occurs during the data conversion process
     */
    @Override
    public User getObjectFromData(String string, String data) throws DBException {
        try{
            BufferedReader reader = new BufferedReader(new StringReader(data));
            String password = reader.readLine();
            String email = reader.readLine();
            String faculty = reader.readLine();
            boolean isLocked = reader.readLine().equals("lock");
            reader.close();
            return new User(string, password, email, faculty, isLocked);
        }
        catch (Exception e){
            throw new DBException("Error in reading user data for user " + string);
        }
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
