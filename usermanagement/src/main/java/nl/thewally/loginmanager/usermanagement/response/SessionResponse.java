package nl.thewally.loginmanager.usermanagement.response;

import nl.thewally.loginmanager.usermanagement.domain.Session;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SessionResponse {
    @Autowired
    private SessionRepository sessionRepository;

    private List<ResponseItem> responseList = new ArrayList<>();

    public List generateUserResponse() {
        responseList.clear();
        List<Session> sessionList = sessionRepository.findAll();
        for(Session session:sessionList) {
            addResponseItem(session);
        }
        return responseList;
    }

    public ResponseItem generateSessionResponse(Session session) {
        responseList.clear();
        return addResponseItem(session);
    }

    private ResponseItem addResponseItem(Session session) {
        ResponseItem responseItem = new ResponseItem();
        responseItem.setSessioId(session.getSessionId());
        responseItem.setValidUntil(session.getValidUntil().toString());
        responseList.add(responseItem);
        return responseItem;
    }

    class ResponseItem {
        private String sessioId;
        private String validUntil;

        public String getSessioId() {
            return sessioId;
        }

        public void setSessioId(String sessioId) {
            this.sessioId = sessioId;
        }

        public String getValidUntil() {
            return validUntil;
        }

        public void setValidUntil(String validUntil) {
            this.validUntil = validUntil;
        }
    }
}
