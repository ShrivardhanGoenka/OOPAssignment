import java.util.Date;

public class ViewCampStaffMenu extends IMenu<Staff>{

    @Override
    public String getMenuDescription() {
        return "View all camps";
    }

    @Override
    public void runMenu(Staff userObject) {
        System.out.println("-------------<List of Camps>-------------");
        for(Camp campIterator: Registry.campMap.values()){
            System.out.println("Name:" + campIterator.getCampName());
            System.out.println("Description: " + campIterator.getDescription());
            System.out.println("Faculty open to: " + campIterator.getFacultyOpenTo());
            System.out.println("Visibile to students: " + campIterator.isVisible());
            System.out.print("Dates of Camp: ");
            for (Date date: campIterator.campDates)
                System.out.print(printDate(date) + ", ");
            System.out.println();
            System.out.println("Location: " + campIterator.getLocation());
            System.out.println("Registration Deadline: " + printDate(campIterator.getRegistrationDeadline()));
            System.out.println("Staff Member in charge: " + campIterator.staffID);
            System.out.println("-----------------------------------------------");
        }
    }
    String printDate(Date d){
        return d.getDate() + "/" + d.getMonth() + "/" + (d.getYear() + 1900);
    }
}
