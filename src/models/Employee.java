package models;

import java.sql.Date;

public class Employee {

    private int empId;
    private String name;
    private String role;
    private double basicSalary;
    private String email;
    private String phone;
    private Date dateJoined;
    private boolean active;

    // Getters
    public int getEmpId() { return empId; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public double getBasicSalary() { return basicSalary; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Date getDateJoined() { return dateJoined; }
    public boolean isActive() { return active; }

    // Setters
    public void setEmpId(int empId) { this.empId = empId; }
    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDateJoined(Date dateJoined) { this.dateJoined = dateJoined; }
    public void setActive(boolean active) { this.active = active; }
}
