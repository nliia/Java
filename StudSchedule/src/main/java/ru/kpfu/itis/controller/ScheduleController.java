package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.model.interfaces.ClassesTime;
import ru.kpfu.itis.model.Group;
import ru.kpfu.itis.model.Subject;
import ru.kpfu.itis.service.GroupService;
import ru.kpfu.itis.service.SubjectService;

/**
 * @author Liia
 */

@Controller
public class ScheduleController {
    private GroupService groupService;
    private SubjectService subjectService;
    private final String ERROR_NOT_SELECTED_GROUP_MESSAGE = "Select group!";
    private final String ERROR_SCHEDULE_EXIST_MESSAGE = "Schedule for this group already exists! Delete existing first";
    private final String SUCCESS_ADD_MESSAGE = "Added successfully!";
    private final String SUCCESS_DELETE_MESSAGE = "Deleted successfully!";

    @Autowired
    public ScheduleController(GroupService groupService, SubjectService subjectService) {
        this.groupService = groupService;
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/newSchedule", method = RequestMethod.POST)
    public String addSchedule(@RequestParam("name1") String name1,
                              @RequestParam("name2") String name2,
                              @RequestParam("name3") String name3,
                              @RequestParam("name4") String name4,
                              @RequestParam("groupId") Long groupId,
                              @RequestParam("weekday") String weekday,
                              Model model) {
        if (groupId == null) {
            model.addAttribute("errorSchedule", ERROR_NOT_SELECTED_GROUP_MESSAGE);
            return "redirect: /newchedule";
        }
        Group group = groupService.get(groupId);

        if (!subjectService.getByGroupAndWeekday(group, weekday).isEmpty()) {
            model.addAttribute("errorSchedule", ERROR_SCHEDULE_EXIST_MESSAGE);
            return "redirect: /newschedule";
        }
        subjectService.add(new Subject(name1, ClassesTime.FIRST, group, weekday));
        subjectService.add(new Subject(name2, ClassesTime.SECOND, group, weekday));
        subjectService.add(new Subject(name3, ClassesTime.THIRD, group, weekday));
        subjectService.add(new Subject(name4, ClassesTime.FOURTH, group, weekday));
        model.addAttribute("successSchedule", SUCCESS_ADD_MESSAGE);

        return "redirect: /newschedule";
    }

    @RequestMapping("/deleteSchedule")
    public String deleteSchedule(@RequestParam("groupId") Long groupId,
                                 Model model) {
        subjectService.deleteByGroupId(groupId);
        model.addAttribute("successSchedule", SUCCESS_DELETE_MESSAGE);

        return "redirect: /deletecat";
    }
}
