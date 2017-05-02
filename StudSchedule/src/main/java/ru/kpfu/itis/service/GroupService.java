package ru.kpfu.itis.service;

import ru.kpfu.itis.model.Group;

import java.util.List;

/**
 * @author Liia
 */
public interface GroupService {
    List<Group> getByDepartment(Long id);

    void add(Group group);

    void delete(Long id);

    List<Group> getAll();

    Group get(Long id);
}
