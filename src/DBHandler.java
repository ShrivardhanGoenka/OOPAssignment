import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DBHandler {
    static <T> void readIn(String folderName) throws IOException {
        String root = "LOGS/";
        String path = root+folderName + "/";
        ArrayList<String> files = readDirectoryList(folderName);
        for(String file:files){
            File f = new File(path+file+".txt");
            try {
                FileInputStream fis = new FileInputStream(f);
                byte[] data = new byte[(int) f.length()];
                fis.read(data);
                fis.close();
                String str = new String(data, "UTF-8");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Removes the file from the database folder.
     * @param folder 		The folder name (STUDENT/COMMITTEE/CAMPS/ENQUIRIES/STAFF/SUGGESTIONS)
     */
    void removeAllFiles(String folder){
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
    /**
     * Finds all file in the specified directories.
     * @param dirname 			The directories path to find the files from.
     * @return {@code ArrayList<String>} of the file path found.
     * @throws IOException 		If the directory is not found.
     */
    static ArrayList<String> readDirectoryList(String dirname) throws IOException {
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
