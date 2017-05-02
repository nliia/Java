package ru.kpfu.itis.service;

import ru.kpfu.itis.model.Group;
import ru.kpfu.itis.model.Subject;

import java.util.List;

/**
 * @author Liia
 */
public interface SubjectService {
    List<List> getAllByGroup(Long groupId);

    void add(Subject subject);

    void delete(Long id);

    List<Subject> getByGroupAndWeekday(Group group, String weekday);

    void deleteByGroupId(Long id);
}
