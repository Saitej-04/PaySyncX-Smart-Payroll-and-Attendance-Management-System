package dao;

import db.DBConnection;
import models.PayrollRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollDAO {

    public static boolean insert(PayrollRecord p) {
        String sql = "INSERT INTO payroll (emp_id, year, month, basic, allowances, deductions, net_salary) VALUES (?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getEmpId());
            ps.setInt(2, p.getYear());
            ps.setInt(3, p.getMonth());
            ps.setDouble(4, p.getBasic());
            ps.setDouble(5, p.getAllowances());
            ps.setDouble(6, p.getDeductions());
            ps.setDouble(7, p.getNetSalary());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static List<PayrollRecord> getAll() {
        List<PayrollRecord> list = new ArrayList<>();

        String sql = "SELECT * FROM payroll";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PayrollRecord p = new PayrollRecord();
                p.setId(rs.getInt("id"));
                p.setEmpId(rs.getInt("emp_id"));
                p.setYear(rs.getInt("year"));
                p.setMonth(rs.getInt("month"));
                p.setBasic(rs.getDouble("basic"));
                p.setAllowances(rs.getDouble("allowances"));
                p.setDeductions(rs.getDouble("deductions"));
                p.setNetSalary(rs.getDouble("net_salary"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public static List<PayrollRecord> getByEmployee(int empId) {
        List<PayrollRecord> list = new ArrayList<>();

        String sql = "SELECT * FROM payroll WHERE emp_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PayrollRecord p = new PayrollRecord();

                p.setId(rs.getInt("id"));
                p.setEmpId(rs.getInt("emp_id"));
                p.setYear(rs.getInt("year"));
                p.setMonth(rs.getInt("month"));
                p.setBasic(rs.getDouble("basic"));
                p.setAllowances(rs.getDouble("allowances"));
                p.setDeductions(rs.getDouble("deductions"));
                p.setNetSalary(rs.getDouble("net_salary"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
