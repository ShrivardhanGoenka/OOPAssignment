import java.util.Date;

public class ViewMyRegisteredCampMenu implements IMenu {

	private Student studentObject;

	public ViewMyRegisteredCampMenu(Student studentObject) {
		this.studentObject = studentObject;
	}

	public void runMenu() {
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
