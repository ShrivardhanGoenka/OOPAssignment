import java.util.ArrayList;
import java.util.Date;
/**
 * The {@code EditCampMenu} class provides the execution logics of the menu for editing the camp details.
 */
public class EditCampMenu implements IMenu<Staff> {

	/** 
	 * @return the menu description
	 */
    @Override
    public String getMenuDescription() {
        return "Edit Camp";
    }

	/**
	 * Executes the menu logics for changing the camp details.
	 * The logics are as follows:
	 * 1. The program prompt the user to choose the camp to edit.
	 * 2. If the user enter an invalid choice, the menu will be terminated.
	 * 3. If the user enter a valid choice, the field to be modified will be shown.
	 * 4. The user choose the field to modify.
	 * 5. The user enter the new value for the chosen field.
	 * 
	 * @throws CampException 		If the modification to the camp cannot be made.
	 */
    @Override
    public void runMenu(Staff userObject) throws CampException {
        ConsoleReaderInteger intReader = new ConsoleReaderInteger();
        ConsoleReaderString stringReader = new ConsoleReaderString();
        ConsoleReaderDateList dateListReader = new ConsoleReaderDateList();
        ConsoleReaderDate dateReader = new ConsoleReaderDate();
        try{
            System.out.println("<------------------Choose the camp you want to edit------------------>");
            ArrayList<Camp> camps = userObject.createdCamps();
            for(int i=0;i<camps.size();i++){
                System.out.println((i+1) + ". " + camps.get(i).getCampName());
            }
            System.out.print("Your choice: ");
            int choice = intReader.readFromConsole(1, camps.size());
            Camp chosen = camps.get(choice-1);
            System.out.println("<------------------Details of the camp------------------>");
            CustomPrinterCamp printer = new CustomPrinterCamp();
            printer.print(chosen);
            System.out.println("<------------------Choose the field you want to edit------------------>");
            System.out.println("1. Camp Name");
            System.out.println("2. Camp Description");
            System.out.println("3. Camp Dates");
            System.out.println("4. Camp Location");
            System.out.println("5. Camp Registration Deadline");
            System.out.println("6. Camp Total Slots");
            System.out.println("7. Camp Committee Slots");
            System.out.println("8. Camp Visibility");
            System.out.println("9. Faculty Open to");
            System.out.println("10. Exit");
            System.out.print("Your choice: ");
            choice = intReader.readFromConsole(1, 10);
            switch(choice){
                case 1:
                    System.out.print("Enter new camp name: ");
                    chosen.setCampName(stringReader.readFromConsole());
                    break;
                case 2:
                    System.out.print("Enter new camp description: ");
                    chosen.setDescription(stringReader.readFromConsole());
                    break;
                case 3:
                    System.out.print("Enter new camp dates (dd/mm/yyyy) in a comma separated line: ");
                    chosen.setCampDates(dateListReader.readFromConsole());
                    break;
                case 4:
                    System.out.print("Enter new camp location: ");
                    chosen.setLocation(stringReader.readFromConsole());
                    break;
                case 5:
                    System.out.print("Enter new camp registration deadline (dd/mm/yyyy): ");
                    Date registrationDate = dateReader.readFromConsole();
                    if(registrationDate.before(new Date())) throw new CampException("Registration date has passed");
                    chosen.setRegistrationDeadline(registrationDate);
                    break;
                case 6:
                    System.out.print("Enter new camp total slots: ");
                    chosen.setTotalSlots(intReader.readFromConsole(1, Integer.MAX_VALUE));
                    break;
                case 7:
                    System.out.print("Enter new camp committee slots: ");
                    chosen.setCampCommitteeSlots(intReader.readFromConsole(1, 10));
                    break;
                case 8:
                    System.out.print("Enter new camp visibility (1 for visible, 0 for invisible): ");
                    int v = intReader.readFromConsole(0, 1);
                    if(v == 1){
                        chosen.show();
                    } else{
                        chosen.hide();
                    }
                    break;
                case 9:
                    System.out.print("Enter new faculty open to (1 for all, 2 for only your faculty): ");
                    String faculty = intReader.readFromConsole(1,2) == 1 ? "*" : userObject.getFaculty();
                    chosen.setFacultyOpenTo(faculty);
                    break;
            }
            System.out.println("Camp edited successfully");
        } catch(InputException | CampException e){
            System.out.println(e.getMessage());
        }
    }
}
