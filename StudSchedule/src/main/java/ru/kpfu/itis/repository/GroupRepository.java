package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Department;
import ru.kpfu.itis.model.Group;

import java.util.List;

/**
 * @author Liia
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{
    List<Group> findByDepartment(Department department);
}
