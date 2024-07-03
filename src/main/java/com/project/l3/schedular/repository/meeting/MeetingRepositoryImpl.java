package com.project.l3.schedular.repository.meeting;

import com.project.l3.schedular.model.Employee;
import com.project.l3.schedular.model.Meeting;
import com.project.l3.schedular.model.MeetingRoom;
import com.project.l3.schedular.persistence.Context;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;

import java.util.List;

@Stateless
public class MeetingRepositoryImpl implements MeetingRepository {
    @Override
    public List<Meeting> getMeetings() {
        EntityManager em = Context.getEntityManager();

        return em.createQuery("select m from Meeting m", Meeting.class)
                .getResultList();
    }

    @Override
    public Meeting getMeeting(int id) {
        EntityManager em = Context.getEntityManager();

        return em.find(Meeting.class, id);
    }

    @Override
    public Meeting createMeeting(Meeting meeting, int employId, int roomId) {
        EntityManager em = Context.getEntityManager();

        em.getTransaction().begin();

        Employee employee  = em.find(Employee.class, employId);
        if(employee == null) {
            throw  new IllegalArgumentException("Employee not found with id " + employId);
        }

        MeetingRoom meetingRoom = em.find(MeetingRoom.class, roomId);
        if(meetingRoom == null) {
            throw  new IllegalArgumentException("Meeting room not found with id " + roomId);
        }

        meeting.setRoom(meetingRoom);
        meeting.setOrganizer(employee);

        em.persist(meeting);
        em.getTransaction().commit();

        return meeting;
    }

    @Override
    public Meeting updateMeeting(Meeting meeting, int employId, int roomId) {
        EntityManager em = Context.getEntityManager();

        em.getTransaction().begin();

        Employee employee  = em.find(Employee.class, employId);
        if(employee == null) {
            throw  new IllegalArgumentException("Employee not found with id " + employId);
        }

        MeetingRoom meetingRoom = em.find(MeetingRoom.class, roomId);
        if(meetingRoom == null) {
            throw  new IllegalArgumentException("Meeting room not found with id " + roomId);
        }

        meeting.setRoom(meetingRoom);
        meeting.setOrganizer(employee);

        em.merge(meeting);
        em.getTransaction().commit();

        return meeting;
    }

    @Override
    public void deleteMeeting(int id) {
        EntityManager em = Context.getEntityManager();

        Meeting meeting = em.find(Meeting.class, id);

        em.getTransaction().begin();
        em.remove(meeting);
        em.getTransaction().commit();
    }
}
