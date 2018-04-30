package nl.thewally.loginmanager.usermanagement.request;

import javax.validation.constraints.NotNull;

public class AddUserRequest {

    @NotNull
    private String sessionId;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private Long groupFk;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getGroupFk() {
        return groupFk;
    }

    public void setGroupFk(Long groupFk) {
        this.groupFk = groupFk;
    }

}
