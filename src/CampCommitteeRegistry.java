public interface CampCommitteeRegistry {
    boolean isCommitteeSlotAvailable();
    void registerForCommittee(String userID);
    boolean isUserCommitteeMember(String userID);

}
