package com.project.l3.schedular.controller;

import com.project.l3.schedular.model.Employee;
import com.project.l3.schedular.repository.employee.EmployeeRepository;
import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EmployeeController {

    // Use a stateless bean as a repository to handle business logic
    // It will be injected by the application container
    @EJB
    private EmployeeRepository repository;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        List<Employee> employees = repository.getEmployees();

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + employees.size() + "</h1>");
        out.println("</body></html>");
    }
}
