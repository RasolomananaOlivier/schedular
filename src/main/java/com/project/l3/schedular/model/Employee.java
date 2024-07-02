package com.project.l3.schedular.model;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id")
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public static Employee fromRequest(HttpServletRequest request) {
        Employee employee = new Employee();

        if(request.getParameter("id") != null) {
            employee.setId(Integer.parseInt(request.getParameter("id")));
        }

        employee.setLastName(request.getParameter("lastName"));
        employee.setFirstName(request.getParameter("firstName"));
        employee.setEmail(request.getParameter("email"));
        employee.setPhone(request.getParameter("phone"));

        return employee;
    }
}
