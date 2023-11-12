import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.Date;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        DBInterface dbInterface = new DBInterface();
        dbInterface.loadNextValues();
        dbInterface.populateSuggestions();
        dbInterface.populateEnquiries();
        dbInterface.populateCamps();
        dbInterface.populateStudents();
        try {
            StudentDriver.studentDriverPage1();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

