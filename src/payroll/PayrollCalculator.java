package payroll;

import dao.EmployeeDAO;
import models.Employee;
import models.PayrollRecord;

public class PayrollCalculator {

    public static PayrollRecord calculate(int empId) {

        Employee e = EmployeeDAO.getById(empId);   // FIXED

        if (e == null) return null;

        PayrollRecord pr = new PayrollRecord();
        pr.setEmpId(e.getEmpId());                 // FIXED

        double basic = e.getBasicSalary();
        double allowance = basic * 0.10;
        double deduction = basic * 0.05;

        pr.setBasic(basic);
        pr.setAllowances(allowance);
        pr.setDeductions(deduction);
        pr.setNetSalary(basic + allowance - deduction);

        return pr;
    }
}
