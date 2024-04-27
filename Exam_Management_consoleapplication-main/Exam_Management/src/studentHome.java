import java.util.Scanner;

public class studentHome {

    Scanner scanner = new Scanner(System.in);

    public void initComponents(String username) {
    	 System.out.println("Welcome to the Student Home, " + username + "!");
        System.out.println("1. Take Quiz");
        System.out.println("2. View Quiz Results");
        
        System.out.println("3. Add details");
        System.out.println("4. Exit");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
            	System.out.println("You selected: Take Quiz");
                AttemptTest attemptTest = new AttemptTest();
                attemptTest.attemptQuiz(username);
                break;
            case 2:
                System.out.println("You selected: View Quiz Results");
                ViewResult viewResult = new ViewResult();
                viewResult.viewResults(username);
                break;

            case 3:
            	System.out.println("You selected: Add details");
                StudentDetails studentDetails = new StudentDetails();
                studentDetails.initDetails(username);
                break;
            case 4:
                System.out.println("Exiting application...");
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }
}

