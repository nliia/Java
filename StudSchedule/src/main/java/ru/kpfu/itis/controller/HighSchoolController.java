package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.model.HighSchool;
import ru.kpfu.itis.service.HighSchoolService;

/**
 * @author Liia
 */
@Controller
public class HighSchoolController {
    private static final String SUCCESS_ADD_MESSAGE = "Added successfully";
    private static final String SUCCESS_DELETE_MESSAGE = "Deleted successfully";
    private static final String ERROR_MESSAGE = "Error. Category was not added";

    private HighSchoolService highSchoolService;

    @Autowired
    public HighSchoolController(HighSchoolService highSchoolService) {
        this.highSchoolService = highSchoolService;
    }

    @RequestMapping(value = "/newHighSchool", method = RequestMethod.POST)
    public String addHighSchool(@ModelAttribute("highSchool") HighSchool highSchool,
                                Model model,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errorHighSchool", ERROR_MESSAGE);
            return "redirect: /newcat";
        }

        highSchoolService.add(highSchool);
        model.addAttribute("successHighSchool", SUCCESS_ADD_MESSAGE);
        return "redirect: /newcat";
    }

    @RequestMapping(value = "/deleteHighSchool", method = RequestMethod.POST)
    public String deleteHighSchool(@ModelAttribute("highSchId") Long id,
                                   Model model) {
        highSchoolService.delete(id);
        model.addAttribute("successHighSchool", SUCCESS_DELETE_MESSAGE);
        return "redirect: /deletecat";
    }


}
