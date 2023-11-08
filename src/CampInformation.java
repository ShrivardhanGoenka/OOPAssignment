import java.util.ArrayList;
import java.util.Date;
public class CampInformation implements Visibility {
    int campID;
    String campName;
    ArrayList<Date> campDates;
    Date registrationDeadline;
    String schoolOpenTo;
    String location;
    int totalSlots;
    int campCommitteeSlots;
    String description;
    String staffID;
    boolean visibility;
    public CampInformation(int campID, String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo, String location, int totalSlots, int campCommitteeSlots, String description, String staffID) {
        this.campID = campID;
        this.campName = campName;
        this.campDates = campDates;
        this.registrationDeadline = registrationDeadline;
        this.schoolOpenTo = schoolOpenTo;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffID = staffID;
    }

    public int getCampID() {
        return campID;
    }

    public String getCampName() {
        return campName;
    }

    public ArrayList<Date> getCampDates() {
        return campDates;
    }

    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    public String getSchoolOpenTo() {
        return schoolOpenTo;
    }

    public String getLocation() {
        return location;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }

    public String getDescription() {
        return description;
    }

    public String getStaffID() {
        return staffID;
    }
    public void hide() {
        visibility = false;
    }
    public void show() {
        visibility = true;
    }
    public boolean isVisible() {
        return visibility;
    }
    public boolean isVisible(String faculty){
        if(schoolOpenTo.equals("*")) return true;
        return schoolOpenTo.equalsIgnoreCase(faculty);
    }
}
