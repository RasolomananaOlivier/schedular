package com.project.l3.schedular.controller;

import com.project.l3.schedular.model.Employee;
import com.project.l3.schedular.model.Meeting;
import com.project.l3.schedular.repository.employee.EmployeeRepository;
import com.project.l3.schedular.repository.meeting.MeetingRepository;
import com.project.l3.schedular.repository.meetingRoom.MeetingRoomRepository;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "meetingController", value = "/meetings/*")
public class MeetingController extends HttpServlet {
    // Use a stateless bean as a repository to handle business logic
    // It will be injected by the application container
    @EJB
    private MeetingRepository meetingRepository;
    @EJB
    private EmployeeRepository employeeRepository;
    @EJB
    private MeetingRoomRepository roomRepository;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        request.setAttribute("meetings", meetingRepository.getMeetings());

        request.getRequestDispatcher("/WEB-INF/views/meetings/index.jsp")
                .forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("employees", employeeRepository.getEmployees());
        request.setAttribute("rooms", roomRepository.getMeetingRooms());

        request.getRequestDispatcher("/WEB-INF/views/meetings/create.jsp")
                .forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int meetingId = Integer.parseInt(request.getParameter("meetingId"));

        Meeting meeting = meetingRepository.getMeeting(meetingId);

        request.setAttribute("meeting", meeting);
        request.setAttribute("employees", employeeRepository.getEmployees());
        request.setAttribute("rooms", roomRepository.getMeetingRooms());

        request.getRequestDispatcher("/WEB-INF/views/meetings/edit.jsp")
                .forward(request, response);
    }

    private void deleteResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int meetingId = Integer.parseInt(request.getParameter("meetingId"));

        meetingRepository.deleteMeeting(meetingId);

        response.sendRedirect(request.getContextPath() + "/meetings");
    }

    private void storeResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Meeting meeting = Meeting.fromRequest(request);

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        int roomId = Integer.parseInt(request.getParameter("roomId"));

        meetingRepository.createMeeting(meeting, employeeId, roomId);

        response.sendRedirect(request.getContextPath() + "/meetings");
    }

    private void updateResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Meeting meeting = Meeting.fromRequest(request);

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        int roomId = Integer.parseInt(request.getParameter("roomId"));

        meetingRepository.updateMeeting(meeting, employeeId, roomId);

        response.sendRedirect(request.getContextPath() + "/meetings");
    }
}
