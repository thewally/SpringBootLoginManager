package nl.thewally.loginmanager.usermanagement.controller;

import nl.thewally.loginmanager.usermanagement.domain.UserGroup;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.UserGroupRepository;
import nl.thewally.loginmanager.usermanagement.errorhandler.FunctionalException;
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

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public ResponseEntity addGroup(@RequestBody UserGroup userGroup) throws FunctionalException {
        userGroupRepository.save(userGroup);
        return new ResponseEntity<>(userGroupRepository.findById(userGroup.getId()), HttpStatus.OK);
    }
}
