package com.project.l3.schedular.model;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;

@Entity
@Table(name = "meeting_rooms")
public class MeetingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String location;

    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static MeetingRoom fromRequest(HttpServletRequest request) {
        MeetingRoom room = new MeetingRoom();

        if(request.getParameter("id") != null) {
            room.setId(Integer.parseInt(request.getParameter("id")));
        }

        room.setName(request.getParameter("name"));
        room.setLocation(request.getParameter("location"));
        room.setCapacity(Integer.parseInt(request.getParameter("capacity")));

        return room;
    }
}
