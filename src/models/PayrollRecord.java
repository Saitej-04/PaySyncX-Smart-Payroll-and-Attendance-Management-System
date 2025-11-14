package models;

public class PayrollRecord {
    private int id;
    private int empId;
    private int year;
    private int month;
    private double basic;
    private double allowances;
    private double deductions;
    private double netSalary;

    public PayrollRecord() {}

    public PayrollRecord(int id, int empId, int year, int month,
                         double basic, double allowances, double deductions, double netSalary) {
        this.id = id;
        this.empId = empId;
        this.year = year;
        this.month = month;
        this.basic = basic;
        this.allowances = allowances;
        this.deductions = deductions;
        this.netSalary = netSalary;
    }

    // Getters & Setters
    public int getId() { return id; }
    public int getEmpId() { return empId; }
    public int getYear() { return year; }
    public int getMonth() { return month; }
    public double getBasic() { return basic; }
    public double getAllowances() { return allowances; }
    public double getDeductions() { return deductions; }
    public double getNetSalary() { return netSalary; }

    public void setId(int id) { this.id = id; }
    public void setEmpId(int empId) { this.empId = empId; }
    public void setYear(int year) { this.year = year; }
    public void setMonth(int month) { this.month = month; }
    public void setBasic(double basic) { this.basic = basic; }
    public void setAllowances(double allowances) { this.allowances = allowances; }
    public void setDeductions(double deductions) { this.deductions = deductions; }
    public void setNetSalary(double netSalary) { this.netSalary = netSalary; }
}
