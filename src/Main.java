import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        DBInterface dbInterface = new DBInterface();
        dbInterface.populateEnquiries();
        for(Map.Entry<Integer, Enquiry> i: Registry.enquiryMap.entrySet()){
            Enquiry e = i.getValue();
            System.out.println(e.getEnquiryString());

        }
        dbInterface.populateStudents();
        dbInterface.populateCamps();
        //StudentDriver.studentDriverPage1();
    }
}