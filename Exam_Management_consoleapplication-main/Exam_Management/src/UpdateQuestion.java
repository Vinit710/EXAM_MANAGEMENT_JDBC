import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateQuestion extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idField, nameField, opt1Field, opt2Field, opt3Field, opt4Field, answerField;

    public UpdateQuestion() {
        initComponents();
        setTitle("Update Question");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void initComponents() {
        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        add(mainPanel);

        idField = new JTextField();
        nameField = new JTextField();
        opt1Field = new JTextField();
        opt2Field = new JTextField();
        opt3Field = new JTextField();
        opt4Field = new JTextField();
        answerField = new JTextField();

        mainPanel.add(new JLabel("Enter question ID:"));
        mainPanel.add(idField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchQuestion();
            }
        });
        mainPanel.add(searchButton);

        mainPanel.add(new JLabel("Name:"));
        mainPanel.add(nameField);
        mainPanel.add(new JLabel("Option 1:"));
        mainPanel.add(opt1Field);
        mainPanel.add(new JLabel("Option 2:"));
        mainPanel.add(opt2Field);
        mainPanel.add(new JLabel("Option 3:"));
        mainPanel.add(opt3Field);
        mainPanel.add(new JLabel("Option 4:"));
        mainPanel.add(opt4Field);
        mainPanel.add(new JLabel("Answer:"));
        mainPanel.add(answerField);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateQuestion();
            }
        });
        mainPanel.add(updateButton);
    }

    private void searchQuestion() {
        String id = idField.getText();

        try (Connection connection = getConnection()) {
            String selectQuery = "SELECT * FROM question WHERE id = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                nameField.setText(resultSet.getString("name"));
                opt1Field.setText(resultSet.getString("opt1"));
                opt2Field.setText(resultSet.getString("opt2"));
                opt3Field.setText(resultSet.getString("opt3"));
                opt4Field.setText(resultSet.getString("opt4"));
                answerField.setText(resultSet.getString("answer"));
            } else {
                JOptionPane.showMessageDialog(this, "Question with ID " + id + " not found.");
            }

            resultSet.close();
            selectStatement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred while searching question: " + ex.getMessage());
        }
    }

    private void updateQuestion() {
        String id = idField.getText();
        String name = nameField.getText();
        String opt1 = opt1Field.getText();
        String opt2 = opt2Field.getText();
        String opt3 = opt3Field.getText();
        String opt4 = opt4Field.getText();
        String answer = answerField.getText();

        try (Connection connection = getConnection()) {
            String updateQuery = "UPDATE question SET name = ?, opt1 = ?, opt2 = ?, opt3 = ?, opt4 = ?, answer = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, name);
            updateStatement.setString(2, opt1);
            updateStatement.setString(3, opt2);
            updateStatement.setString(4, opt3);
            updateStatement.setString(5, opt4);
            updateStatement.setString(6, answer);
            updateStatement.setString(7, id);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Question updated successfully!");
                // Close the UpdateQuestion window
                dispose();
                // Open a new instance of the adminHome window
                new adminHome().initComponents();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update question.");
            }

            updateStatement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred while updating question: " + ex.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UpdateQuestion();
            }
        });
    }
}
