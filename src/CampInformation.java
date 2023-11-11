import java.util.ArrayList;
import java.util.Date;
public abstract class CampInformation implements Visibility {
    protected int campID;
    protected String campName;
    protected ArrayList<Date> campDates;
    protected Date registrationDeadline;
    protected String schoolOpenTo;
    protected String location;
    protected int totalSlots;
    protected int campCommitteeSlots;
    protected String description;
    protected String staffID;
    protected boolean visibility;
    protected boolean isCampActive;
    public CampInformation(int campID, String campName, ArrayList<Date> campDates, Date registrationDeadline, String schoolOpenTo, String location, int totalSlots, int campCommitteeSlots, String description, String staffID, boolean isCampActive, boolean isVisible) {
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
        this.isCampActive = isCampActive;
        this.visibility = isVisible;
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

    public void setCampName(String name){
        campName = name;
    }

    public void setCampDates(ArrayList<Date> dates){
        campDates = new ArrayList<>(dates);
    }

    public void setRegistrationDeadline(Date date){
        registrationDeadline = date;
    }

    public void setSchoolOpenTo(String schoolOpenTo){
        this.schoolOpenTo = schoolOpenTo;
    }

    public void setLocation(String location){
        this.location = location;
    }

    //the below 2 functions are abstract as I need to call the camp constraints object to check if changing the slot is allowed or not.
    public abstract void setTotalSlots(int newSlots) throws CampException;

    public abstract void setCampCommitteeSlots(int newSlots) throws CampException;

    public void setDescription(String description){
        this.description = description;
    }

    public boolean isCampActive(){
        return isCampActive;
    }
}
