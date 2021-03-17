package my.project.report.controller;

import my.project.report.lib.dto.CostsDTO;
import my.project.report.lib.dto.RegisterFormDTO;
import my.project.report.lib.exception.CostsNotFoundException;
import my.project.report.lib.exception.UserNotFoundException;
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
    public String addPurchase(@RequestParam(value= "product") String product, @RequestParam(value= "amount") Long purchaseAmount,
                              @RequestParam(value= "date") LocalDate date, @RequestParam(value= "warranty") Integer warrantyPeriod,
                              @ModelAttribute(USER) User user) throws IOException {
        Costs costs= null;
        try {
            costs = costsService.createCosts(user, product, purchaseAmount, date, warrantyPeriod);
            user.getCosts().add(costs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/user/userPage";
    }


    @ResponseBody
    @PostMapping("/updatePurchase")
    public String updateCar(@RequestParam Long id, @RequestParam String product, @RequestParam Long purchaseAmount,
                            @RequestParam LocalDate date, @RequestParam Integer warrantyPeriod) {
        Costs costs= null;
        try {
            costs = costsService.updateCosts(id, product, purchaseAmount, date, warrantyPeriod);
        } catch (IOException | CostsNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/user/userPage";
    }
}


