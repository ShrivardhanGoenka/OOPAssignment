public class PrintProfileMenu extends IMenu<User> {

	public void runMenu(User studentObject) {
		System.out.println();
		studentObject.printProfile();
	}

	public String getMenuDescription () {
		return "View my Profile";
	}
}
