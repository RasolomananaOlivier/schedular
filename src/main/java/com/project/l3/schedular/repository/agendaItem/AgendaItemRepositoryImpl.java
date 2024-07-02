package com.project.l3.schedular.repository.agendaItem;

import com.project.l3.schedular.model.AgendaItem;
import com.project.l3.schedular.persistence.Context;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AgendaItemRepositoryImpl implements AgendaItemRepository {
    @Override
    public List<AgendaItem> getAgendaItems() {
        EntityManager em = Context.getEntityManager();

        return em.createQuery("select a from AgendaItem a", AgendaItem.class)
                .getResultList();
    }

    @Override
    public AgendaItem getAgendaItem(int id) {
        EntityManager em = Context.getEntityManager();

        return em.find(AgendaItem.class, id);
    }

    @Override
    public AgendaItem createAgendaItem(AgendaItem agendaItem) {
        EntityManager em = Context.getEntityManager();

        em.getTransaction().begin();
        em.persist(agendaItem);
        em.getTransaction().commit();

        return agendaItem;
    }

    @Override
    public AgendaItem updateAgendaItem(AgendaItem agendaItem) {
        EntityManager em = Context.getEntityManager();

        em.getTransaction().begin();
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
