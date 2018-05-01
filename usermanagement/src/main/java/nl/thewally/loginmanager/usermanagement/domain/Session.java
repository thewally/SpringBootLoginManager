package nl.thewally.loginmanager.usermanagement.domain;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Parameter;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name="SESSION")
@TableGenerator(name="tab", initialValue=0, allocationSize=1)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="tab")
    private Long id;

    @Column(name="CREATIONDATETIME")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @Parameter(name = "databaseZone", value = "Europe/Amsterdam"),
            @Parameter(name = "javaZone", value = "jvm")})
    private DateTime creationDateTime;

    @Column(name="SESSIONID", nullable = false, unique = true)
    private String sessionId;

    @Column(name="USER_FK")
    private Long userFk;

    @Column(name="VALIDUNTIL")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @Parameter(name = "databaseZone", value = "Europe/Amsterdam"),
            @Parameter(name = "javaZone", value = "jvm")})
    private DateTime validUntil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(DateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
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

    public DateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(DateTime validUntil) {
        this.validUntil = validUntil;
    }
}
