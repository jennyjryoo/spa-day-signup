package org.launchcode.spaday.controllers;

import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("add")
    public String addUserForm(Model model) {
        return "user/add";
    }

    @PostMapping
    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {

        if(user.getPassword().equals(verify)) {
            UserData.add(user);
            model.addAttribute("users",UserData.getAll());
            return "user/index";
        }
            model.addAttribute("error","Passwords do not match");
            model.addAttribute("username",user.getUsername());
            model.addAttribute("email",user.getEmail());
            return "user/add";
    }

    @GetMapping("{userId}")
    public String displayUserInformation(Model model, @PathVariable int userId) {
        model.addAttribute("user", UserData.getById(userId));
        return "user/info";
    }
}
