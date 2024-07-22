package com.project.l3.schedular.model;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String status;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusLabel() {
        switch (status) {
            case "invited":
                return "Invité";
            case "present":
                return "Présent";
            case "absent":
                return "Absent";
            default:
                return status;
        }
    }

    public String getStatusColor() {
        switch (status) {
            case "invited":
                return "#3b82f6";
            case "present":
                return "#22c55e";
            case "absent":
                return "#ef4444";
            default:
                return "#e2e8f0 ";
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
