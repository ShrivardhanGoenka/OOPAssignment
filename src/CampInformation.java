import java.util.ArrayList;
import java.util.Date;
/**
 * The {@code CampInformation} stores the necessary attributes for the camp in this camp management system, such as ID, name, dates, registration deadline, locaiton, etc.
 * It realizes the visibility class to provide a method to hide or unhide the camp to the student.
 * The visibility is meant to be controlled by the staff.
 */
public abstract class CampInformation implements Visibility {

    /** The ID of the camp */
    protected int campID;
    /** The name of the camp */
    protected String campName;
    /** The dates that the camp will be held */
    protected ArrayList<Date> campDates;
    /** The registration dates of the camp */
    protected Date registrationDeadline;
    /** The faculty from which the student are allowed to register and attend */
    protected String facultyOpenTo;
    /** The location of the camp */
    protected String location;
    /** The total maximum slots of committees and attendees for this camp */
    protected int totalSlots;
    /** The maximum slots reserved for committees*/
    protected int campCommitteeSlots;
    /** The description of the camp*/
    protected String description;
    /** The ID of staff that create this camp*/
    protected String staffID;
    /** A boolean indicating whether the camp is visible to student*/
    protected boolean visibility;

    /**
     * Constructor
     * @param campID 			The ID of the camp.
     * @param campName 			The name of the camp.
     * @param campDates 		The dates that the camp will be held.
     * @param registrationDeadline 	The date of registration deadline.
     * @param facultyOpenTo 		The faculty from which the students are allowed to register and attend.
     * @param location 			The location of the camp.
     * @param totalSlots 		The number of maximum slots for committee and attendees combined.
     * @param campCommitteeSlots 	The maximum number of slots reserved for camp committees.
     * @param description 		The description of the camp.
     * @param staffID 			The ID of staff that create this camp.
     * @param isVisible 		A boolean indicating whether the camp is visible to student.
     */
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

    /**
     * Returns the ID of the camp
     * @return {@code int} of ID
     */
    public int getCampID() {
        return campID;
    }

    /**
     * Returns the name of the camp.
     * @return String of camp name
     */
    public String getCampName() {
        return campName;
    }

    /**
     * Returns the list of dates the camp will be held.
     *
     * @return {@code ArrayList<Date>} of dates
     */
    public ArrayList<Date> getCampDates() {
        return campDates;
    }

    /**
     * Returns the date of registration deadline
     *
     * @return {@code Date} of registration deadline
     */
    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    /**
     * Returns the faculty from which the student are allowed to register and attend this camp
     *
     * @return {@code String} of faculty ("SCSE", "EEE", stc.); the String "*" indicates that all camp are allowed. 
     */
    public String getFacultyOpenTo() {
        return facultyOpenTo;
    }

    /**
     * Returns the location that the camp will take place at.
     *
     * @return {@code String} of location
     */
    public String getLocation() {
        return location;
    }

    /** 
     * Returns the total maximum slots of committees and attendees for this camp
     *
     * @return {@code int} of total slots.
     */
    public int getTotalSlots() {
        return totalSlots;
    }

    /** Returns the maximum slots reserved for committees
     * @return {@code int} of camp committee slots.
     */
    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }

    /**
     * Returns the description of the camp.
     *
     * @return {@code String} of description message.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the ID of staff that creates this camp.
     * @return {@code String} of staff ID.
     */
    public String getStaffID() {
        return staffID;
    }

    /**
     * Hides the camp from student.
     *
     * @throws CampException If the camp is already hidden
     */
    public void hide() throws CampException {
        if(!visibility) throw new CampException("Camp is already hidden");
        visibility = false;
    }
    /**
     * Unhides the camp to student.
     * @throws CampException If the camp is already visible
     */
    public void show() throws CampException {
        if(visibility) throw new CampException("Camp is already visible");
        visibility = true;
    }

    /**
     * Returns whether the camp is visible to student.
     *
     * @return A {@code boolean} indicating whether the camp is visible to student.
     */
    public boolean isVisible() {
        return visibility;
    }

    /**
     * Returns whether the camp is visible to student from a specific faculty.
     * @param faculty 			The faculty to check. "*" indicates that the camp is visible to all faculties.
     * @return A {@code boolean} indicating whether the camp is visible to the student from the input faculty.
     */
    public boolean isVisible(String faculty){
        if(facultyOpenTo.equals("*")) return true;
        return facultyOpenTo.equalsIgnoreCase(faculty);
    }

    /**
     * Changes the name of the camp.
     * This method is to be performed by staff.
     * @param name 		The new name for this camp.
     */
    public void setCampName(String name){
        campName = name;
    }

    /**
     * Changes the dates of the camp.
     * This method is to be performed by staff.
     *
     * @param dates 		The ArrayList of dates to change to.
     */
    public void setCampDates(ArrayList<Date> dates){
        campDates = new ArrayList<>(dates);
    }

    /**
     * Changes the registration date of the camp.
     * This method is to be performed by staff.
     *
     * @param date 		The Date of registration deadline to change to.
     */
    public void setRegistrationDeadline(Date date){
        registrationDeadline = date;
    }

    /**
     * Changes the faculty that the camp open to.
     * "*" indicates the camp is open to all faculty.
     * This method is to be performed by staff.
     *
     * @param facultyOpenTo 	The new allowed faculty from which student can register and attend the camp.
     * @throws CampException 	If the faculty that the camp opens to cannot be modified.
     */
    public void setFacultyOpenTo(String facultyOpenTo) throws CampException{
        if(this.facultyOpenTo.equals(facultyOpenTo)) throw new CampException("Camp is already open to " + facultyOpenTo);
        this.facultyOpenTo = facultyOpenTo;
    }

    /**
     * Changes the location details of the camp
     * @param location 		The new location
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * The abstract method to set the new attendees slot of the camp.
     * @param newSlots 			    The number of total slot to change to
     * @throws CampException                If the change is unsuccesful.
     */
    public abstract void setTotalSlots(int newSlots) throws CampException;

    /**
     * The abstract method to set the new camp committee slot of the camp.
     * @param newSlots 			    The number of slots reserved for camp committee to change to
     * @throws CampException                If the change is unsuccesful.
     */
    public abstract void setCampCommitteeSlots(int newSlots) throws CampException;

    /**
     * Changes the description of the camp.
     * @param description		    The description text to change to.
     */
    public void setDescription(String description){
        this.description = description;
    }

}
