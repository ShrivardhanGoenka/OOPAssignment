import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DBReader {
    public static void Initialise() throws IOException,DBException{
        for(Enquiry enquiry:readEnquiry())
            Registry.addEnquiry(enquiry);
        for(Suggestion suggestion:readSuggestion())
            Registry.addSuggestion(suggestion);
        for(Camp camp:readCamp())
            Registry.addCamp(camp);
        for(Student student:readStudent())
            Registry.addStudent(student);
        for(Staff staff:readStaff())
            Registry.addStaff(staff);
        for(CampCommittee committee:readCommittee())
            Registry.addCommittee(committee);
    }

    private static ArrayList<Student> readStudent() throws IOException,DBException{
        ArrayList<String> studentList = readDirectoryList("STUDENT");
        ArrayList<Student> studentObjects = new ArrayList<Student>();
        for(String student:studentList){
            File f = new File("LOGS/STUDENT/"+student+".txt");
            FileInputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            TXTDBStudent studentObject = new TXTDBStudent(null);
            Student studentObj = studentObject.getObjectFromData(student, str);
            studentObjects.add(studentObj);
        }
        return studentObjects;
    }
    private static ArrayList<Staff> readStaff() throws IOException,DBException{
        ArrayList<String> staffList = readDirectoryList("STAFF");
        ArrayList<Staff> staffObjects = new ArrayList<Staff>();
        for(String staff:staffList){
            File f = new File("LOGS/STAFF/"+staff+".txt");
            FileInputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            TXTDBStaff staffObject = new TXTDBStaff(null);
            Staff staffObj = staffObject.getObjectFromData(staff, str);
            staffObjects.add(staffObj);
        }
        return staffObjects;
    }
    private static ArrayList<Camp> readCamp() throws IOException,DBException{
        ArrayList<String> campList = readDirectoryList("CAMPS");
        ArrayList<Camp> campObjects = new ArrayList<Camp>();
        for(String camp:campList){
            File f = new File("LOGS/CAMPS/"+camp+".txt");
            FileInputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            TXTDBCamp campObject = new TXTDBCamp(null);
            Camp campObj = campObject.getObjectFromData(Integer.parseInt(camp.substring(4)), str);
            campObjects.add(campObj);
        }
        return campObjects;
    }
    private static ArrayList<Enquiry> readEnquiry() throws IOException,DBException{
        ArrayList<String> enquiryList = readDirectoryList("ENQUIRIES");
        ArrayList<Enquiry> enquiryObjects = new ArrayList<Enquiry>();
        for(String enquiry:enquiryList){
            File f = new File("LOGS/ENQUIRIES/"+enquiry+".txt");
            FileInputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            TXTDBEnquiry enquiryObject = new TXTDBEnquiry(null);
            Enquiry enquiryObj = enquiryObject.getObjectFromData(Integer.parseInt(enquiry.substring(7)), str);
            enquiryObjects.add(enquiryObj);
        }
        return enquiryObjects;
    }
    private static ArrayList<Suggestion> readSuggestion() throws IOException,DBException{
        ArrayList<String> suggestionList = readDirectoryList("SUGGESTIONS");
        ArrayList<Suggestion> suggestionObjects = new ArrayList<Suggestion>();
        for(String suggestion:suggestionList){
            File f = new File("LOGS/SUGGESTIONS/"+suggestion+".txt");
            FileInputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            TXTDBSuggestion suggestionObject = new TXTDBSuggestion(null);
            Suggestion suggestionObj = suggestionObject.getObjectFromData(Integer.parseInt(suggestion.substring(10)), str);
            suggestionObjects.add(suggestionObj);
        }
        return suggestionObjects;
    }
    private static ArrayList<CampCommittee> readCommittee() throws IOException,DBException{
        ArrayList<String> committeeList = readDirectoryList("COMMITTEE");
        ArrayList<CampCommittee> committeeObjects = new ArrayList<CampCommittee>();
        for(String committee:committeeList){
            File f = new File("LOGS/COMMITTEE/"+committee+".txt");
            FileInputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            TXTDBCampCommittee committeeObject = new TXTDBCampCommittee(null);
            CampCommittee committeeObj = committeeObject.getObjectFromData(committee, str);
            committeeObjects.add(committeeObj);
        }
        return committeeObjects;
    }

    private static ArrayList<String> readDirectoryList(String dirname) throws IOException {
        String directoryPath = "LOGS/" + dirname + "/";
        ArrayList<String> dir = new ArrayList<String>();
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if(files == null) return new ArrayList<String>();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    dir.add(file.getName().substring(0, file.getName().length() - 4));
                }
            }
        } else {
            throw new IOException("Directory not found");
        }
        return dir;
    }
}
