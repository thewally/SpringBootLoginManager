package nl.thewally.loginmanager.usermanagement.controller;

import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.domain.UserGroup;
import nl.thewally.loginmanager.usermanagement.errorhandler.FunctionalErrorHandler;
import nl.thewally.loginmanager.usermanagement.repository.UserGroupRepository;
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

    @Autowired
    private UserGroupRepository userGroupRepository;

    @RequestMapping(value = "/getUsers")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Map addUser(@RequestBody Map<String, String> map) {
        User user = new User();
        user.setUsername(map.get("username"));
        String password = map.get("password");
        String passwordValidate = String.valueOf(map.get("passwordValidate"));
        if(password.equals(passwordValidate)) {
            user.setPassword(password);
        } else {
            return new FunctionalErrorHandler("002", "Password is incorrect").get();
        }

        if(map.get("groupName")!=null) {
            UserGroup userGroup = userGroupRepository.findByGroupName(map.get("groupName"));
            if (userGroup != null) {
                user.setGroupFk(userGroup.getId());
            }
        }

        if(user.getGroupFk()==null) {
            user.setGroupFk(userGroupRepository.findByGroupName("Default").getId());
        }

        userRepository.save(user);
        return Collections.singletonMap("userId", String.valueOf(user.getId()));
    }
}
