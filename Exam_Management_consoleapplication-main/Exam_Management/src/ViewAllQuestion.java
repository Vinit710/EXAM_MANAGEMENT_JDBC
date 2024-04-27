import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAllQuestion extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewAllQuestion() {
        initComponents();
        setTitle("View All Questions");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Table to display questions
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Fetch questions from the database and populate the table
        fetchQuestions(table);
    }

    private void fetchQuestions(JTable table) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root");

            // SQL query to retrieve all questions
            String query = "SELECT * FROM question";
            preparedStatement = connection.prepareStatement(query);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Create table model
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Option 1");
            model.addColumn("Option 2");
            model.addColumn("Option 3");
            model.addColumn("Option 4");
            model.addColumn("Answer");

            // Populate table model with question data
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String opt1 = resultSet.getString("opt1");
                String opt2 = resultSet.getString("opt2");
                String opt3 = resultSet.getString("opt3");
                String opt4 = resultSet.getString("opt4");
                String answer = resultSet.getString("answer");

                model.addRow(new String[]{id, name, opt1, opt2, opt3, opt4, answer});
            }

            // Set table model to the JTable
            table.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error occurred while fetching questions: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error occurred while closing resources: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewAllQuestion();
            }
        });
    }
}
