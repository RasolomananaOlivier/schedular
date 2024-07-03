package com.project.l3.schedular.repository.agendaItem;

import com.project.l3.schedular.model.AgendaItem;
import com.project.l3.schedular.model.Employee;
import com.project.l3.schedular.model.Meeting;
import com.project.l3.schedular.persistence.Context;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class AgendaItemRepositoryImpl implements AgendaItemRepository {
    @Override
    public List<AgendaItem> getAgendaItems(int meetingId) {
        EntityManager em = Context.getEntityManager();

        TypedQuery<AgendaItem> query = em.createQuery(
                "select a from AgendaItem a where a.meeting.id = :meetingId",
                AgendaItem.class
        );

        query.setParameter("meetingId", meetingId);

        return query.getResultList();
    }

    @Override
    public AgendaItem getAgendaItem(int id) {
        EntityManager em = Context.getEntityManager();

        return em.find(AgendaItem.class, id);
    }

    @Override
    public AgendaItem createAgendaItem(AgendaItem agendaItem, int meetingId, int presenterId) {
        EntityManager em = Context.getEntityManager();

        em.getTransaction().begin();

        Employee employee = em.find(Employee.class, presenterId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found with id " + presenterId);
        }

        Meeting meeting = em.find(Meeting.class, meetingId);
        if (meeting == null) {
            throw new IllegalArgumentException("Meeting not found with id " + meetingId);
        }

        agendaItem.setMeeting(meeting);
        agendaItem.setPresenter(employee);

        em.persist(agendaItem);
        em.getTransaction().commit();

        return agendaItem;
    }

    @Override
    public AgendaItem updateAgendaItem(AgendaItem agendaItem, int meetingId, int presenterId) {
        EntityManager em = Context.getEntityManager();

        em.getTransaction().begin();

        Employee employee = em.find(Employee.class, presenterId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found with id " + presenterId);
        }

        Meeting meeting = em.find(Meeting.class, meetingId);
        if (meeting == null) {
            throw new IllegalArgumentException("Meeting not found with id " + meetingId);
        }

        agendaItem.setMeeting(meeting);
        agendaItem.setPresenter(employee);

        em.merge(agendaItem);
        em.getTransaction().commit();

        return agendaItem;
    }

    @Override
    public void deleteAgendaItem(int id) {
        EntityManager em = Context.getEntityManager();

        AgendaItem agendaItem = em.find(AgendaItem.class, id);

        em.getTransaction().begin();
        em.remove(agendaItem);
        em.getTransaction().commit();
    }
}
