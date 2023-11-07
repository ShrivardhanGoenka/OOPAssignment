import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        DBInterface dbInterface = new DBInterface();
        dbInterface.populateStudents();
        StudentDriver.studentDriverPage1();
    }
}