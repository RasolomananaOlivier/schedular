package com.project.l3.schedular.repository.attendance;

import com.project.l3.schedular.model.Attendance;
import com.project.l3.schedular.model.Meeting;
import com.project.l3.schedular.persistence.Context;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AttendanceRepositoryImpl implements AttendanceRepository{
    @Override
    public List<Attendance> getAttendances() {
        EntityManager em = Context.getEntityManager();

        return em.createQuery("select a from Attendance a", Attendance.class)
                .getResultList();
    }

    @Override
    public Attendance getAttendance(int id) {
        EntityManager em = Context.getEntityManager();

        return em.find(Attendance.class, id);
    }

    @Override
    public Attendance createAttendance(Attendance attendance) {
        EntityManager em = Context.getEntityManager();

        em.getTransaction().begin();
        em.persist(attendance);
        em.getTransaction().commit();

        return attendance;
    }

    @Override
    public Attendance updateAttendance(Attendance attendance) {
        EntityManager em = Context.getEntityManager();

        em.getTransaction().begin();
        em.merge(attendance);
        em.getTransaction().commit();

        return attendance;
    }

    @Override
    public void deleteAttendance(int id) {
        EntityManager em = Context.getEntityManager();

        Attendance attendance = em.find(Attendance.class, id);

        em.getTransaction().begin();
        em.remove(attendance);
        em.getTransaction().commit();
    }
}
