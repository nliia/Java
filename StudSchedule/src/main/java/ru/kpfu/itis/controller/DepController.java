package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.model.Department;
import ru.kpfu.itis.service.DepartmentService;
import ru.kpfu.itis.service.GroupService;
import ru.kpfu.itis.service.HighSchoolService;
import ru.kpfu.itis.service.SubjectService;

/**
 * @author Liia
 */

@Controller
public class DepController {

    private static final String SUCCESS_ADD_MESSAGE = "Added successfully";
    private static final String SUCCESS_DELETE_MESSAGE = "Deleted successfully";
    private static final String ERROR_MESSAGE = "Select high school!";

    private DepartmentService departmentService;
    private GroupService groupService;
    private HighSchoolService highSchoolService;
    private SubjectService subjectService;

    @Autowired
    public DepController(DepartmentService departmentService,
                         GroupService groupService,
                         HighSchoolService highSchoolService, SubjectService subjectService) {
        this.departmentService = departmentService;
        this.groupService = groupService;
        this.highSchoolService = highSchoolService;
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/newDep", method = RequestMethod.POST)
    public String addDepartment(@RequestParam("highSchoolId") Long id,
                                @RequestParam("name") String name,
                                Model model) {
        if (id == null) {
            model.addAttribute("errorDepartment", ERROR_MESSAGE);
            return "redirect: /newcat";
        }
        departmentService.add(new Department(highSchoolService.getOne(id), name));
        model.addAttribute("successDepartment", SUCCESS_ADD_MESSAGE);
        return "redirect: /newcat";
    }

    @RequestMapping(value = "/deleteDep", method = RequestMethod.POST)
    public String deleteDepartment(@RequestParam("depId") Long id,
                                   Model model) {
        departmentService.delete(id);

        model.addAttribute("successDepartment", SUCCESS_DELETE_MESSAGE);
        return "redirect: /deletecat";
    }
}
