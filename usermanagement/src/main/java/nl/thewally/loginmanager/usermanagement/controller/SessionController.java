package nl.thewally.loginmanager.usermanagement.controller;

import nl.thewally.loginmanager.usermanagement.domain.Session;
import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.errorhandler.ErrorCode;
import nl.thewally.loginmanager.usermanagement.errorhandler.FunctionalErrorHandler;
import nl.thewally.loginmanager.usermanagement.errorhandler.FunctionalException;
import nl.thewally.loginmanager.usermanagement.repository.SessionRepository;
import nl.thewally.loginmanager.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

@RestController
public class SessionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @RequestMapping(value = "/login" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map loginUser(@RequestBody User user) throws FunctionalException {

        long currentTimeStamp = Instant.now().toEpochMilli();
        long randomNumber = new Random().nextLong();
        String sessionId = currentTimeStamp + "" + randomNumber;

        User userFound = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(userFound==null) {
            throw new FunctionalException(ErrorCode.ERROR0001);
        }
        Session session = new Session();
        session.setSessionId(sessionId);
        session.setUserFk(user.getId());
        sessionRepository.save(session);

        return Collections.singletonMap("sessionId", sessionId);
    }
}
