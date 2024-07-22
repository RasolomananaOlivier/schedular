package com.project.l3.schedular.model;

import com.project.l3.schedular.util.DateParser;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "meetings")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date startTime;

    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private MeetingRoom room;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Employee organizer;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendances;

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Employee organizer) {
        this.organizer = organizer;
    }

    public MeetingRoom getRoom() {
        return room;
    }

    public void setRoom(MeetingRoom room) {
        this.room = room;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public static Meeting fromRequest(HttpServletRequest request){
        Meeting meeting = new Meeting();

        if(request.getParameter("id") != null) {
            meeting.setId(Integer.parseInt(request.getParameter("id")));
        }

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String date   = request.getParameter("date");

        meeting.setStartTime(DateParser.fromDateWithHour(date, startTime));
        meeting.setEndTime(DateParser.fromDateWithHour(date, endTime));

        return meeting;
    }
}
