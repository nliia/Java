package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.model.Department;
import ru.kpfu.itis.model.Group;
import ru.kpfu.itis.model.HighSchool;
import ru.kpfu.itis.service.DepartmentService;
import ru.kpfu.itis.service.GroupService;
import ru.kpfu.itis.service.HighSchoolService;
import ru.kpfu.itis.service.SubjectService;

import java.util.List;
import java.util.Set;

/**
 * @author Liia
 */

@Controller
public class MainController {
    private DepartmentService departmentService;
    private GroupService groupService;
    private HighSchoolService highSchoolService;
    private SubjectService subjectService;

    @Autowired
    public MainController(DepartmentService departmentService,
                          GroupService groupService,
                          HighSchoolService highSchoolService, SubjectService subjectService) {
        this.departmentService = departmentService;
        this.groupService = groupService;
        this.highSchoolService = highSchoolService;
        this.subjectService = subjectService;
    }

    @RequestMapping("/")
    public String getHome(Model model) {
        Set<String> cities = highSchoolService.getAllCities();
        model.addAttribute("cities", cities);
        return "home";
    }

    @RequestMapping("/high_schools")
    public
    @ResponseBody
    List<HighSchool> getHighSchools(@RequestParam("city") String city) {
        return highSchoolService.getByCity(city);
    }

    @RequestMapping("/departments")
    public
    @ResponseBody
    List<Department> getDepartments(@RequestParam("highschId") Long highschId) {
        return departmentService.getByHighSchool(highschId);
    }

    @RequestMapping("/groups")
    public
    @ResponseBody
    List<Group> getGroups(@RequestParam("depId") Long depId) {
        return groupService.getByDepartment(depId);
    }

    @RequestMapping("/schedule")
    public String getSchedule(@RequestParam("groupId") Long groupId,
                              Model model) {

        List week = subjectService.getAllByGroup(groupId);

        model.addAttribute("monday", week.get(0));
        model.addAttribute("tuesday", week.get(1));
        model.addAttribute("wednesday", week.get(2));
        model.addAttribute("thursday", week.get(3));
        model.addAttribute("friday", week.get(4));
        model.addAttribute("cities", highSchoolService.getAllCities());
        return "home";
    }

}
