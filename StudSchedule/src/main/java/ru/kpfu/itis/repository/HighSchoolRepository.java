package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.HighSchool;

import java.util.List;

/**
 * @author Liia
 */

@Repository
public interface HighSchoolRepository extends JpaRepository<HighSchool, Long>{

   List<HighSchool> findByCity(String city);
}
