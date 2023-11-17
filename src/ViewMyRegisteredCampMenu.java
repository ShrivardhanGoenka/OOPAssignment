import java.util.Date;

public class ViewMyRegisteredCampMenu extends IMenu<Student> {

	public void runMenu(Student studentObject) {
                    for (Camp registered : studentObject.registeredCamps()){
                        System.out.println(registered.getCampName());
                    }
	}

	public String getMenuDescription() {
		return "View my registered camps";
	} 

	static String printDate(Date d){
		return d.getDate() + "/" + d.getMonth() + "/" + (d.getYear() + 1900);
	}
}
