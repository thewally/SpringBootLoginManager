package nl.thewally.loginmanager.usermanagement.controller;

import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.domain.UserGroup;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.UserGroupRepository;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.UserRepository;
import nl.thewally.loginmanager.usermanagement.errorhandler.FunctionalException;
import nl.thewally.loginmanager.usermanagement.errorhandler.Validator;
import nl.thewally.loginmanager.usermanagement.request.AddGroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserGroupController {
    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public ResponseEntity addGroup(@RequestBody AddGroupRequest userGroup) throws FunctionalException {
        validator.validateSessionAvailable(userGroup.getSessionId());
        validator.validateUserMayCreateGroups(userGroup.getSessionId());

        UserGroup userGroupToAdd = new UserGroup();
        userGroupToAdd.setGroupName(userGroup.getGroupName());
        userGroupToAdd.setMayAddUsersToGroup(userGroup.getMayAddUsersToGroup());
        userGroupToAdd.setMayCreateUsers(userGroup.getMayCreateUsers());
        userGroupToAdd.setMayCreateGroups(userGroup.getMayCreateGroups());
        userGroupRepository.save(userGroupToAdd);

        return new ResponseEntity<>(userGroupRepository.findById(userGroupToAdd.getId()), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getGroups/{sessionId}/{groupId}")
    public UserGroup getGroup(@PathVariable String sessionId, @PathVariable Long groupId) throws FunctionalException {
        validator.validateSessionAvailable(sessionId);
        return userGroupRepository.findById(groupId);
    }

    @RequestMapping(value = "/getGroups/{sessionId}")
    public List<UserGroup> getGroups(@PathVariable String sessionId) throws FunctionalException {
        validator.validateSessionAvailable(sessionId);
        return userGroupRepository.findAll();
    }

    @RequestMapping(value = "/removeGroup/{sessionId}/{groupId}")
    public ResponseEntity removeGroup(@PathVariable String sessionId, @PathVariable Long groupId) throws FunctionalException {
        validator.validateSessionAvailable(sessionId);
        validator.validateUserMayCreateGroups(sessionId);

        UserGroup userGroup = userGroupRepository.findById(groupId);
        userGroupRepository.delete(userGroup);

        List<User> users = userRepository.findByGroupFk(groupId);
        for(User user : users) {
            user.setGroupFk(userGroupRepository.findByGroupName("Default").getId());
            userRepository.save(user);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
