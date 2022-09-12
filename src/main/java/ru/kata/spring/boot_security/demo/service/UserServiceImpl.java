package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService,UserDetailsService {
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException(String.format("Эмейл '%s' не найден",email));
        }
//        user.getAuthorities().size();
        return user;
    }


    @Override
    public boolean saveUser(User user) {
        User userFromBD = userRepository.findByEmail(user.getUsername());
        if(userFromBD != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUser(Long id) {
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User updateUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getUser(Long id) {
        Optional<User>user=userRepository.findById(id);
        return user.orElse(new User());
    }

    @Override
    public List<User> usersList() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail (String email) {
        return userRepository.findByEmail(email);
    }
}
