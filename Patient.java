import java.sql.*;

public class Patient {
    public static void register(String name, String gender, int age, String phone, String address) {
        try (Connection con = DBConnection.connect()) {
            String sql = "INSERT INTO Patients (name, gender, age, phone, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, gender);
            ps.setInt(3, age);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.executeUpdate();
            System.out.println("Patient registered.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}