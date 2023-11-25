import java.io.*;
import java.util.ArrayList;

public class DBReader {
    private static final String rootDirectory = "LOGS/";
    public static void Initialise() throws IOException,DBException{
        populatePrimaryKeys();
        for(Enquiry enquiry:readEnquiry())
            RegistryFactory.enquiryRegistry.addEntry(enquiry,enquiry.getID());
        for(Suggestion suggestion:readSuggestion())
            RegistryFactory.suggestionRegistry.addEntry(suggestion,suggestion.getID());
        for(Camp camp:readCamp())
            RegistryFactory.campRegistry.addEntry(camp,camp.getCampID());
        for(Student student:readStudent())
            RegistryFactory.studentRegistry.addEntry(student,student.getUserID());
        for(Staff staff:readStaff())
            RegistryFactory.staffRegistry.addEntry(staff,staff.getUserID());
        for(CampCommittee committee:readCommittee())
            RegistryFactory.committeeRegistry.addEntry(committee,committee.getUserID());
        for(Admin admin:readAdmin())
            RegistryFactory.adminRegistry.addEntry(admin,admin.getUserID());
        System.out.println("Database loaded successfully!");
    }
    private static void populatePrimaryKeys() throws IOException,DBException{
        BufferedReader reader = new BufferedReader(new FileReader("LOGS/nextValues.txt"));
        PrimaryKeyCounter.nextCampID = Integer.parseInt(reader.readLine());
        PrimaryKeyCounter.nextEnquiryID = Integer.parseInt(reader.readLine());
        PrimaryKeyCounter.nextSuggestionID = Integer.parseInt(reader.readLine());
        reader.close();
    }
    public static void Initialise(String test) throws IOException, DBException {
        populatePrimaryKeys();
        for(Object enquiry : readObjects("ENQUIRIES",new TXTDBEnquiry()))
            RegistryFactory.enquiryRegistry.addEntry((Enquiry) enquiry,((Enquiry) enquiry).getID());
        for(Object suggestion : readObjects("SUGGESTIONS",new TXTDBSuggestion()))
            RegistryFactory.suggestionRegistry.addEntry((Suggestion) suggestion,((Suggestion) suggestion).getID());
        for(Object camp : readObjects("CAMPS",new TXTDBCamp()))
            RegistryFactory.campRegistry.addEntry((Camp) camp,((Camp) camp).getCampID());
        for(Object student : readObjects("STUDENT",new TXTDBStudent()))
            RegistryFactory.studentRegistry.addEntry((Student) student,((Student) student).getUserID());
        for(Object staff : readObjects("STAFF",new TXTDBStaff()))
            RegistryFactory.staffRegistry.addEntry((Staff) staff,((Staff) staff).getUserID());
        for(Object committee : readObjects("COMMITTEE",new TXTDBCampCommittee()))
            RegistryFactory.committeeRegistry.addEntry((CampCommittee) committee,((CampCommittee) committee).getUserID());
        for(Object admin : readObjects("ADMIN",new TXTDBAdmin()))
            RegistryFactory.adminRegistry.addEntry((Admin) admin,((Admin) admin).getUserID());
    }

    private static <T extends TXTDB, W> ArrayList<W> readObjects(String dir, T obj) throws IOException, DBException{
        ArrayList<String> list = readDirectoryList(dir);
        ArrayList<W> objects = new ArrayList<W>();
        for(String item:list){
            File f = new File("LOGS/"+dir+"/"+item+".txt");
            FileInputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            W object = (W) obj.getObjectFromData(obj.getID(item), str);
            objects.add(object);
        }
        return objects;
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
    private static ArrayList<Admin> readAdmin() throws IOException, DBException{
        ArrayList<String> adminList = readDirectoryList("ADMIN");
        ArrayList<Admin> adminObjects = new ArrayList<Admin>();
        for(String admin:adminList){
            File f = new File("LOGS/ADMIN/"+admin+".txt");
            FileInputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            TXTDBAdmin adminObject = new TXTDBAdmin(null);
            Admin adminObj = adminObject.getObjectFromData(admin, str);
            adminObjects.add(adminObj);
        }
        return adminObjects;
    }

    private static ArrayList<String> readDirectoryList(String dirname) throws IOException {
        String directoryPath = rootDirectory + dirname + "/";
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
