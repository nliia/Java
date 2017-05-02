package ru.kpfu.itis.service;

import ru.kpfu.itis.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getByHighSchool(Long id);

    void add(Department department);

    void delete(Long id);

    List<Department> getAll();

    Department getOne(Long id);
}
