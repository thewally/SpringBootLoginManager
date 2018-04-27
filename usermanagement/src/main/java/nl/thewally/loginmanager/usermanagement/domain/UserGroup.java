package nl.thewally.loginmanager.usermanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="USERGROUP")
@TableGenerator(name="tab", initialValue=0, allocationSize=1)
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="tab")
    private Long id;

    @Column(name="GROUPNAME", nullable = false, unique = true)
    private String groupName;

    @Column(name="MAYCREATEUSERS", nullable = false)
    private Boolean mayCreateUsers;

    @Column(name="MAYCREATEGROUPS", nullable = false)
    private Boolean mayCreateGroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
