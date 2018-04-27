package nl.thewally.loginmanager.usermanagement.controller;

import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.errorhandler.FunctionalErrorHandler;
import nl.thewally.loginmanager.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/getUsers")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Map addUser(@RequestBody Map<String, Object> map) {
        User user = new User();
        user.setUsername(String.valueOf(map.get("username")));
        String password = String.valueOf(map.get("password"));
        String passwordValidate = String.valueOf(map.get("passwordValidate"));
        if(password.equals(passwordValidate)) {
            user.setPassword(password);
        } else {
            return new FunctionalErrorHandler("002", "Password is incorrect").get();
        }
        userRepository.save(user);
        return Collections.singletonMap("userId", String.valueOf(user.getId()));
    }
}
