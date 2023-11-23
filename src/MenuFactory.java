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
				new EditEnquiryMenu(),
				new RaiseEnquiryMenu(),
				new DeleteEnquiryMenu(),
				new WithdrawCampMenu(),
				new ViewBlockedDatesMenu()
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
				new ViewMyCommitteeCampMenu(),
				new ViewMySubmittedEnquiriesMenu(),
				new EditEnquiryMenu(),
				new RaiseEnquiryMenu(),
				new DeleteEnquiryMenu(),
				new WithdrawCampMenu(),
				new ViewBlockedDatesMenu(),
				new SubmitSuggestionMenu(),
				new EditSuggestionMenu(),
				new DeleteSuggestionMenu(),
				new ViewMySubmittedSuggestionsMenu(),
				new ViewAllEnquiriesMenu(),
				new ReplyToEnquiriesMenu(),
				new GenerateCampReportCommitteeMenu()
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
				new CreateCampMenu(),
				new ViewCreatedCampsStaffMenu(),
				new EditCampMenu(),
				new ViewAllEnquiriesStaffMenu(),
				new ReplyToEnquiryStaffMenu(),
				new ViewSuggestionMenu(),
				new ProcessSuggestionMenu(),
				new GenerateCampReportStaffMenu()
			)
		);
		userMenus.addAll(staffSpecificMenus);
		return userMenus;
	}

	public static ArrayList<IMenu> getAdminMenu(){
		ArrayList<IMenu> userMenus = getUserMenu();
		ArrayList<IMenu> adminSpecificMenu = new ArrayList<IMenu>(
			Arrays.asList(
				new ChangePasswordAdminMenu(),
				new UnlockUserMenu(),
				new LockUserMenu(),
				new CreateUserMenu()
			)
		);
		userMenus.addAll(adminSpecificMenu);
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
