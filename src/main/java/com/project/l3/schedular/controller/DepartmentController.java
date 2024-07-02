package com.project.l3.schedular.controller;

import com.project.l3.schedular.model.Department;
import com.project.l3.schedular.repository.department.DepartmentRepository;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "departmentController", value = "/departments/*")
public class DepartmentController extends HttpServlet {

    // Use a stateless bean as a repository to handle business logic
    // It will be injected by the application container
    @EJB
    private DepartmentRepository repository;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getPathInfo();

        if (action == null) {
            listDepartments(request, response);
        } else {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteDepartment(request, response);
                    break;
                default:
                    listDepartments(request, response);
                    break;
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getPathInfo();

        if (action != null) {
            switch (action) {
                case "/store":
                    storeDepartment(request, response);
                    break;
                case "/update":
                    updateDepartment(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void listDepartments(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Department> departments = repository.getDepartments();

        request.setAttribute("departments", departments);

        request.getRequestDispatcher("/WEB-INF/views/departments/index.jsp")
                .forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/departments/create.jsp")
                .forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));

        Department department = repository.getDepartment(departmentId);

        request.setAttribute("department", department);

        request.getRequestDispatcher("/WEB-INF/views/departments/edit.jsp")
                .forward(request, response);
    }

    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));

        repository.deleteDepartment(departmentId);

        response.sendRedirect(request.getContextPath() + "/departments");
    }

    private void storeDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Department department = Department.fromRequest(request);

        repository.createDepartment(department);

        response.sendRedirect(request.getContextPath() + "/departments");
    }

    private void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Department department = Department.fromRequest(request);

        repository.updateDepartment(department);

        response.sendRedirect(request.getContextPath() + "/departments");
    }
}