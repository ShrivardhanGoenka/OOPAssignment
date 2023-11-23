import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
public abstract class TXTDB<T,ID> {
    public abstract String getFileName();
    public abstract String getWriteData() throws DBException;
    public abstract T getObjectFromData(String id,String data) throws DBException;
    public void populate(String root){

    }
    ArrayList<String> readDirectoryList(String root) throws DBException {
        String directoryPath = "LOGS/" + root + "/";
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
            throw new DBException("Directory not found");
        }
        return dir;
    }
}
