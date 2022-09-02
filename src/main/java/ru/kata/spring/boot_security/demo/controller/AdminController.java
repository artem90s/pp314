package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String adminView(Model model) {
        List<User> userList = userService.allUsers();
        model.addAttribute("users", userList);
        return "admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }
    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "new";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id")Long id, Model model){
        model.addAttribute("user", userService.findUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update (@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "edit";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute ("user") User user){
        userService.deleteUser(user.getId());
        return "redirect:/admin";

    }

}
