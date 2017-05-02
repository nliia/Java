package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.model.Group;
import ru.kpfu.itis.repository.DepartmentRepository;
import ru.kpfu.itis.repository.GroupRepository;
import ru.kpfu.itis.service.GroupService;

import java.util.List;

/**
 * @author Liia
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, DepartmentRepository departmentRepository) {
        this.groupRepository = groupRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Group> getByDepartment(Long id) {
        return groupRepository.findByDepartment(departmentRepository.findOne(id));
    }

    @Override
    public void add(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void delete(Long id) {
        groupRepository.delete(id);
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group get(Long id) {
        return groupRepository.getOne(id);
    }
}
