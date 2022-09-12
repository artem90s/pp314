package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    public boolean saveUser(User user);
    public boolean deleteUser(Long id);
    public User updateUser(User user);
    public User getUser(Long id);

    public List<User> usersList();
    User findByEmail(String email);
}
