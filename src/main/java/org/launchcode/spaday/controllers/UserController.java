package org.launchcode.spaday.controllers;

import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    private static boolean errorMessage = false;
    @GetMapping("add")
    public String addUserForm(Model model) {
        if(errorMessage=true) {
            String error = "Passwords did not match. Please try again.";
            model.addAttribute("error", error);
            errorMessage=false;
            return "user/add";
        }
        return "user/add";
    }

    @PostMapping("")
    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {

        if(user.getPassword().equals(verify)) {
            return "user/index";
        }
        errorMessage=true;
        return "user/add";
    }
}
