import java.sql.*;

public class Appointment {
    public static void book(int patientId, int doctorId, String date, String remarks) {
        try (Connection con = DBConnection.connect()) {
            String sql = "INSERT INTO Appointments (patient_id, doctor_id, appointment_date, remarks) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.setDate(3, Date.valueOf(date));
            ps.setString(4, remarks);
            ps.executeUpdate();
            System.out.println("Appointment booked.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}