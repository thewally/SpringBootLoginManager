package nl.thewally.loginmanager.usermanagement.controller;

import nl.thewally.loginmanager.usermanagement.domain.Session;
import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.errorhandler.FunctionalException;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.SessionRepository;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.UserRepository;
import nl.thewally.loginmanager.usermanagement.errorhandler.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Collections;
import java.util.Random;

@RestController
public class SessionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private Validator validator;

    @RequestMapping(value = "/login" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loginUser(@RequestBody User user) throws FunctionalException {

        long currentTimeStamp = Instant.now().toEpochMilli();
        long randomNumber = new Random().nextLong();
        String sessionId = currentTimeStamp + "" + randomNumber;

        validator.validateUsernameAndPasswordCorrect(user.getUsername(), user.getPassword());

        Session session = new Session();
        session.setSessionId(sessionId);
        session.setUserFk(userRepository.findByUsername(user.getUsername()).getId());
        sessionRepository.save(session);

        return new ResponseEntity<>(Collections.singletonMap("sessionId", sessionId), HttpStatus.OK);
    }
}
