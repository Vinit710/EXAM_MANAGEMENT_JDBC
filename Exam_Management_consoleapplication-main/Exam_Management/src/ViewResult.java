import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ViewResult {

    public void viewResults(String username) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root")) {
            // Fetch all attempted questions and answers by the user
            String attemptedQuery = "SELECT * FROM studentresult WHERE username = ?";
            PreparedStatement attemptedStatement = connection.prepareStatement(attemptedQuery);
            attemptedStatement.setString(1, username);
            ResultSet attemptedResultSet = attemptedStatement.executeQuery();

            // Map to store correct answers for each question
            Map<String, String> correctAnswers = new HashMap<>();
            String questionsQuery = "SELECT id, answer FROM question";
            PreparedStatement questionsStatement = connection.prepareStatement(questionsQuery);
            ResultSet questionsResultSet = questionsStatement.executeQuery();
            while (questionsResultSet.next()) {
                String questionId = questionsResultSet.getString("id");
                String correctAnswer = questionsResultSet.getString("answer");
                correctAnswers.put(questionId, correctAnswer);
            }

            // Calculate marks based on correct answers
            int marks = 0;
            while (attemptedResultSet.next()) {
                String questionId = attemptedResultSet.getString("question_id");
                String userAnswer = attemptedResultSet.getString("answer");
                String correctAnswer = correctAnswers.get(questionId);
                if (correctAnswer != null && correctAnswer.equals(userAnswer)) {
                    marks++;
                }
            }

            // Store the final marks in the result table
            String updateResultQuery = "INSERT INTO result (username, marks) VALUES (?, ?)";
            PreparedStatement updateResultStatement = connection.prepareStatement(updateResultQuery);
            updateResultStatement.setString(1, username);
            updateResultStatement.setInt(2, marks);
            updateResultStatement.executeUpdate();

            System.out.println("Results for " + username + ":");
            System.out.println("Marks Obtained: " + marks);
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching or updating results: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username to view results: ");
        String username = scanner.nextLine();

        ViewResult viewResult = new ViewResult();
        viewResult.viewResults(username);

        scanner.close();
    }
}
