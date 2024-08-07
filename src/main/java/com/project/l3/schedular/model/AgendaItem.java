package com.project.l3.schedular.model;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;

@Entity
@Table(name = "agenda_items")
public class AgendaItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "presenter_id")
    private Employee presenter;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Employee getPresenter() {
        return presenter;
    }

    public void setPresenter(Employee presenter) {
        this.presenter = presenter;
    }

    public static AgendaItem fromRequest(HttpServletRequest request) {
        AgendaItem item = new AgendaItem();

        item.setDescription(request.getParameter("description"));

        if(request.getParameter("id") != null){
            item.setId(Integer.parseInt(request.getParameter("id")));
        }

        return item;
    }
}
