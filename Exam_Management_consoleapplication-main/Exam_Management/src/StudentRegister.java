import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentRegister {
    public void initRegister() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Student Registration");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Register the student using the register method
        if (register(username, password)) {
            System.out.println("Registration successful! Welcome, " + username + ".");
            // Call StudentDetails class to fill out personal details
            studentHome studentHome = new studentHome();
            studentHome.initComponents(username);
            
            StudentLogin studentLogin = new StudentLogin();
            studentLogin.initLogin();
               
        } else {
            System.out.println("Registration failed. Please try again.");
        }

        scanner.close();
    }

    public boolean register(String username, String password) {
        // Connect to the database and insert the new student's details
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root")) {
            String query = "INSERT INTO studentregister (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0; // Return true if registration is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurs
        }
    }
}
