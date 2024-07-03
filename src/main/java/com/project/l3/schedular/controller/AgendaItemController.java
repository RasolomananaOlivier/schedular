package com.project.l3.schedular.controller;

import com.project.l3.schedular.model.AgendaItem;
import com.project.l3.schedular.repository.agendaItem.AgendaItemRepository;
import com.project.l3.schedular.repository.employee.EmployeeRepository;
import com.project.l3.schedular.repository.meeting.MeetingRepository;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "agendaItemController", value = "/agendas/*")
public class AgendaItemController extends HttpServlet {
    // Use a stateless bean as a repository to handle business logic
    // It will be injected by the application container
    @EJB
    private MeetingRepository meetingRepository;
    @EJB
    private EmployeeRepository employeeRepository;
    @EJB
    private AgendaItemRepository agendaItemRepository;

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
        int meetingId = Integer.parseInt(request.getParameter("meetingId"));

        request.setAttribute(
                "agendas",
                agendaItemRepository.getAgendaItems(meetingId)
        );
        request.setAttribute("meeting", meetingRepository.getMeeting(meetingId));

        request.getRequestDispatcher("/WEB-INF/views/agendas/index.jsp")
                .forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int meetingId = Integer.parseInt(request.getParameter("meetingId"));

        request.setAttribute("employees", employeeRepository.getEmployees());
        request.setAttribute("meeting", meetingRepository.getMeeting(meetingId));

        request.getRequestDispatcher("/WEB-INF/views/agendas/create.jsp")
                .forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int agendaItemId = Integer.parseInt(request.getParameter("agendaItemId"));

        AgendaItem agendaItem = agendaItemRepository.getAgendaItem(agendaItemId);

        request.setAttribute("agendaItem", agendaItem);
        request.setAttribute("employees", employeeRepository.getEmployees());

        request.getRequestDispatcher("/WEB-INF/views/agendas/edit.jsp")
                .forward(request, response);
    }

    private void deleteResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int agendaItemId = Integer.parseInt(request.getParameter("agendaItemId"));

        int meetingId = agendaItemRepository.getAgendaItem(agendaItemId)
                .getMeeting().getId();

        agendaItemRepository.deleteAgendaItem(agendaItemId);

        response.sendRedirect(request.getContextPath() + "/agendas?meetingId=" + meetingId);
    }

    private void storeResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AgendaItem agendaItem = AgendaItem.fromRequest(request);

        int presenterId = Integer.parseInt(request.getParameter("presenterId"));
        int meetingId = Integer.parseInt(request.getParameter("meetingId"));

        agendaItemRepository.createAgendaItem(agendaItem, meetingId, presenterId);

        response.sendRedirect(request.getContextPath() + "/agendas?meetingId=" + meetingId);
    }

    private void updateResource(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AgendaItem agendaItem = AgendaItem.fromRequest(request);

        int presenterId = Integer.parseInt(request.getParameter("presenterId"));
        int meetingId = Integer.parseInt(request.getParameter("meetingId"));

        agendaItemRepository.updateAgendaItem(agendaItem, meetingId, presenterId);

        response.sendRedirect(request.getContextPath() + "/agendas?meetingId=" + meetingId);
    }
}
