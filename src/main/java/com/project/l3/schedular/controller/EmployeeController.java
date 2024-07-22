package com.project.l3.schedular.controller;

import com.project.l3.schedular.model.Employee;
import com.project.l3.schedular.repository.department.DepartmentRepository;
import com.project.l3.schedular.repository.department.DepartmentRepositoryImpl;
import com.project.l3.schedular.repository.employee.EmployeeRepository;
import com.project.l3.schedular.repository.employee.EmployeeRepositoryImpl;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "employeeController", value = "/employees/*")
public class EmployeeController extends HttpServlet {
    // Use a stateless bean as a repository to handle business logic
    // It will be injected by the application container
    @EJB
    private EmployeeRepository repository = new EmployeeRepositoryImpl();
    @EJB
    private DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getPathInfo();

        if (action == null) {
            listResources(request, response);
        } else {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteResource(request, response);
                    break;
                default:
                    listResources(request, response);
                    break;
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getPathInfo();

        if (action != null) {
            switch (action) {
                case "/store":
                    storeResource(request, response);
                    break;
                case "/update":
                    updateResource(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void listResources(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setAttribute("employees", repository.getEmployees());

        request.getRequestDispatcher("/WEB-INF/views/employees/index.jsp")
                .forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("departments", departmentRepository.getDepartments());

        request.getRequestDispatcher("/WEB-INF/views/employees/create.jsp")
                .forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        Employee employee = repository.getEmployee(employeeId);
        request.setAttribute("departments", departmentRepository.getDepartments());

        request.setAttribute("employee", employee);

        request.getRequestDispatcher("/WEB-INF/views/employees/edit.jsp")
                .forward(request, response);
    }

    private void deleteResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        repository.deleteEmployee(employeeId);

        response.sendRedirect(request.getContextPath() + "/employees");
    }

    private void storeResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Employee employee = Employee.fromRequest(request);

        int departmentId = Integer.parseInt(request.getParameter("departmentId"));

        repository.createEmployee(employee, departmentId);

        response.sendRedirect(request.getContextPath() + "/employees");
    }

    private void updateResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Employee employee = Employee.fromRequest(request);

        int departmentId = Integer.parseInt(request.getParameter("departmentId"));

        repository.updateEmployee(employee, departmentId);

        response.sendRedirect(request.getContextPath() + "/employees");
    }
}
