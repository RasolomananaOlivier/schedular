package com.project.l3.schedular.model;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public static Department fromRequest(HttpServletRequest request) {
        Department department = new Department();

        if(request.getParameter("id") != null) {
            department.setId(Integer.parseInt(request.getParameter("id")));
        }

        department.setName(request.getParameter("name"));

        return department;
    }
}
