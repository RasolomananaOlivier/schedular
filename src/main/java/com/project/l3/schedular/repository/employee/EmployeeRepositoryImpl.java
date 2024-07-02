package com.project.l3.schedular.repository.employee;

import com.project.l3.schedular.model.Department;
import com.project.l3.schedular.model.Employee;
import com.project.l3.schedular.persistence.Context;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;

import java.util.List;

@Stateless
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public List<Employee> getEmployees() {
        EntityManager em = Context.getEntityManager();

        return em.createQuery("select e from Employee e", Employee.class)
                .getResultList();
    }

    @Override
    public Employee getEmployee(int id) {
        EntityManager em = Context.getEntityManager();

        return em.find(Employee.class, id);
    }

    @Override
    public Employee createEmployee(Employee employee, int departmentId) {
        EntityManager em = Context.getEntityManager();
        em.getTransaction().begin();

        Department department = em.find(Department.class, departmentId);
        if (department == null) {
            throw new IllegalArgumentException("Department not found with ID: " + departmentId);
        }

        employee.setDepartment(department);

        em.persist(employee);
        em.getTransaction().commit();

        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee, int departmentId) {
        EntityManager em = Context.getEntityManager();
        em.getTransaction().begin();

        Department department = em.find(Department.class, departmentId);
        if (department == null) {
            throw new IllegalArgumentException("Department not found with ID: " + departmentId);
        }

        employee.setDepartment(department);

        em.merge(employee);
        em.getTransaction().commit();

        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        EntityManager em = Context.getEntityManager();

        Employee employee = em.find(Employee.class, id);

        em.getTransaction().begin();
        em.remove(employee);
        em.getTransaction().commit();
    }
}
