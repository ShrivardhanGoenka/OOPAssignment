public class PrintProfileMenu implements IMenu {
	private Student studentObject;

	public PrintProfileMenu(Student studentObject) {
		this.studentObject = studentObject;
	}

	public void runMenu() {
		System.out.println();
		studentObject.printProfile();
	}

	public String getMenuDescription () {
		return "View my Profile";
	}
}
