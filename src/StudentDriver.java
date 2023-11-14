import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class StudentDriver {

    static void printMenuPage1(){
        System.out.println("1. Login");
        System.out.println("2. Return to main menu");
        System.out.println("3. Exit");
    }

    static void studentDriverPage1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;
        while (choice != 2 ){
            printMenuPage1();
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(br.readLine());
            switch(choice){
                case 1:
                    System.out.print("Enter your userID: ");
                    String userID = br.readLine();
                    System.out.print("Enter your password: ");
                    String password = br.readLine();
                    if (Registry.studentMap.containsKey(userID)){
                        try{
                            if(Registry.studentMap.get(userID).login(password)){
                                System.out.println("Login successful");
                                printMenuPage2(userID);
                                return;
                            }
                            else{
                                System.out.println("Invalid Credentials");
                            }
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }else{
                        System.out.println("Invalid Credentials");
                    }
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    static void printMenuPage2(String userID) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Student studentObject = Registry.studentMap.get(userID);
        System.out.println("--------------------------------------------------------");
        int choice = 0;
        while(true) {
            System.out.println("------------------------------------");
            System.out.println("Welcome " + userID);
            System.out.println("1. View all camps");
            System.out.println("2. Register for a camp");
            System.out.println("3. View my registered camps");
            System.out.println("4. View my submitted enquiries");
            System.out.println("5. Raise an enquiry");
            System.out.println("6. Edit an enquiry");
            System.out.println("7. Delete an enquiry");
            System.out.println("8. Withdraw from a camp");
            System.out.println("9. View my Profile");
            System.out.println("10. Change Password");
            System.out.println("11. Logout");
            System.out.print("\nEnter your choice: ");
            choice = Integer.parseInt(br.readLine());
            switch(choice){
                case 1:
                    for(Camp i:Filters.filterStudentCamps(studentObject.getFaculty())){
                        System.out.println("Name:" + i.getCampName());
                        System.out.println("Description: " + i.getDescription());
                        System.out.print("Dates of Camp: ");
                        for (Date date: i.campDates)
                            System.out.print(printDate(date) + ", ");
                        System.out.println();
                        System.out.println("Location: " + i.getLocation());
                        System.out.println("Registration Deadline: " + printDate(i.getRegistrationDeadline()));
                        System.out.println("Staff Member in charge: " + i.staffID);

                    }
                    break;

                case 2:
                    ArrayList<Camp> availablaCamps = Filters.filterStudentCamps(studentObject.getFaculty());
                    System.out.println("Choose the camp you want to register for: ");
                    for(int i=0;i<availablaCamps.size();i++){
                        System.out.println((i+1) + ". " + availablaCamps.get(i).getCampName());
                    }
                    System.out.print("Your choice: ");
                    int tempchoice = Integer.parseInt(br.readLine());
                    if(tempchoice <1 || tempchoice > availablaCamps.size()){
                        System.out.println("Invalid choice");
                        continue;
                    }
                    Camp chosen = availablaCamps.get(tempchoice-1);
                    studentObject.registerCamp(chosen);
                    break;

                case 3:
                    for (Camp registered : studentObject.registeredCamps()){
                        System.out.println(registered.getCampName());
                    }
                    break;

                case 4:
                    int counter = 0;
                    for (Enquiry enquiry : studentObject.submittedEnquiries()){
                        System.out.println(counter++);
                        System.out.println("Enquiry: " + enquiry.getStringValue());
                        System.out.println("Camp: " + Registry.campMap.get(enquiry.getCampID()).getCampName());
                        if(enquiry.isProcessed()){
                            System.out.println("Reply: " + enquiry.getReply());
                            System.out.println("Replied by: " + enquiry.getRepliedBy());
                        }
                        else{
                            System.out.println("Enquiry has not been replied to yet!");
                        }
                    }
                    break;

                case 5:
                    System.out.println("Choose the camp you want to raise an enquiry for: ");
                    ArrayList<Camp> camps = Filters.filterStudentCamps(studentObject.getFaculty());
                    for(int i=0;i<camps.size();i++){
                        System.out.println((i+1) + ". " + camps.get(i).getCampName());
                    }
                    System.out.print("Your choice: ");
                    tempchoice = Integer.parseInt(br.readLine());
                    if(tempchoice <1 || tempchoice > camps.size()){
                        System.out.println("Invalid choice");
                        continue;
                    }
                    Camp chosenCamp = camps.get(tempchoice-1);
                    System.out.print("Enter your enquiry: ");
                    String enquiry = br.readLine();
                    studentObject.raiseEnquiry(enquiry, chosenCamp.getCampID());
                    System.out.println("Enquiry raised successfully!");
                    break;

                case 6:
                    System.out.println("Choose the enquiry you want to edit: ");
                    ArrayList<Enquiry> enquiries = studentObject.getUnprocessedEnquiries();
                    if(enquiries.isEmpty()) {
                        System.out.println("You have no open enquiries");
                        break;
                    }
                    for(int i=0;i<enquiries.size();i++){
                        System.out.println((i+1) + ": " + enquiries.get(i).getStringValue());
                    }
                    System.out.print("Your choice: ");
                    int tchoice = Integer.parseInt(br.readLine());
                    if(tchoice < 1 || tchoice > enquiries.size()){
                        System.out.println("Invalid choice");
                        break;
                    }
                    System.out.print("Enter edit: ");
                    String tenquiry = br.readLine();
                    enquiries.get(tchoice-1).edit(tenquiry);
                    System.out.println("Enquiry edited successfully!");
                    break;
                case 7:
                    System.out.println("Choose the enquiry you want to delete: ");
                    enquiries = studentObject.getUnprocessedEnquiries();
                    if(enquiries.isEmpty()) {
                        System.out.println("You have no open enquiries");
                        break;
                    }
                    for(int i=0;i<enquiries.size();i++){
                        System.out.println((i+1) + ": " + enquiries.get(i).getStringValue());
                    }
                    System.out.print("Your choice: ");
                    tchoice = Integer.parseInt(br.readLine());
                    if(tchoice < 1 || tchoice > enquiries.size()){
                        System.out.println("Invalid choice");
                        break;
                    }
                    studentObject.deleteEnquiry(enquiries.get(tchoice-1).getID());
                    System.out.println("Enquiry deleted successfully!");
                    break;
                case 8:
                    System.out.println("Choose the camp you want to withdraw from: ");
                    camps = studentObject.registeredCamps();
                    for(int i=0;i<camps.size();i++){
                        System.out.println((i+1) + ": " + camps.get(i).getCampName());
                    }
                    System.out.print("Your choice: ");
                    tchoice = Integer.parseInt(br.readLine());
                    if(tchoice < 1 || tchoice > camps.size()){
                        System.out.println("Invalid choice");
                        break;
                    }
                    Camp c = camps.get(tchoice-1);
                    studentObject.withdrawFromCamp(c);
                    System.out.println("Withdrawn successfully");
                    break;
                case 9:
                    System.out.println();
                    studentObject.printProfile();
                    break;
                case 10:
                    System.out.print("Enter your new password: ");
                    String newPassword = br.readLine();
                    try {
                        studentObject.changePassword(newPassword);
                        System.out.println("Password changed successfully!\n");
                    }
                    catch (Exception e){
                        System.out.print(e.getMessage());
                    }
                    break;
            }
        }
    }

    static String printDate(Date d){
        return d.getDate() + "/" + d.getMonth() + "/" + (d.getYear() + 1900);
    }
}
