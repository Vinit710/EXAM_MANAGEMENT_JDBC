import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentLogin {

    public void initLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Student Login");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Validate the credentials using the validateCredentials method
        if (validateCredentials(username, password)) {
            System.out.println("Login successful! Welcome, " + username + ".");
            
            studentHome studentHome = new studentHome();
            studentHome.initComponents(username);
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }

        scanner.close();
    }

    public boolean validateCredentials(String username, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root")) {
            String query = "SELECT * FROM studentregister WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Return true if credentials are valid
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurs
        }
    }
}
