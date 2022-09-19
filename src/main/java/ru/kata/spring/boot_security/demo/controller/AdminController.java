//package ru.kata.spring.boot_security.demo.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.model.Role;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
//import ru.kata.spring.boot_security.demo.service.UserServiceImpl;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Set;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//    private final UserServiceImpl userServiceImpl;
//
//    private final RoleServiceImpl roleServiceImpl;
//
//    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
//        this.userServiceImpl = userServiceImpl;
//        this.roleServiceImpl = roleServiceImpl;
//    }
//
//    @GetMapping
//    public String adminView(Model model) {
//        model.addAttribute("users", userServiceImpl.usersList());
//        model.addAttribute("roles", roleServiceImpl.allRoles());
//        model.addAttribute("newUser", new User());
//        return "admin";
//    }
//
//    @GetMapping("/new")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//        return "new";
//    }
//    @PostMapping("/new")
//    public String create(@ModelAttribute("newUser") User user, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return "new";
//        }
//        userServiceImpl.saveUser(user);
//        return "redirect:/admin";
//    }
//    @GetMapping("/{id}/edit")
//    public String edit(@PathVariable("id")Long id, Model model){
//        model.addAttribute("user", userServiceImpl.getUser(id));
//        return "edit";
//    }
//
//    @PatchMapping("/{id}")
//    public String update (@PathVariable("id") Long id, User user, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return "edit";
//        }
//        userServiceImpl.updateUser(user);
//        return "redirect:/admin";
//    }
//    @DeleteMapping("/{id}")
//    public String delete(@ModelAttribute ("id") Long id){
//        userServiceImpl.deleteUser(id);
//        return "redirect:/admin";
//
//    }
//
//}
