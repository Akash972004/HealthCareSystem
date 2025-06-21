import java.sql.*;

public class Billing {
    public static void generateBill(int patientId, double amount) {
        try (Connection con = DBConnection.connect()) {
            String sql = "INSERT INTO Billing (patient_id, amount, billing_date) VALUES (?, ?, CURDATE())";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, patientId);
            ps.setDouble(2, amount);
            ps.executeUpdate();
            System.out.println("Bill generated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}