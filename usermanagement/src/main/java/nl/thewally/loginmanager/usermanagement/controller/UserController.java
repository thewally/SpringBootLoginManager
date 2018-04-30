package nl.thewally.loginmanager.usermanagement.controller;

import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.errorhandler.ErrorCode;
import nl.thewally.loginmanager.usermanagement.errorhandler.FunctionalException;
import nl.thewally.loginmanager.usermanagement.repository.UserGroupRepository;
import nl.thewally.loginmanager.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity addUser(@RequestBody User user) throws FunctionalException {

        if(user.getGroupFk()==null) {
            user.setGroupFk(userGroupRepository.findByGroupName("Default").getId());
        }

        User userFound = userRepository.findByUsername(user.getUsername());
        if(userFound!=null) {
            throw new FunctionalException(ErrorCode.ERROR0002);
        }

        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
