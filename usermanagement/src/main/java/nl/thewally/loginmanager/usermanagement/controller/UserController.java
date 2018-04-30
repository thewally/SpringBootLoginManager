package nl.thewally.loginmanager.usermanagement.controller;

import nl.thewally.loginmanager.usermanagement.domain.Session;
import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.domain.UserGroup;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.SessionRepository;
import nl.thewally.loginmanager.usermanagement.request.AddUserRequest;
import nl.thewally.loginmanager.usermanagement.request.AddUserToGroupRequest;
import nl.thewally.loginmanager.usermanagement.response.UserResponse;
import nl.thewally.loginmanager.usermanagement.errorhandler.ErrorCode;
import nl.thewally.loginmanager.usermanagement.errorhandler.FunctionalException;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.UserGroupRepository;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.UserRepository;
import nl.thewally.loginmanager.usermanagement.response.responserepository.UserResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private SessionRepository sessionRepository;

    @RequestMapping(value = "/getUsers")
    public List<UserResponse> getUsers() {
        return userResponseRepository.findAll();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody AddUserRequest user) throws FunctionalException {
        if(sessionRepository.findBySessionId(user.getSessionId())==null) {
            throw new FunctionalException(ErrorCode.ERROR1001);
        }

        if(!checkUserPolicy(user.getSessionId()).getMayCreateUsers()) {
            throw new FunctionalException(ErrorCode.ERROR1002);
        }

        User userFound = userRepository.findByUsername(user.getUsername());
        if (userFound != null) {
            throw new FunctionalException(ErrorCode.ERROR0002);
        }

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
        if(sessionRepository.findBySessionId(request.getSessionId())==null) {
            throw new FunctionalException(ErrorCode.ERROR1001);
        }

        if(!checkUserPolicy(request.getSessionId()).getMayCreateGroups()) {
            throw new FunctionalException(ErrorCode.ERROR1002);
        }

        User user = userRepository.findById(request.getUserId());
        user.setGroupFk(request.getGroupId());
        userRepository.save(user);

        return new ResponseEntity<>(userResponseRepository.findById(user.getId()), HttpStatus.OK);
    }

    private UserGroup checkUserPolicy(String sessionId) {
        Session session = sessionRepository.findBySessionId(sessionId);
        User moderator = userRepository.findById(session.getUserFk());
        return userGroupRepository.findById(moderator.getGroupFk());
    }
}
