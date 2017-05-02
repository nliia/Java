package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.model.HighSchool;
import ru.kpfu.itis.service.DepartmentService;
import ru.kpfu.itis.service.GroupService;
import ru.kpfu.itis.service.HighSchoolService;
import ru.kpfu.itis.service.SubjectService;

import java.time.DayOfWeek;

/**
 * @author Liia
 */
@Controller
public class GetPageController {
    private DepartmentService departmentService;
    private GroupService groupService;
    private HighSchoolService highSchoolService;
    private SubjectService subjectService;

    @Autowired
    public GetPageController(DepartmentService departmentService,
                             GroupService groupService,
                             HighSchoolService highSchoolService, SubjectService subjectService) {
        this.departmentService = departmentService;
        this.groupService = groupService;
        this.highSchoolService = highSchoolService;
        this.subjectService = subjectService;
    }

    @RequestMapping("/newcat")
    public String getNewCatPage(Model model) {
        addAttrToModelOfNewCat(model);
        return "new_cat";
    }

    @RequestMapping("/deletecat")
    public String getDeletePage(Model model) {
        model.addAttribute("cities", highSchoolService.getAllCities());
        return "delete_cat";
    }

    @RequestMapping("/newschedule")
    public String getNewSchedulePage(Model model) {
        model.addAttribute("cities", highSchoolService.getAllCities());
        model.addAttribute("weekdays", DayOfWeek.values());
        return "new_sched";
    }

    private void addAttrToModelOfNewCat(Model model) {
        model.addAttribute("cities", highSchoolService.getAllCities());
        model.addAttribute("highSchools", highSchoolService.getALl());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("highSchool", new HighSchool());
    }
}
