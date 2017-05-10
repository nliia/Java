package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.model.Group;
import ru.kpfu.itis.model.Subject;
import ru.kpfu.itis.repository.GroupRepository;
import ru.kpfu.itis.repository.SubjectRepository;
import ru.kpfu.itis.service.SubjectService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Liia
 */

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;
    private GroupRepository groupRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, GroupRepository groupRepository) {
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public List<List> getAllByGroup(Long groupId) {
        List<Subject> groupSubjects = subjectRepository.findByGroup(groupRepository.findOne(groupId));
        List<Subject> monday = new ArrayList<>();
        List<Subject> tuesday = new ArrayList<>();
        List<Subject> wednesday = new ArrayList<>();
        List<Subject> thursday = new ArrayList<>();
        List<Subject> friday = new ArrayList<>();

        for (Subject sub : groupSubjects) {
            switch (sub.getWeekday()) {
                case "MONDAY":
                    monday.add(sub);
                    break;
                case "TUESDAY":
                    tuesday.add(sub);
                    break;
                case "WEDNESDAY":
                    wednesday.add(sub);
                    break;
                case "THURSDAY":
                    thursday.add(sub);
                    break;
                case "FRIDAY":
                    friday.add(sub);
                    break;
            }
        }

        List<List> week = new ArrayList<>();
        week.add(monday);
        week.add(tuesday);
        week.add(wednesday);
        week.add(thursday);
        week.add(friday);

        return week;
    }

    @Override
    public void add(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void delete(Long id) {
        subjectRepository.delete(id);
    }

    @Override
    public List<Subject> getByGroupAndWeekday(Group group, String weekday) {
        return subjectRepository.findByGroupAndWeekday(group, weekday);
    }

    @Override
    public void deleteByGroupId(Long id) {
        subjectRepository.deleteByGroup(groupRepository.getOne(id));
    }
}
