public class UserLogoutMenu extends IMenu<User> {
	public void runMenu(User userObject) {
		try{
			userObject.logout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMenuDescription () {
		return "Logout";
	}
}
