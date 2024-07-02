package com.project.l3.schedular.repository.meeting;

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
    public Meeting createMeeting(Meeting meeting) {
        EntityManager em = Context.getEntityManager();

        em.getTransaction().begin();
        em.persist(meeting);
        em.getTransaction().commit();

        return meeting;
    }

    @Override
    public Meeting updateMeeting(Meeting meeting) {
        EntityManager em = Context.getEntityManager();

        em.getTransaction().begin();
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
