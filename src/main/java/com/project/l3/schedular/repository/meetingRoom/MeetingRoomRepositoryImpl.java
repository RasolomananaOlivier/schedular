package com.project.l3.schedular.repository.meetingRoom;

import com.project.l3.schedular.model.Department;
import com.project.l3.schedular.model.MeetingRoom;
import com.project.l3.schedular.persistence.Context;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;

import java.util.List;

@Stateless
public class MeetingRoomRepositoryImpl implements  MeetingRoomRepository {
    @Override
    public List<MeetingRoom> getMeetingRooms() {
        EntityManager em = Context.getEntityManager();

        return em.createQuery("select m from MeetingRoom m", MeetingRoom.class)
                .getResultList();
    }

    @Override
    public MeetingRoom getMeetingRoom(int id) {
        EntityManager em = Context.getEntityManager();

        return em.find(MeetingRoom.class, id);
    }

    @Override
    public MeetingRoom createMeetingRoom(MeetingRoom meetingRoom) {
        EntityManager em = Context.getEntityManager();

        em.getTransaction().begin();
        em.persist(meetingRoom);
        em.getTransaction().commit();

        return meetingRoom;
    }

    @Override
    public MeetingRoom updateMeetingRoom(MeetingRoom meetingRoom) {
        EntityManager em = Context.getEntityManager();

        em.getTransaction().begin();
        em.merge(meetingRoom);
        em.getTransaction().commit();

        return meetingRoom;
    }

    @Override
    public void deleteMeetingRoom(int id) {
        EntityManager em = Context.getEntityManager();

        MeetingRoom meetingRoom = em.find(MeetingRoom.class, id);

        em.getTransaction().begin();
        em.remove(meetingRoom);
        em.getTransaction().commit();
    }
}
