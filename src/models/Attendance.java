package models;

import java.sql.Date;
import java.sql.Time;

public class Attendance {

    private int id;
    private int empId;
    private Date attDate;
    private Time checkIn;
    private Time checkOut;
    private double hoursWorked;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public Date getAttDate() { return attDate; }
    public void setAttDate(Date attDate) { this.attDate = attDate; }

    public Time getCheckIn() { return checkIn; }
    public void setCheckIn(Time checkIn) { this.checkIn = checkIn; }

    public Time getCheckOut() { return checkOut; }
    public void setCheckOut(Time checkOut) { this.checkOut = checkOut; }

    public double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }
}
