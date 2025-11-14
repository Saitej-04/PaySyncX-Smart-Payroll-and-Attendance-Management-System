package dao;

import models.Employee;
import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // GET ALL EMPLOYEES
    public static List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();

        String sql = "SELECT * FROM employees";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee e = new Employee();
                e.setEmpId(rs.getInt("emp_id"));
                e.setName(rs.getString("name"));
                e.setRole(rs.getString("role"));
                e.setBasicSalary(rs.getDouble("basic_salary"));
                e.setEmail(rs.getString("email"));
                e.setPhone(rs.getString("phone"));
                e.setDateJoined(rs.getDate("date_joined"));
                e.setActive(rs.getBoolean("active"));
                list.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // GET an employee by ID
    public static Employee getById(int id) {
        String sql = "SELECT * FROM employees WHERE emp_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Employee e = new Employee();
                e.setEmpId(rs.getInt("emp_id"));
                e.setName(rs.getString("name"));
                e.setRole(rs.getString("role"));
                e.setBasicSalary(rs.getDouble("basic_salary"));
                e.setEmail(rs.getString("email"));
                e.setPhone(rs.getString("phone"));
                e.setDateJoined(rs.getDate("date_joined"));
                e.setActive(rs.getBoolean("active"));
                return e;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // INSERT NEW EMPLOYEE
    public static boolean insert(Employee emp) {
        String sql = "INSERT INTO employees (name, role, basic_salary, email, phone, date_joined, active) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getRole());
            ps.setDouble(3, emp.getBasicSalary());
            ps.setString(4, emp.getEmail());
            ps.setString(5, emp.getPhone());
            ps.setDate(6, emp.getDateJoined());
            ps.setBoolean(7, emp.isActive());

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // DELETE EMPLOYEE BY ID
    public static boolean delete(int id) {
        String sql = "DELETE FROM employees WHERE emp_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
