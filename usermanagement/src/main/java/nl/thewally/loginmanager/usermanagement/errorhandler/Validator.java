package nl.thewally.loginmanager.usermanagement.errorhandler;

import nl.thewally.loginmanager.usermanagement.domain.Session;
import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.domain.UserGroup;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.SessionRepository;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.UserGroupRepository;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    public void validateUsernameAndPasswordCorrect(String username, String password) throws FunctionalException {
        User userFound = userRepository.findByUsernameAndPassword(username, password);
        if(userFound==null) {
            throw new FunctionalException(ErrorCode.ERROR0001);
        }
    }

    public void validateUserAlreadyAvailable(String username) throws FunctionalException {
        User userFound = userRepository.findByUsername(username);
        if (userFound != null) {
            throw new FunctionalException(ErrorCode.ERROR0002);
        }
    }

    public void validateSessionAvailable(String sessionId) throws FunctionalException {
        if (sessionRepository.findBySessionId(sessionId) == null)
        {
            throw new FunctionalException(ErrorCode.ERROR1001);
        }
    }

    public void validateUserMayCreateUsers(String sessionId) throws FunctionalException {
        if(!checkUserPolicy(sessionId).getMayCreateUsers()) {
            throw new FunctionalException(ErrorCode.ERROR1002);
        }
    }

    public void validateUserMayCreateGroups(String sessionId) throws FunctionalException {
        if(!checkUserPolicy(sessionId).getMayCreateGroups()) {
            throw new FunctionalException(ErrorCode.ERROR1003);
        }
    }

    public void validateUserMayAddUsersToGroup(String sessionId) throws FunctionalException {
        if(!checkUserPolicy(sessionId).getMayAddUsersToGroup()) {
            throw new FunctionalException(ErrorCode.ERROR1004);
        }
    }

    public void validateGroupIsAvailable(Long groupId) throws FunctionalException {
        if(userGroupRepository.findById(groupId)==null) {
            throw new FunctionalException(ErrorCode.ERROR2001);
        }
    }

    private UserGroup checkUserPolicy(String sessionId) {
        Session session = sessionRepository.findBySessionId(sessionId);
        User moderator = userRepository.findById(session.getUserFk());
        return userGroupRepository.findById(moderator.getGroupFk());
    }
}
