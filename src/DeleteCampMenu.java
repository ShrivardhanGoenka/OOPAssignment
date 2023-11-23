import java.util.ArrayList;

public class DeleteCampMenu extends IMenu<Staff>{

    @Override
    public String getMenuDescription() {
        return "Delete Camp";
    }

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
            return;
        }
    }
}
