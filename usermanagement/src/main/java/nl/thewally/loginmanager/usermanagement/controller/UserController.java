package nl.thewally.loginmanager.usermanagement.controller;

import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.errorhandler.Validator;
import nl.thewally.loginmanager.usermanagement.request.AddUserRequest;
import nl.thewally.loginmanager.usermanagement.request.AddUserToGroupRequest;
import nl.thewally.loginmanager.usermanagement.response.UserResponse;
import nl.thewally.loginmanager.usermanagement.errorhandler.FunctionalException;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.UserGroupRepository;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.UserRepository;
import nl.thewally.loginmanager.usermanagement.response.responserepository.UserResponseRepository;
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
    private UserResponseRepository userResponseRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private Validator validator;

    @RequestMapping(value = "/getUsers/{sessionId}")
    public List<UserResponse> getUsers(@PathVariable String sessionId) throws FunctionalException {
        validator.validateSessionAvailable(sessionId);
        return userResponseRepository.findAll();
    }

    @RequestMapping(value = "/getUsers/{sessionId}/{userId}")
    public UserResponse getUser(@PathVariable String sessionId, @PathVariable Long userId) throws FunctionalException {
        validator.validateSessionAvailable(sessionId);
        return userResponseRepository.findById(userId);
    }

    @RequestMapping(value = "/removeUser/{sessionId}/{userId}")
    public ResponseEntity removeUser(@PathVariable String sessionId, @PathVariable Long userId) throws FunctionalException {
        validator.validateSessionAvailable(sessionId);
        validator.validateUserMayCreateUsers(sessionId);

        User user = userRepository.findById(userId);
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody AddUserRequest user) throws FunctionalException {
        validator.validateSessionAvailable(user.getSessionId());
        validator.validateUserMayCreateUsers(user.getSessionId());
        validator.validateUserAlreadyAvailable(user.getUsername());

        User userToAdd = new User();
        userToAdd.setUsername(user.getUsername());
        userToAdd.setPassword(user.getPassword());
        if (user.getGroupFk() != null) {
            userToAdd.setGroupFk(user.getGroupFk());
        } else {
            userToAdd.setGroupFk(userGroupRepository.findByGroupName("Default").getId());
        }

        userRepository.save(userToAdd);

        return new ResponseEntity<>(userResponseRepository.findById(userToAdd.getId()), HttpStatus.OK);
    }

    @RequestMapping(value = "/addUserToGroup", method = RequestMethod.POST)
    public ResponseEntity addUserToGroup(@RequestBody AddUserToGroupRequest request) throws FunctionalException {
        validator.validateSessionAvailable(request.getSessionId());
        validator.validateUserMayAddUsersToGroup(request.getSessionId());
        validator.validateGroupIsAvailable(request.getGroupId());

        User user = userRepository.findById(request.getUserId());
        user.setGroupFk(request.getGroupId());
        userRepository.save(user);

        return new ResponseEntity<>(userResponseRepository.findById(user.getId()), HttpStatus.OK);
    }
}
