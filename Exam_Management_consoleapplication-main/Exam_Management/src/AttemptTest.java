import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AttemptTest {

    Scanner scanner = new Scanner(System.in);

    public Connection getConnection() throws SQLException {
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root");
        return connect;
    }

    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM question");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String opt1 = resultSet.getString("opt1");
                String opt2 = resultSet.getString("opt2");
                String opt3 = resultSet.getString("opt3");
                String opt4 = resultSet.getString("opt4");
                String answer = resultSet.getString("answer");

                Question question = new Question(id, name, opt1, opt2, opt3, opt4, answer);
                questions.add(question);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching questions: " + e.getMessage());
        }
        return questions;
    }

    public void attemptQuiz(String username) {
        List<Question> questions = getQuestions();
        if (questions.isEmpty()) {
            System.out.println("No questions available to attempt the quiz.");
            return;
        }

        // Iterate through questions and store student's answers
        try {
            Connection connection = getConnection();
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO studentResult (username, question_id, answer) VALUES (?, ?, ?)");
            for (Question question : questions) {
                System.out.println(question.getName());
                System.out.println("Options:");
                System.out.println("1. " + question.getOpt1());
                System.out.println("2. " + question.getOpt2());
                System.out.println("3. " + question.getOpt3());
                System.out.println("4. " + question.getOpt4());
                System.out.print("Enter your choice (1-4): ");
                int userChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                insertStatement.setString(1, username);
                insertStatement.setString(2, question.getId());
                insertStatement.setInt(3, userChoice);
                insertStatement.executeUpdate();
            } 
            insertStatement.close();
            connection.close();
            System.out.println("Quiz completed. Your responses have been recorded.");
        } catch (SQLException e) {
            System.out.println("Error occurred while inserting student result: " + e.getMessage());
        }

        System.out.println("Quiz completed. Your responses have been recorded.");
    }
}
