package nl.thewally.loginmanager.usermanagement.controller;

import nl.thewally.loginmanager.usermanagement.domain.Session;
import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.errorhandler.FunctionalException;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.SessionRepository;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.UserRepository;
import nl.thewally.loginmanager.usermanagement.errorhandler.Validator;
import nl.thewally.loginmanager.usermanagement.response.SessionResponse;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.time.Instant;

@RestController
public class SessionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private Validator validator;

    @Autowired
    private SessionResponse sessionResponse;

    @RequestMapping(value = "/login" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loginUser(@RequestBody User user) throws FunctionalException {

        long currentTimeStamp = Instant.now().toEpochMilli();
        int randomNumber = new SecureRandom().nextInt((900000000)+100000000);
        String sessionId = currentTimeStamp + "" + randomNumber;

        validator.validateUsernameAndPasswordCorrect(user.getUsername(), user.getPassword());

        Session session = new Session();
        session.setCreationDateTime(DateTime.now());
        session.setSessionId(sessionId);
        session.setUserFk(userRepository.findByUsername(user.getUsername()).getId());
        session.setValidUntil(DateTime.now().plusHours(1));
        sessionRepository.save(session);

        return new ResponseEntity<>(sessionResponse.generateSessionResponse(session), HttpStatus.OK);
    }
}
