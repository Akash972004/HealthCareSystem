// Enhanced HealthCareGUI with data tables and auto-refresh
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class HealthCareGUI_Enhanced extends JFrame {
    private Connection conn;

    public HealthCareGUI_Enhanced() {
        setTitle("🏥 Health Care Management System");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        conn = connectToDatabase();

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Register Patient", createPatientPanel());
        tabs.addTab("Add Doctor", createDoctorPanel());
        tabs.addTab("Book Appointment", createAppointmentPanel());
        tabs.addTab("Generate Bill", createBillingPanel());

        add(tabs);
    }

    private Connection connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/hcms", "root", "Akash@2004");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }

    private JPanel createPatientPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(5, 2));

        JTextField name = new JTextField(), age = new JTextField(), gender = new JTextField(), contact = new JTextField();
        JButton add = new JButton("Register Patient");

        form.add(new JLabel("Name:")); form.add(name);
        form.add(new JLabel("Age:")); form.add(age);
        form.add(new JLabel("Gender:")); form.add(gender);
        form.add(new JLabel("Contact:")); form.add(contact);
        form.add(new JLabel()); form.add(add);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        add.addActionListener(e -> {
            try {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO Patients (name, age, gender, contact) VALUES (?, ?, ?, ?)");
                stmt.setString(1, name.getText());
                stmt.setInt(2, Integer.parseInt(age.getText()));
                stmt.setString(3, gender.getText());
                stmt.setString(4, contact.getText());
                stmt.executeUpdate();
                loadTable(model, "SELECT * FROM Patients");
                name.setText(""); age.setText(""); gender.setText(""); contact.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        loadTable(model, "SELECT * FROM Patients");
        panel.add(form, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createDoctorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(4, 2));

        JTextField name = new JTextField(), spec = new JTextField(), contact = new JTextField();
        JButton add = new JButton("Add Doctor");

        form.add(new JLabel("Name:")); form.add(name);
        form.add(new JLabel("Specialty:")); form.add(spec);
        form.add(new JLabel("Contact:")); form.add(contact);
        form.add(new JLabel()); form.add(add);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        add.addActionListener(e -> {
            try {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO Doctors (name, specialty, contact) VALUES (?, ?, ?)");
                stmt.setString(1, name.getText());
                stmt.setString(2, spec.getText());
                stmt.setString(3, contact.getText());
                stmt.executeUpdate();
                loadTable(model, "SELECT * FROM Doctors");
                name.setText(""); spec.setText(""); contact.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        loadTable(model, "SELECT * FROM Doctors");
        panel.add(form, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createAppointmentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(4, 2));

        JTextField pid = new JTextField(), did = new JTextField(), date = new JTextField();
        JButton add = new JButton("Book Appointment");

        form.add(new JLabel("Patient ID:")); form.add(pid);
        form.add(new JLabel("Doctor ID:")); form.add(did);
        form.add(new JLabel("Date (YYYY-MM-DD):")); form.add(date);
        form.add(new JLabel()); form.add(add);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        add.addActionListener(e -> {
            try {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO Appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)");
                stmt.setInt(1, Integer.parseInt(pid.getText()));
                stmt.setInt(2, Integer.parseInt(did.getText()));
                stmt.setString(3, date.getText());
                stmt.executeUpdate();
                loadTable(model, "SELECT * FROM Appointments");
                pid.setText(""); did.setText(""); date.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        loadTable(model, "SELECT * FROM Appointments");
        panel.add(form, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createBillingPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(3, 2));

        JTextField pid = new JTextField(), amount = new JTextField();
        JButton add = new JButton("Generate Bill");

        form.add(new JLabel("Patient ID:")); form.add(pid);
        form.add(new JLabel("Amount:")); form.add(amount);
        form.add(new JLabel()); form.add(add);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        add.addActionListener(e -> {
            try {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO Billing (patient_id, amount, billing_date, paid) VALUES (?, ?, CURDATE(), false)");
                stmt.setInt(1, Integer.parseInt(pid.getText()));
                stmt.setDouble(2, Double.parseDouble(amount.getText()));
                stmt.executeUpdate();
                loadTable(model, "SELECT * FROM Billing");
                pid.setText(""); amount.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        loadTable(model, "SELECT * FROM Billing");
        panel.add(form, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private void loadTable(DefaultTableModel model, String query) {
        try {
            model.setRowCount(0);
            model.setColumnCount(0);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                model.addColumn(meta.getColumnName(i));
            }
            while (rs.next()) {
                Object[] row = new Object[meta.getColumnCount()];
                for (int i = 0; i < meta.getColumnCount(); i++) {
                    row[i] = rs.getObject(i + 1);
                }
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Load Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HealthCareGUI_Enhanced().setVisible(true));
    }
}