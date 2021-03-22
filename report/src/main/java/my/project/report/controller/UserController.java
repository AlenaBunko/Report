package my.project.report.controller;

import my.project.report.lib.exception.CostsNotFoundException;
import my.project.report.model.Costs;
import my.project.report.model.User;
import my.project.report.service.ICostsService;
import my.project.report.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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

        String login = ((User) (((UsernamePasswordAuthenticationToken) principal).getPrincipal())).getLogin();
        if (login != null) {
            try {
                User user = userService.findUserByLogin(login, Boolean.TRUE);
                view.setViewName("userPage");
                view.addObject(USER, user);
            } catch (Exception e) {
                e.printStackTrace();
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
    public ModelAndView addPurchase(@RequestParam String product, @RequestParam Long purchaseAmount,
                                    @RequestParam String date, @RequestParam Integer warrantyPeriod,
                                    @SessionAttribute(USER) User user, ModelAndView view) throws IOException {
        Costs costs = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date d = formatter.parse(date);
            costs = costsService.createCosts(user, product, purchaseAmount, d, warrantyPeriod);
            user.getCosts().add(costs);
            view.setViewName("redirect:/user/userPage");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            view.setViewName("error");
        }
        return view;
    }

}


