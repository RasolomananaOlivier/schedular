package com.project.l3.schedular.repository.employee;

import com.project.l3.schedular.model.Department;
import com.project.l3.schedular.model.Employee;
import com.project.l3.schedular.persistence.Context;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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
    public List<Employee> getEmployeesByIds(List<Integer> ids) {
        EntityManager em = Context.getEntityManager();

        if (ids == null || ids.isEmpty()) {
            return em.createQuery("SELECT e FROM Employee e", Employee.class)
                    .getResultList();
        }

        String jpql = "SELECT e FROM Employee e WHERE e.id IN :ids";

        TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);

        query.setParameter("ids", ids);

        return query.getResultList();
    }

    @Override
    public List<Employee> getEmployeesApart(List<Integer> excludedIds) {
        EntityManager em = Context.getEntityManager();

        if (excludedIds == null || excludedIds.isEmpty()) {
            // If there are no excluded IDs, return all employees
            return em.createQuery("SELECT e FROM Employee e", Employee.class)
                    .getResultList();
        }

        // Constructing the JPQL query with a WHERE clause to exclude specific IDs
        String jpql = "SELECT e FROM Employee e WHERE e.id NOT IN :excludedIds";

        // Creating a typed query
        TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);

        // Setting the parameter for the excluded IDs
        query.setParameter("excludedIds", excludedIds);

        // Executing the query and returning the result list
        return query.getResultList();
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
