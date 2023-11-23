public class ViewMyCommitteeCampMenu extends IMenu<CampCommittee>{
    public void runMenu(CampCommittee committeeObject) {
        System.out.println("You are in charge of the camp: " + committeeObject.getCamp().getCampName());
    }

    /**
     * @return the menu description
     */
    public String getMenuDescription () {
        return "View My Committee Camp";
    }
}
