package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AdminRestController {
    private RoleService roleService;
    private UserService userService;

    AdminRestController(RoleService roleService, UserService userService){
        this.roleService=roleService;
        this.userService=userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers(){
        List<User>allUsers=userService.usersList();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User user=userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity <User> addNewUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUser (@RequestBody User user){
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<User> oneOfUsers(Authentication auth){
        return new ResponseEntity<>((User) auth.getPrincipal(), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.allRoles(), HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> role(@PathVariable("id") Long id){
        return new ResponseEntity<>(roleService.getRole(id), HttpStatus.OK);
    }
}
