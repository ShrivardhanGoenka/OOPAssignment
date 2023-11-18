import java.util.ArrayList;
import java.util.Arrays;

public class MenuFactory {
	public static ArrayList<IMenu> getStudentMenu() {
		ArrayList<IMenu> userMenus = getUserMenu();
		ArrayList<IMenu> studentSpecificMenus = new ArrayList<IMenu>(
			Arrays.asList(
				new ViewCampStudentMenu(),
				new RegisterCampStudentMenu(),
				new ViewMyRegisteredCampMenu(),
				new ViewMySubmittedEnquiriesMenu(),
				new RaiseEnquiryMenu(),
				new DeleteEnquiryMenu(),
				new WithdrawCampMenu()
			)	
		);
		userMenus.addAll(studentSpecificMenus);
		return userMenus;
	};

	public static ArrayList<IMenu> getCommitteeMenu() {
		ArrayList<IMenu> userMenus = getUserMenu();
		ArrayList<IMenu> committeeSpecificMenus = new ArrayList<IMenu>(
			Arrays.asList(
				new ViewCampStudentMenu(),
				new RegisterCampCommitteeMenu(),
				new ViewMyRegisteredCampMenu(),
				new ViewMySubmittedEnquiriesMenu(),
				new RaiseEnquiryMenu(),
				new DeleteEnquiryMenu(),
				new WithdrawCampMenu(),
				new SubmitSuggestionMenu(), // checked
				new EditSuggestionMenu(), // checked
				new DeleteSuggestionMenu(), //checked
				new ViewMySubmittedSuggestionsMenu(), // checked
				new ViewAllEnquiriesMenu(), //checked
				new ReplyToEnquiriesMenu(), //checked
				new GenerateCampReportMenu() // not yet
			)
		);
		userMenus.addAll(committeeSpecificMenus);
		return userMenus;
	};
	public static ArrayList<IMenu> getStaffMenu(){
		ArrayList<IMenu> userMenus = getUserMenu();
		ArrayList<IMenu> staffSpecificMenus = new ArrayList<IMenu>(
			Arrays.asList(
					new ViewCampStaffMenu(),
					new ViewCreatedCampsStaffMenu()
			)
		);
		userMenus.addAll(staffSpecificMenus);
		return userMenus;
	}

	public static ArrayList<IMenu> getUserMenu() {
		ArrayList<IMenu> userProfileMenus = new ArrayList<IMenu>(
			Arrays.asList(
				new ChangePasswordMenu(),
				new PrintProfileMenu(),  // might have to separate the printprofile for user/student/committee/staff
				new UserLogoutMenu()
			)
		);
		return userProfileMenus;
	};
}
