import java.util.Date;

public interface CampAttendeeRegistry{
    boolean checkAvailablity() ;
    void registerForCamp(String userID) throws CampException;
    boolean isUserAttendee(String userID) ;
    void withdrawFromCamp(String userID) throws CampException;
    boolean hasUserWithdrawn(String userID) ;
    boolean checkDateClash(Date[] userDates) ;
    boolean isFacultyIncluded(String Faculty) ;
    boolean isDeadlineMet() ;
    boolean isUserAlreadyRegistered(String userID) ;
}