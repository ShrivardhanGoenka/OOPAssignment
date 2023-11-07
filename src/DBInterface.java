import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;

public class DBInterface {
    ArrayList<String> readStudentsList() throws IOException{
        String directoryPath = "LOGS/STUDENT";
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
            students = readStudentsList();
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        for(String student : students){
            Registry.studentMap.put(student, addStudent(student));
        }


    }
}
