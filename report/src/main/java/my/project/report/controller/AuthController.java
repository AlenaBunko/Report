package my.project.report.controller;

import my.project.report.lib.dto.RegisterFormDTO;
import my.project.report.lib.dto.UserDTO;
import my.project.report.lib.exception.UserNotFoundException;
import my.project.report.model.User;
import my.project.report.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class AuthController {

    private final IUserService userService;

    @Autowired
    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public ModelAndView registerForm(ModelAndView view) {
        view.setViewName("register");
        view.addObject("registerForm", new RegisterFormDTO());
        return view;
    }

    @PostMapping("/registration")
    public ModelAndView register(@ModelAttribute("registerForm") RegisterFormDTO registerForm,
                                 ModelAndView view, HttpServletRequest request) {
        try {
            User user = userService.createUser(registerForm);
            request.login(user.getUsername(), registerForm.getPassword());
            view.setViewName("redirect:/user/userPage");
            view.addObject("user", user);
        } catch (Exception e) {
            view.setViewName("register");
            view.addObject("loginError", e.getMessage());
        }
        return view;

    }

}
