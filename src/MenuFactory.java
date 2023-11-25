import java.util.ArrayList;
import java.util.Arrays;
/**
 * The {@code MenuFactory} class provides static methods to generate menus for different user based on roles such as students, committee members, staff, and admin.
 */
public class MenuFactory {
    /**
     * Returns list of menus specific to students.
     *
     * @return {@code ArrayList<IMenu>}
     */
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

    /**
     * Returns list of menus specific to camp committee.
     *
     * @return {@code ArrayList<IMenu>}
     */
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

    /**
     * Returns list of menus specific to staff.
     *
     * @return {@code ArrayList<IMenu>}
     */
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
				new GenerateCampReportStaffMenu(),
				new DeleteCampMenu()
			)
		);
		userMenus.addAll(staffSpecificMenus);
		return userMenus;
	}

    /**
     * Returns list of menus specific to admin.
     *
     * @return {@code ArrayList<IMenu>}
     */
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

    /**
     * Returns list of menus shared across all user.
     *
     * @return {@code ArrayList<IMenu>}
     */
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
