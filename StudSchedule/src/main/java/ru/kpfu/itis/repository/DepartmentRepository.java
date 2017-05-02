package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Department;
import ru.kpfu.itis.model.HighSchool;

import java.util.List;

/**
 * @author Liia
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
    List<Department> findByHighSchool(HighSchool highSchool);
}
