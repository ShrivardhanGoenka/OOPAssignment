import java.util.Scanner;
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
                                //studentDriverPage2(userID);
                                System.out.println("Login successful");
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

    static void printMenuPage2(){
        System.out.println("1. Register for a camp");
        System.out.println("2. View registered camps");
        System.out.println("3. Logout");
    }


}
