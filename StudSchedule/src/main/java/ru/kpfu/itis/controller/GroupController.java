package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.model.Group;
import ru.kpfu.itis.service.DepartmentService;
import ru.kpfu.itis.service.GroupService;

/**
 * @author Liia
 */
@Controller
public class GroupController {
    private static final String SUCCESS_ADD_MESSAGE = "Added successfully";
    private static final String SUCCESS_DELETE_MESSAGE = "Deleted successfully";
    private static final String ERROR_MESSAGE = "Select department!";

    private DepartmentService departmentService;
    private GroupService groupService;

    @Autowired
    public GroupController(DepartmentService departmentService,
                           GroupService groupService) {
        this.departmentService = departmentService;
        this.groupService = groupService;
    }

    @RequestMapping(value = "/newGroup", method = RequestMethod.POST)
    public String addGroup(@RequestParam("depId") Long id,
                           @RequestParam("number") String number,
                           @RequestParam("head") String head,
                           @RequestParam("headPhone") String headPhone,
                           @RequestParam("amount") int amount,
                           Model model) {
        if (id == null) {
            model.addAttribute("errorGroup", ERROR_MESSAGE);
            return "redirect: /newcat";
        }
        groupService.add(new Group(departmentService.getOne(id), number, head, headPhone, amount));
        model.addAttribute("successGroup", SUCCESS_ADD_MESSAGE);
        return "redirect: /newcat";
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
    public String deleteGroup(@RequestParam("groupId") Long id,
                              Model model) {
        groupService.delete(id);
        model.addAttribute("successGroup", SUCCESS_DELETE_MESSAGE);
        return "redirect: /deletecat";
    }
}
