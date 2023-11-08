import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Date;

public class DBInterface {
    ArrayList<String> readDirectoryList(String dirname) throws IOException{
        String directoryPath = "LOGS/" + dirname + "/";
        ArrayList<String> students = new ArrayList<String>();
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    students.add(file.getName().substring(0, file.getName().length() - 4));
                }
            }
        } else {
            throw new IOException("Directory not found");
        }
        return students;
    }

    Student addStudent(String userID){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("LOGS/STUDENT/" + userID + ".txt"));
            String password = reader.readLine();
            String email = reader.readLine();
            String faculty = reader.readLine();
            String temp = reader.readLine();
            boolean isLocked = false;
            if(temp.equals("locked")) isLocked = true;
            reader.close();
            return new Student(userID, password, email, faculty, isLocked);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    void populateStudents(){
        ArrayList<String> students = new ArrayList<String>();
        try{
            students = readDirectoryList("STUDENT");
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        for(String student : students){
            Registry.studentMap.put(student, addStudent(student));
        }
    }

    Camp readCampDetails(int campID){
        Camp camp = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("LOGS/CAMPS/camp" + campID + ".txt"));

            // Read camp details line by line
            String campName = reader.readLine();
            String dates = reader.readLine();
            String registrationClosingDate = reader.readLine();
            String userGroup = reader.readLine();
            String location = reader.readLine();
            int totalSlots = Integer.parseInt(reader.readLine());
            int campCommitteeSlots = Integer.parseInt(reader.readLine());
            String description = reader.readLine();
            String staffInCharge = reader.readLine();

            // Parse the dates and registration deadline
            ArrayList<Date> campDates = parseDates(dates);
            Date regDeadline = parseDate(registrationClosingDate);

            // Create the CampInformation object
            camp = new Camp(campID, campName, campDates, regDeadline, userGroup, location, totalSlots, campCommitteeSlots, description, staffInCharge);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return camp;
    }

    // Helper method to parse dates in the format: dd/MM/yyyy,dd/MM/yyyy
    private static ArrayList<Date> parseDates(String dates) {
        ArrayList<Date> dateList = new ArrayList<>();
        try {
            String[] dateArr = dates.split(",");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            for (String dateStr : dateArr) {
                Date date = dateFormat.parse(dateStr.trim());
                dateList.add(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateList;
    }

    // Helper method to parse a date in the format: dd/MM/yyyy
    private static Date parseDate(String dateStr) {
        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = dateFormat.parse(dateStr.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }

    void populateCamps(){
        ArrayList<String> camps = new ArrayList<String>();
        try{
            camps = readDirectoryList("CAMPS");
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        for(String camp : camps){
            Registry.campMap.put(Integer.parseInt(camp.substring(4)), readCampDetails(Integer.parseInt(camp.substring(4))));
        }
    }
}
