import java.util.ArrayList;
import java.util.Date;
public abstract class CampInformation implements Visibility {
    protected int campID;
    protected String campName;
    protected ArrayList<Date> campDates;
    protected Date registrationDeadline;
    protected String facultyOpenTo;
    protected String location;
    protected int totalSlots;
    protected int campCommitteeSlots;
    protected String description;
    protected String staffID;
    protected boolean visibility;
    public CampInformation(int campID, String campName, ArrayList<Date> campDates, Date registrationDeadline, String facultyOpenTo, String location, int totalSlots, int campCommitteeSlots, String description, String staffID, boolean isVisible) {
        this.campID = campID;
        this.campName = campName;
        this.campDates = campDates;
        this.registrationDeadline = registrationDeadline;
        this.facultyOpenTo = facultyOpenTo;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffID = staffID;
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

    public String getFacultyOpenTo() {
        return facultyOpenTo;
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
        if(facultyOpenTo.equals("*")) return true;
        return facultyOpenTo.equalsIgnoreCase(faculty);
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

    public void setFacultyOpenTo(String facultyOpenTo){
        this.facultyOpenTo = facultyOpenTo;
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

    public String DBWriter(){
        String output = "";
        output += campName + "\n";
        String campdates = "";
        for (Date campdate : campDates) {
            campdates += DBInterface.returnDateVal(campdate) + ",";
        }
        if(!campdates.isEmpty())
        campdates = campdates.substring(0, campdates.length()-1);
        output += campdates + "\n";
        output += DBInterface.returnDateVal(registrationDeadline) + "\n";
        output += facultyOpenTo + "\n";
        output += location + "\n";
        output += totalSlots + "\n";
        output += campCommitteeSlots + "\n";
        output += description + "\n";
        output += staffID + "\n";
        output += visibility? "visible\n" : "notvisible\n";
        return output;
    }
}
