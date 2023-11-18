public abstract class IMenu<T extends User>{
	public abstract String getMenuDescription();
	public abstract void runMenu(T userObject) throws CampException;

	public final boolean isUserLogin(T userObject){
		return userObject.isLoggedIn();
	}
}
