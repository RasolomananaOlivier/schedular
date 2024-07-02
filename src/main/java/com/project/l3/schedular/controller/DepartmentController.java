package com.project.l3.schedular.controller;

import java.io.*;
import java.util.List;

import com.project.l3.schedular.model.Department;
import com.project.l3.schedular.repository.department.DepartmentRepository;
import jakarta.ejb.EJB;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "departmentController", value = "/departments")
public class DepartmentController extends HttpServlet {

    // Use a stateless bean as a repository to handle business logic
    // It will be injected by the application container
    @EJB
    private DepartmentRepository repository;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        List<Department> departments = repository.getDepartments();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + departments.size() + "</h1>");
        out.println("</body></html>");
    }
}