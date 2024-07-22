package com.project.l3.schedular.controller;

import com.project.l3.schedular.model.Attendance;
import com.project.l3.schedular.model.Employee;
import com.project.l3.schedular.model.Meeting;
import com.project.l3.schedular.repository.attendance.AttendanceRepository;
import com.project.l3.schedular.repository.attendance.AttendanceRepositoryImpl;
import com.project.l3.schedular.repository.department.DepartmentRepository;
import com.project.l3.schedular.repository.department.DepartmentRepositoryImpl;
import com.project.l3.schedular.repository.employee.EmployeeRepository;
import com.project.l3.schedular.repository.employee.EmployeeRepositoryImpl;
import com.project.l3.schedular.repository.meeting.MeetingRepository;
import com.project.l3.schedular.repository.meeting.MeetingRepositoryImpl;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "attendanceController", value = "/attendances/*")
public class AttendanceController extends HttpServlet {
    // Use a stateless bean as a repository to handle business logic
    // It will be injected by the application container
    @EJB
    private EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    @EJB
    private AttendanceRepository attendanceRepository = new AttendanceRepositoryImpl();
    @EJB
    private MeetingRepository meetingRepository = new MeetingRepositoryImpl();

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
                case "/remove":
                    removeResource(request, response);
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
                case "/delete":
                    deleteResource(request, response);
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
        int meetingId = Integer.parseInt(request.getParameter("meetingId"));
        Meeting meeting = meetingRepository.getMeeting(meetingId);

        request.setAttribute("meeting", meeting);
        request.setAttribute("attendances", meeting.getAttendances());

        request.getRequestDispatcher("/WEB-INF/views/attendances/index.jsp")
                .forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int meetingId = Integer.parseInt(request.getParameter("meetingId"));
        Meeting meeting = meetingRepository.getMeeting(meetingId);
        List<Employee> employees = employeeRepository.getEmployeesApart(meeting.getAttendances().stream().map(Attendance::getId).toList());
        request.setAttribute("meeting", meeting);
        request.setAttribute("employees", employees);
        request.setAttribute("freePlaceCount", meeting.getRoom().getCapacity() - meeting.getAttendances().size());

        request.getRequestDispatcher("/WEB-INF/views/attendances/create.jsp")
                .forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int meetingId = Integer.parseInt(request.getParameter("meetingId"));

        Meeting meeting = meetingRepository.getMeeting(meetingId);

        request.setAttribute("meeting", meeting);

        request.getRequestDispatcher("/WEB-INF/views/attendances/edit.jsp")
                .forward(request, response);
    }

    private void removeResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int meetingId = Integer.parseInt(request.getParameter("meetingId"));
        Meeting existingMeeting = meetingRepository.getMeeting(meetingId);

        request.setAttribute("meeting", existingMeeting);

        request.getRequestDispatcher("/WEB-INF/views/attendances/remove.jsp")
                .forward(request, response);
    }

    private void deleteResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Integer> attendanceIds = Arrays.stream(request.getParameterValues("attendanceIds")).map(Integer::parseInt).toList();

        System.out.println(attendanceIds);
        attendanceRepository.deleteAttendances(attendanceIds);

        response.sendRedirect(request.getContextPath() + "/attendances?meetingId=" + request.getParameter("meetingId"));
    }

    private void storeResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int meetingId = Integer.parseInt(request.getParameter("meetingId"));
        Meeting meeting = meetingRepository.getMeeting(meetingId);

        List<Integer> employeeIds = Arrays.stream(request.getParameterValues("employeeIds")).map(Integer::parseInt).toList();
        List<Employee> employees = employeeRepository.getEmployeesByIds(employeeIds);
        List<Attendance> newAttendances = new ArrayList<>();
        for (Employee employee : employees) {
            Attendance attendance = new Attendance();
            attendance.setEmployee(employee);
            attendance.setMeeting(meeting);
            attendance.setStatus("invited");
            newAttendances.add(attendance);
        }

        meeting.setAttendances(Stream.concat(
                        meeting.getAttendances().stream(),
                        newAttendances.stream()
                ).collect(Collectors.toList())
        );

        meetingRepository.updateMeeting(meeting);

        response.sendRedirect(request.getContextPath() + "/attendances?meetingId=" + meetingId);
    }

    private void updateResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int meetingId = Integer.parseInt(request.getParameter("meetingId"));
        Meeting meeting = meetingRepository.getMeeting(meetingId);
        Map<Integer, String> statusMap = new HashMap<>();


        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("attendance_") && paramName.endsWith("_status")) {
                int attendanceId = Integer.parseInt(paramName.split("_")[1]);
                String paramValue = request.getParameter(paramName);
                statusMap.put(attendanceId, paramValue);
            }
        }

        for (Attendance attendance : meeting.getAttendances()) {
            if (statusMap.containsKey(attendance.getId())) {
                attendance.setStatus(statusMap.get(attendance.getId()));
            }
        }

        meetingRepository.updateMeeting(meeting);

        response.sendRedirect(request.getContextPath() + "/attendances?meetingId=" + meetingId);
    }
}
