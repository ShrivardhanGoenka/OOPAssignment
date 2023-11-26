import java.util.ArrayList;

/**
 * The {@code DeleteCampMenu} class provides the execution logics of the staff's menu for deleting the camp.
 */
public class DeleteCampMenu implements IMenu<Staff>{

	/** 
	 * @return the menu description
	 */
    @Override
    public String getMenuDescription() {
        return "Delete Camp";
    }

	/**
	 * Executes the menu logics for deleing the camp.
	 * The logics are as follows:
	 * 1. The program prompt the user to choose the camp to delete.
	 * 2. The program shows the message to indicate whether the deletion is successful.
	 */
    @Override
    public void runMenu(Staff userObject) throws CampException {
        ArrayList<Camp> camps = userObject.createdCamps();
        if(camps.isEmpty()){
            System.out.println("You have not created any camps");
            return;
        }
        System.out.println("<------------------Choose the camp you want to delete------------------>");
        for(int i=0;i<camps.size();i++){
            System.out.println((i+1) + ". " + camps.get(i).getCampName());
        }
        ConsoleReaderInteger cr = new ConsoleReaderInteger();
        try{
            System.out.print("Your choice: ");
            int choice = cr.readFromConsole(1,camps.size());
            Camp chosen = camps.get(choice-1);
            userObject.deleteCamp(chosen);
        }catch (InputException | CampException e) {
            System.out.println(e.getMessage());
        }
    }
}
