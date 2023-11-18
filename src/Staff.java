import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public class Staff extends User implements DatabaseWritable{
    private HashMap<Integer, Camp> createdCamps;
    public Staff(String userID, String password, String email, String faculty, boolean isLocked){
        super(userID, password, email, faculty, isLocked);
    }
    public Staff(String userID, String password, String email, String faculty, boolean isLocked, HashMap<Integer,Camp> createdCamps){
        super(userID, password, email, faculty, isLocked);
        this.createdCamps = createdCamps;
    }

    @Override
    public void printProfile() {
        super.printProfile();
        System.out.println("Domain: Staff");
    }

    @Override
    public String DBWriter(){
        String output = super.DBWriter();
        String campList = "";
        for(Camp camp: createdCamps.values()){
            campList += camp.getCampID() + ",";
        }
        if(!campList.isEmpty())
            campList = campList.substring(0, campList.length()-1);
        output += campList;
        return output;
    }

    public String getFileName(){
        return getUserID() + ".txt";
    }
    public ArrayList<Camp> createdCamps(){
        return new ArrayList<>(Registry.campMap.values());
    }

    public void createCamp(String campName, String description, boolean openToAll, boolean isVisible, ArrayList<Date> campDates, String location, Date registrationDeadline, int totalSlots, int campCommitteeSlots){
        Camp newCamp = new Camp(Registry.nextCampID,campName, campDates, registrationDeadline, (openToAll?"*":getFaculty()), location,totalSlots, campCommitteeSlots, description, getUserID(), isVisible);
        Registry.campMap.put(newCamp.getCampID(), newCamp);
        createdCamps.put(newCamp.getCampID(), newCamp);
    }


}
