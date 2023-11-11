import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
public class StudentDriver {

    static void printMenuPage1(){
        System.out.println("1. Login");
        System.out.println("2. Return to main menu");
        System.out.println("3. Exit");
    }

    static void studentDriverPage1(){
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 2 ){
            printMenuPage1();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter your userID: ");
                    String userID = sc.next();
                    System.out.print("Enter your password: ");
                    String password = sc.next();
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

    static void printMenuPage2(String userID){
        Student studentObject = Registry.studentMap.get(userID);
        System.out.println("--------------------------------------------------------");
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while(true) {
            System.out.println("------------------------------------");
            System.out.println("Welcome " + userID);
            System.out.println("1. View all camps");
            System.out.println("2. Register for a camp");
            System.out.println("3. View my registered camps");
            System.out.println("6. View my Profile");
            System.out.println("7. Change Password");
            System.out.println("8. Logout");
            System.out.print("\nEnter your choice: ");
            choice = sc.nextInt();
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
                    int tempchoice = sc.nextInt();
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
                    break;

                case 5:
                    break;

                case 6:
                    System.out.println();
                    studentObject.printProfile();
                    break;
                case 7:
                    System.out.print("Enter your new password: ");
                    String newPassword = sc.next();
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
