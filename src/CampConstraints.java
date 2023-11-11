import java.util.ArrayList;
import java.util.Date;
public class CampConstraints {
    int availableSlots;
    int availableCommitteeSlots;
    Date registrationDeadline;
    String schoolOpenTo;
    ArrayList<String> withdrawn;
    ArrayList<Date> campDates;

    public CampConstraints(int availableSlots, int availableCommitteeSlots, Date registrationDeadline, String schoolOpenTo, ArrayList<String> withdrawn, ArrayList<Date> campDates){
        this.availableSlots = availableSlots;
        this.availableCommitteeSlots = availableCommitteeSlots;
        this.registrationDeadline = registrationDeadline;
        this.schoolOpenTo = schoolOpenTo;
        this.withdrawn = withdrawn;
        this.campDates = campDates;
    }

    void checkAttendeeRegistration(String userID, ArrayList<Date> blockedDates, String faculty) throws CampException{
        if(availableSlots <= 0){
            throw new CampException("Camp is full!");
        }
        Date dateToday = new Date();
        if(dateToday.after(registrationDeadline)){
            throw new CampException("Registration deadline has passed!");
        }
        for(Date blockedDate : blockedDates){
            for(Date campDate : campDates){
                if(blockedDate.equals(campDate)){
                    throw new CampException("Date clash!");
                }
            }
        }
        if(!schoolOpenTo.equals("*") && !schoolOpenTo.equalsIgnoreCase(faculty)){
            throw new CampException("Camp is not open to your school!");
        }
        if(withdrawn.contains(userID)) {
            throw new CampException("User has already withdrawn from this camp!");
        }
    }

    void checkCommitteeRegistration(String userID, ArrayList<Date> blockedDates, String faculty) throws CampException{
        checkAttendeeRegistration(userID, blockedDates, faculty);
        if(availableCommitteeSlots <= 0){
            throw new CampException("Camp committee is full!");
        }
    }
    void addRegistration(){
        availableSlots--;
    }
    void addCommitteeMember(){
        availableSlots--;
        availableCommitteeSlots--;
    }
    void withdrawAttendee(String userID){
        availableSlots++;
        withdrawn.add(userID);
    }
    //difference = currentslots - newslots
    boolean changeAttendeeSlots(int difference){
        if (difference > availableSlots) return false;
        availableSlots -= difference;
        return true;
    }

    boolean changeCampCommitteeSlots(int difference){
        if (difference > availableCommitteeSlots) return false;
        availableCommitteeSlots -= difference;
        return true;
    }

}
