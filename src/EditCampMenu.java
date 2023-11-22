import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
public class EditCampMenu extends IMenu<Staff> {

    @Override
    public String getMenuDescription() {
        return "Edit Camp";
    }

    @Override
    public void runMenu(Staff userObject) throws CampException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("<------------------Choose the camp you want to edit------------------>");
        ArrayList<Camp> camps = userObject.createdCamps();
        for(int i=0;i<camps.size();i++){
            System.out.println((i+1) + ". " + camps.get(i).getCampName());
        }
        System.out.print("Your choice: ");
        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());
            if(choice <1 || choice > camps.size()){
                System.out.println("Invalid choice");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid choice");
            return;
        }
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
        System.out.println("9. Camp Open to");
        System.out.println("10. Exit");
        System.out.print("Your choice: ");
        try {
            choice = Integer.parseInt(br.readLine());
            if(choice <1 || choice > 10){
                System.out.println("Invalid choice");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid choice");
            return;
        }
        switch(choice){
            case 1:
                System.out.print("Enter new camp name: ");
                String name = "";
                try {
                    name = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                chosen.setCampName(name);
                break;
            case 2:
                System.out.print("Enter new camp description: ");
                String description = "";
                try {
                    description = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                chosen.setDescription(description);
                break;
            case 3:
                System.out.print("Enter new camp dates (dd/mm/yyyy) in a comma separated line: ");
                String dates = "";
                try {
                    dates = br.readLine();
                    ArrayList<Date> newdates = DateInputFormatter.formatDateInput(dates);
                    chosen.setCampDates(newdates);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                } catch(ParseException e){
                    System.out.println("Invalid date format");
                    return;
                }
                break;
            case 4:
                System.out.print("Enter new camp location: ");
                String location = "";
                try {
                    location = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                chosen.setLocation(location);
                break;
            case 5:
                System.out.print("Enter new camp registration deadline (dd/mm/yyyy): ");
                String deadline = "";
                try {
                    deadline = br.readLine();
                    Date newdeadline = DateInputFormatter.formatDateInput(deadline).get(0);
                    chosen.setRegistrationDeadline(newdeadline);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                } catch(ParseException e){
                    System.out.println("Invalid date format");
                    return;
                }
                break;
            case 6:
                System.out.print("Enter new camp total slots: ");
                int totalslots = 0;
                try {
                    totalslots = Integer.parseInt(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                chosen.setTotalSlots(totalslots);
                break;
            case 7:
                System.out.print("Enter new camp committee slots: ");
                int committeeslots = 0;
                try {
                    committeeslots = Integer.parseInt(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                chosen.setCampCommitteeSlots(committeeslots);
                break;
        }
    }
}
