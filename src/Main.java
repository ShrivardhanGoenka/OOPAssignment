import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        DBInterface dbInterface = new DBInterface();
        dbInterface.populateStudents();
        dbInterface.populateCamps();
        //StudentDriver.studentDriverPage1();
        for (Camp camp : Registry.campMap.values()){
            System.out.println(camp.getCampName());
        }
    }
}