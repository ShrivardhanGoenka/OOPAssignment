public class ViewMyCommitteeCampMenu extends IMenu<CampCommittee>{

	/**
	 * Executes the menu logics for viewing camp that the camp committee oversee.
	 * @param committeeObject 			The camp committee that runs the menu.
	 */
    public void runMenu(CampCommittee committeeObject) {
        System.out.println("You are in charge of the camp: " + committeeObject.getCamp().getCampName());
        System.out.println("Camp Details: ");
        CustomPrinterCamp printer = new CustomPrinterCamp();
        printer.print(committeeObject.getCamp());
    }

    /**
     * @return the menu description
     */
    public String getMenuDescription () {
        return "View My Committee Camp";
    }
}
