package com.project.l3.schedular.controller;

import com.project.l3.schedular.model.Employee;
import com.project.l3.schedular.model.MeetingRoom;
import com.project.l3.schedular.repository.meetingRoom.MeetingRoomRepository;
import com.project.l3.schedular.repository.meetingRoom.MeetingRoomRepositoryImpl;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "meetingRoomController", value = "/rooms/*")
public class MeetingRoomController extends HttpServlet {
    // Use a stateless bean as a repository to handle business logic
    // It will be injected by the application container
    @EJB
    private MeetingRoomRepository repository = new MeetingRoomRepositoryImpl();

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

        request.setAttribute("rooms", repository.getMeetingRooms());

        request.getRequestDispatcher("/WEB-INF/views/rooms/index.jsp")
                .forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/rooms/create.jsp")
                .forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int roomId = Integer.parseInt(request.getParameter("roomId"));

        MeetingRoom room = repository.getMeetingRoom(roomId);
        request.setAttribute("room", room);

        request.getRequestDispatcher("/WEB-INF/views/rooms/edit.jsp")
                .forward(request, response);
    }

    private void deleteResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int roomId = Integer.parseInt(request.getParameter("roomId"));

        repository.deleteMeetingRoom(roomId);

        response.sendRedirect(request.getContextPath() + "/rooms");
    }

    private void storeResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MeetingRoom room = MeetingRoom.fromRequest(request);

        repository.createMeetingRoom(room);

        response.sendRedirect(request.getContextPath() + "/rooms");
    }

    private void updateResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MeetingRoom room = MeetingRoom.fromRequest(request);

        repository.updateMeetingRoom(room);

        response.sendRedirect(request.getContextPath() + "/rooms");
    }
}
