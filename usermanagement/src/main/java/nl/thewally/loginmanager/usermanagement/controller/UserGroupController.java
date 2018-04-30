package nl.thewally.loginmanager.usermanagement.controller;

import nl.thewally.loginmanager.usermanagement.domain.UserGroup;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.UserGroupRepository;
import nl.thewally.loginmanager.usermanagement.errorhandler.FunctionalException;
import nl.thewally.loginmanager.usermanagement.errorhandler.Validator;
import nl.thewally.loginmanager.usermanagement.request.AddGroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserGroupController {
    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private Validator validator;

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public ResponseEntity addGroup(@RequestBody AddGroupRequest userGroup) throws FunctionalException {

        validator.validateUserMayCreateGroups(userGroup.getSessionId());

        UserGroup userGroupToAdd = new UserGroup();
        userGroupToAdd.setGroupName(userGroup.getGroupName());
        userGroupToAdd.setMayCreateUsers(userGroup.getMayCreateUsers());
        userGroupToAdd.setMayCreateGroups(userGroup.getMayCreateGroups());
        userGroupRepository.save(userGroupToAdd);

        return new ResponseEntity<>(userGroupRepository.findById(userGroupToAdd.getId()), HttpStatus.OK);
    }
}
