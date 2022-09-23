package ru.kata.spring.boot_security.demo;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class InitUsersAndRoles {

    private final UserService userService;
    private final RoleService roleService;

    public InitUsersAndRoles(UserService userRepository, RoleService roleRepository) {
        this.userService = userRepository;
        this.roleService = roleRepository;
    }

    @PostConstruct
    public void createUsers() {
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");

        roleService.save(user);
        roleService.save(admin);

        User user1 = new User("loc90@rambler.ru", "100");//"$2a$12$cUEyfImF3j3QSc3IKseYb.tK8o5Sw8eyPo0m4SqcLlBsAOtaGhV2y"

        user1.setRole(roleService.findByName("ROLE_ADMIN"));
        user1.setRole(roleService.findByName("ROLE_USER"));
        user1.setFirstName("Artem");
        user1.setLastName("S");
        userService.saveUser(user1);

    }
}

