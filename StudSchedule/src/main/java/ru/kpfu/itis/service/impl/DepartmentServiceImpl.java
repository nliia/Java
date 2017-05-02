package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.model.Department;
import ru.kpfu.itis.model.HighSchool;
import ru.kpfu.itis.repository.DepartmentRepository;
import ru.kpfu.itis.repository.HighSchoolRepository;
import ru.kpfu.itis.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liia
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{

    private DepartmentRepository departmentRepository;
    private HighSchoolRepository highSchoolRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, HighSchoolRepository highSchoolRepository) {
        this.departmentRepository = departmentRepository;
        this.highSchoolRepository = highSchoolRepository;
    }

    public List<Department> getByHighSchool(Long id) {
        return departmentRepository.findByHighSchool(highSchoolRepository.findOne(id));
    }

    @Override
    public void add(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.delete(id);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getOne(Long id) {
        return departmentRepository.getOne(id);
    }


}
