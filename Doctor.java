import java.sql.*;

public class Doctor {
    public static void addDoctor(String name, String specialization, String phone) {
        try (Connection con = DBConnection.connect()) {
            String sql = "INSERT INTO Doctors (name, specialization, phone) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, specialization);
            ps.setString(3, phone);
            ps.executeUpdate();
            System.out.println("Doctor added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}