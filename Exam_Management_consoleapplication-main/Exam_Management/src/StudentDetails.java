import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDetails {
    public void initDetails(String username) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please fill out your personal details:");
        System.out.print("Roll Number: ");
        String rollNumber = scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Father's Name: ");
        String fatherName = scanner.nextLine();

        System.out.print("Surname: ");
        String surname = scanner.nextLine();

        System.out.print("Mother's Name: ");
        String motherName = scanner.nextLine();

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Contact Number: ");
        String contactNumber = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Parents Contact Number: ");
        String parentsContactNumber = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        // Inserting data into the database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root")) {
            String query = "INSERT INTO studentdata (roll_no, name, father_name, surname, mother_name, gender, contact_number, email, parent_contact_number, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rollNumber);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, fatherName);
            preparedStatement.setString(4, surname);
            preparedStatement.setString(5, motherName);
            preparedStatement.setString(6, gender);
            preparedStatement.setString(7, contactNumber);
            preparedStatement.setString(8, email);
            preparedStatement.setString(9, parentsContactNumber);
            preparedStatement.setString(10, address);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Details inserted successfully!");
            } else {
                System.out.println("Failed to insert details.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
