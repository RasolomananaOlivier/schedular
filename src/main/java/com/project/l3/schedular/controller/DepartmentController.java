package com.project.l3.schedular.controller;

import java.io.*;
import java.util.List;

import com.project.l3.schedular.model.Department;
import com.project.l3.schedular.repository.department.DepartmentRepository;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

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

    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        request.getRequestDispatcher( "/WEB-INF/views/departments/index.jsp")
                .forward(request, response);
    }
    private  void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher( "/WEB-INF/views/departments/create.jsp")
                .forward(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {}
    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {}
    private void storeDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {}
    private void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {}
}