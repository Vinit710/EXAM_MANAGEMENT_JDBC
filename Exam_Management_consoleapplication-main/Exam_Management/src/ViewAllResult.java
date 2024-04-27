import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewAllResult extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable resultTable;

    public ViewAllResult() {
        initComponents();
        setTitle("View All Results");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Create table model with columns "Username" and "Marks"
        DefaultTableModel model = new DefaultTableModel(new String[]{"Username", "Marks"}, 0);
        resultTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Fetch results from database and populate the table
        fetchResults(model);
    }

    private void fetchResults(DefaultTableModel model) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root")) {
            String query = "SELECT * FROM result";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                int marks = resultSet.getInt("marks");
                model.addRow(new Object[]{username, marks});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error occurred while fetching results: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewAllResult();
            }
        });
    }
}
