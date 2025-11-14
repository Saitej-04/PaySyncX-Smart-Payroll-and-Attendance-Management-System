package dao;

import db.DBConnection;
import models.Attendance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    public static List<Attendance> getAll() {
        List<Attendance> list = new ArrayList<>();

        String sql = "SELECT * FROM attendance ORDER BY att_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Attendance a = new Attendance();
                a.setId(rs.getInt("id"));
                a.setEmpId(rs.getInt("emp_id"));
                a.setAttDate(rs.getDate("att_date"));
                a.setCheckIn(rs.getTime("check_in"));
                a.setCheckOut(rs.getTime("check_out"));
                a.setHoursWorked(rs.getDouble("hours_worked"));

                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static boolean checkIn(int empId) {
        String sql = "INSERT INTO attendance (emp_id, att_date, check_in) VALUES (?, CURDATE(), CURTIME())";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkOut(int empId) {
        String sql = "UPDATE attendance SET check_out = CURTIME(), " +
                "hours_worked = TIMESTAMPDIFF(HOUR, check_in, CURTIME()) " +
                "WHERE emp_id = ? AND att_date = CURDATE() AND check_out IS NULL";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean insert(Attendance a) {
        String sql = "INSERT INTO attendance (emp_id, att_date, check_in, check_out, hours_worked) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getEmpId());
            ps.setDate(2, a.getAttDate());
            ps.setTime(3, a.getCheckIn());
            ps.setTime(4, a.getCheckOut());
            ps.setDouble(5, a.getHoursWorked());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
