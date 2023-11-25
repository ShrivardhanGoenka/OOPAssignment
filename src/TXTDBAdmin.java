public class TXTDBAdmin extends TXTDB<Admin,String>{
    public TXTDBAdmin() {
        super();
    }
    public TXTDBAdmin(Admin obj) {
        super(obj);
    }

    @Override
    public String getFileName() {
        return obj.getUserID() + ".txt";
    }

    @Override
    public String getWriteData() throws DBException {
        TXTDBUser user = new TXTDBUser(obj);
        return user.getWriteData();
    }

    @Override
    public Admin getObjectFromData(String string, String data) throws DBException {
        TXTDBUser user = new TXTDBUser(null);
        User userObj = user.getObjectFromData(string, data);
        return new Admin(userObj.getUserID(), userObj.getPassword(), userObj.getEmail(), userObj.getFaculty(), userObj.isLocked());
    }
    @Override
    public String getID(String id){
        return id;
    }
}
