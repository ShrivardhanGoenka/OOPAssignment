import java.io.BufferedReader;
import java.io.StringReader;

public class TXTDBUser extends TXTDB<User, String>{

    public TXTDBUser(User obj) {
        super(obj);
    }

    @Override
    public String getFileName() {
        return obj.getUserID() + ".txt";
    }

    @Override
    public String getWriteData() throws DBException {
        String output = "";
        output += obj.getPassword() + "\n";
        output += obj.getEmail() + "\n";
        output += obj.getFaculty() + "\n";
        output += obj.isLocked() ? "lock\n" : "unlock\n";
        return output;
    }

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
    @Override
    public String getID(String id){
        return id;
    }
}
