import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DBWriter {
    private final static String rootDirectory = "LOGS/";
    public static void saveState() throws FileNotFoundException {
        writePrimaryKeys();
        for (Admin admin : RegistryFactory.adminRegistry.getAllEntries())
            writeObject(rootDirectory + "ADMIN/", new TXTDBAdmin(admin));
        for (CampCommittee committee : RegistryFactory.committeeRegistry.getAllEntries())
            writeObject(rootDirectory + "COMMITTEE/", new TXTDBCampCommittee(committee));
        for (Staff staff : RegistryFactory.staffRegistry.getAllEntries())
            writeObject(rootDirectory + "STAFF/", new TXTDBStaff(staff));
        for (Student student : RegistryFactory.studentRegistry.getAllEntries())
            writeObject(rootDirectory + "STUDENT/", new TXTDBStudent(student));
        for (Camp camp : RegistryFactory.campRegistry.getAllEntries())
            writeObject(rootDirectory + "CAMPS/", new TXTDBCamp(camp));
        for (Enquiry enquiry : RegistryFactory.enquiryRegistry.getAllEntries())
            writeObject(rootDirectory + "ENQUIRIES/", new TXTDBEnquiry(enquiry));
        for (Suggestion suggestion : RegistryFactory.suggestionRegistry.getAllEntries())
            writeObject(rootDirectory + "SUGGESTIONS/", new TXTDBSuggestion(suggestion));
    }

    private static void writePrimaryKeys() throws FileNotFoundException {
        try{
            PrintWriter writer = new PrintWriter(rootDirectory + "nextValues.txt");
            writer.write(PrimaryKeyCounter.nextCampID + "\n");
            writer.write(PrimaryKeyCounter.nextEnquiryID + "\n");
            writer.write(PrimaryKeyCounter.nextSuggestionID + "\n");
            writer.close();
        } catch (FileNotFoundException e) {
            PrintWriter writer = new PrintWriter(rootDirectory + "nextValues.txt");
            writer.write(PrimaryKeyCounter.nextCampID + "\n");
            writer.write(PrimaryKeyCounter.nextEnquiryID + "\n");
            writer.write(PrimaryKeyCounter.nextSuggestionID + "\n");
            writer.close();
        }
    }
    private static <T extends TXTDB> void writeObject(String directory, T obj){
        try{
            removeAllFiles(directory);
            PrintWriter writer = new PrintWriter(directory + obj.getFileName());
            writer.write(obj.getWriteData());
            writer.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error in writing " + obj.getFileName());
        }
    }
    private static void removeAllFiles(String folder){
        folder = "LOGS/" + folder + "/";
        File directory = new File(folder);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if(files == null) return;
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    file.delete();
                }
            }
        }
    }

}
