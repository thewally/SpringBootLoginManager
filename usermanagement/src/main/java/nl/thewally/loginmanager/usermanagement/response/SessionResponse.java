package nl.thewally.loginmanager.usermanagement.response;

import nl.thewally.loginmanager.usermanagement.domain.Session;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionResponse {
    @Autowired
    private SessionRepository sessionRepository;

    private Map<String, String> response = new HashMap<>();

    public SessionResponse() {
        List<Session> sessionList = sessionRepository.findAll();
        for(Session session:sessionList) {
            addResponseItem(session);
        }
    }

    public SessionResponse(Session session) {
        addResponseItem(session);
    }

    private void addResponseItem(Session session) {
        response.put("sessionId", session.getSessionId());
        response.put("validUntil", session.getValidUntil().toString());
    }

    public Map<String, String> getResponse() {
        return response;
    }
}
