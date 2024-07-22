package com.project.l3.schedular.repository.employee;

import com.project.l3.schedular.model.Employee;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface EmployeeRepository {
    List<Employee> getEmployees();
    Employee getEmployee(int id);
    Employee createEmployee(Employee employee, int departmentId);
    Employee updateEmployee(Employee employee, int departmentId);
    List<Employee> getEmployeesByIds(List<Integer> ids);
    List<Employee> getEmployeesApart(List<Integer> excludedIds);
    void deleteEmployee(int id);
}
