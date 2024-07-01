package com.project.l3.schedular.repository.department;

import com.project.l3.schedular.model.Department;
import jakarta.ejb.*;

import java.util.List;

@Local
public interface DepartmentRepository {
    List<Department> getDepartments();
    Department getDepartment(int id);
    Department createDepartment(Department department);
    Department updateDepartment(Department department);
    void deleteDepartment(int id);
}
