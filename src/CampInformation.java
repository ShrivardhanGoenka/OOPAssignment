import java.util.ArrayList;
import java.util.Date;
public class CampInformation {
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

}
