/**
 * {@code CampCommitteeRegistry} provides template for methods to handle committee's registration.
 */
public interface CampCommitteeRegistry {
    boolean isCommitteeSlotAvailable();
    void registerForCommittee(String userID);
    boolean isUserCommitteeMember(String userID);

}
