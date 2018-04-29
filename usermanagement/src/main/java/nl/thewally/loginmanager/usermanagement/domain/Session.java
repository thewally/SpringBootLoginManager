package nl.thewally.loginmanager.usermanagement.domain;

import javax.persistence.*;

@Entity
@Table(name="SESSION")
@TableGenerator(name="tab", initialValue=0, allocationSize=1)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="tab")
    private Long id;

    @Column(name="SESSIONID", nullable = false, unique = true)
    private String sessionId;

    @Column(name="USER_FK")
    private Long userFk;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserFk() {
        return userFk;
    }

    public void setUserFk(Long userFk) {
        this.userFk = userFk;
    }
}
