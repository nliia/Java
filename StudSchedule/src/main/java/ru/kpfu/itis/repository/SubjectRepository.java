package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Group;
import ru.kpfu.itis.model.Subject;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByGroup(Group group);

    List<Subject> findByGroupAndWeekday(Group group, String weekday);

    void deleteByGroup(Group group);
}
