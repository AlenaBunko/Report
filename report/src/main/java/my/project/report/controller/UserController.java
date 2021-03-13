package my.project.report.controller;

import my.project.report.model.User;
import my.project.report.service.ICostsService;
import my.project.report.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = {UserController.USER})

public class UserController {

    static final String USER = "user";

    private final IUserService userService;

    private final ICostsService costsService;

    @Autowired
    public UserController(IUserService userService, ICostsService costsService) {
        this.userService = userService;
        this.costsService = costsService;
    }


    @GetMapping("/userPage")
    public ModelAndView userPage(Principal principal, ModelAndView view) {
        String login = ((User)(((UsernamePasswordAuthenticationToken) principal).getPrincipal())).getLogin();
        if (login != null) {
            try {
                User user = userService.findUserByLogin(login, Boolean.TRUE);
                view.setViewName("userPage");
                view.addObject(USER, user);
            } catch (Exception e) {
                view.addObject("error", e.getMessage());
                view.setViewName("login");
            }
        } else {
            view.setViewName("login");
        }
        return view;
    }

    @ResponseBody
    @PostMapping("/addPurchase")
    public ModelAndView addPurchase(@RequestParam String product, @RequestParam Long purchaseAmount, @RequestParam LocalDate date,
                                    @RequestParam String warrantyPeriod, @SessionAttribute(USER) User user, ModelAndView view) throws IOException {
        view.setViewName("redirect:/user/userPage");
        costsService.createCosts(user, product, purchaseAmount, date, warrantyPeriod);
        return view;
    }
}


