package com.project.l3.schedular.repository.department;

import com.project.l3.schedular.model.Department;
import com.project.l3.schedular.persistence.Context;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;

import java.util.List;

@Stateless
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @Override
    public List<Department> getDepartments() {
        // The entity manager is used to manage the context
        // A context is like a container between the database and the application
        // It handles all operation like persisting the entity to the database
        // App -> context -> database
        EntityManager em = Context.getEntityManager();

        return em.createQuery("select d from Department d", Department.class)
                .getResultList();
    }

    @Override
    public Department getDepartment(int id) {
        EntityManager em = Context.getEntityManager();

        return em.find(Department.class, id);
    }

    @Override
    public Department createDepartment(Department department) {
        EntityManager em = Context.getEntityManager();

        em.getTransaction().begin();
        em.persist(department);
        em.getTransaction().commit();

        return department;
    }

    @Override
    public Department updateDepartment(Department department) {
        EntityManager em = Context.getEntityManager();

        em.getTransaction().begin();
        em.merge(department);
        em.getTransaction().commit();

        return department;
    }

    @Override
    public void deleteDepartment(int id) {
        EntityManager em = Context.getEntityManager();

        Department department = em.find(Department.class, id);
        em.remove(department);
    }
}
