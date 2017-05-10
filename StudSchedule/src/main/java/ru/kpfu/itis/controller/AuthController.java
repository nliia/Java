package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.AuthForm;
import ru.kpfu.itis.util.UserForm;
import ru.kpfu.itis.util.UserFormToUserTransformer;

import javax.validation.Valid;
import java.util.function.Function;

/**
 * @author Liia
 */
@Controller
public class AuthController {

    private final UserService userService;
    private final Function<UserForm, User> transformer;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
        transformer = new UserFormToUserTransformer();
    }

    @RequestMapping("/sign_in")
    public String getSignIn(@RequestParam(value = "error", required = false) Boolean error,
                            Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", error);
        }
        model.addAttribute("authForm", new AuthForm());
        return "sign_in";
    }

    @RequestMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("user", new UserForm());
        return "sign_up";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("user") @Valid UserForm userForm,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new UserForm());
            model.addAttribute("error", "Password too short(from 7 to 20 symbols) or email is incorrect");
            return "sign_up";
        }
        if (userService.getByEmail(userForm.getEmail()) != null) {
            model.addAttribute("user", new UserForm());
            model.addAttribute("error", "User with this email already exists!");
            return "sign_up";
        }
        userService.add(transformer.apply(userForm));
        model.addAttribute("afterSignUp", "true");
        model.addAttribute("authForm", new AuthForm());
        return "sign_in";
    }

}