package nl.thewally.loginmanager.usermanagement.request;

import javax.validation.constraints.NotNull;

public class AddGroupRequest {

    @NotNull
    private String sessionId;
    @NotNull
    private String groupName;
    @NotNull
    private Boolean mayAddUsersToGroup;
    @NotNull
    private Boolean mayCreateUsers;
    @NotNull
    private Boolean mayCreateGroups;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Boolean getMayAddUsersToGroup() {
        return mayAddUsersToGroup;
    }

    public void setMayAddUsersToGroup(Boolean mayAddUsersToGroup) {
        this.mayAddUsersToGroup = mayAddUsersToGroup;
    }

    public Boolean getMayCreateUsers() {
        return mayCreateUsers;
    }

    public void setMayCreateUsers(Boolean mayCreateUsers) {
        this.mayCreateUsers = mayCreateUsers;
    }

    public Boolean getMayCreateGroups() {
        return mayCreateGroups;
    }

    public void setMayCreateGroups(Boolean mayCreateGroups) {
        this.mayCreateGroups = mayCreateGroups;
    }
}
