import java.util.Date;

public class ViewCampStudentMenu implements IMenu {

	private Student studentObject;

	public ViewCampStudentMenu(Student studentObject) {
		this.studentObject = studentObject;
	}

	public void runMenu() {
	    System.out.println("-------------<List of Avaiable Camp>-------------");
	    for(Camp campIterator:Filters.filterStudentCamps(studentObject.getFaculty())){
		System.out.println("Name:" + campIterator.getCampName());
		System.out.println("Description: " + campIterator.getDescription());
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

	public String getMenuDescription() {
		return "View all camps";
	} 

	static String printDate(Date d){
		return d.getDate() + "/" + d.getMonth() + "/" + (d.getYear() + 1900);
	}
}
