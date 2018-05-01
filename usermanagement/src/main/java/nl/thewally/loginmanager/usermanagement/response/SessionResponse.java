package nl.thewally.loginmanager.usermanagement.response;

import nl.thewally.loginmanager.usermanagement.domain.Session;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SessionResponse {
    @Autowired
    private SessionRepository sessionRepository;

    private Map<String, Object> response = new HashMap<>();

    public Map<String, Object> generateSessionResponse() {
        List<Session> sessionList = sessionRepository.findAll();
        for(Session session:sessionList) {
            addResponseItem(session);
        }
        return response;
    }

    public Map<String, Object> generateSessionResponse(Session session) {
        addResponseItem(session);
        return response;
    }

    private void addResponseItem(Session session) {
        response.put("sessionId", session.getSessionId());
        response.put("validUntil", session.getValidUntil().toString());
    }
}
