package nl.thewally.loginmanager.usermanagement.controller;

import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.domain.UserLite;
import nl.thewally.loginmanager.usermanagement.errorhandler.ErrorCode;
import nl.thewally.loginmanager.usermanagement.errorhandler.FunctionalException;
import nl.thewally.loginmanager.usermanagement.repository.UserGroupRepository;
import nl.thewally.loginmanager.usermanagement.repository.UserLiteRepository;
import nl.thewally.loginmanager.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLiteRepository userLiteRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @RequestMapping(value = "/getUsers")
    public List<UserLite> getUsers() {
        return userLiteRepository.findAll();
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

        return new ResponseEntity<>(userLiteRepository.findById(user.getId()), HttpStatus.OK);
    }
}
