package nl.thewally.loginmanager.usermanagement.domain;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="USER")
@TableGenerator(name="tab", initialValue=0, allocationSize=1)
public class UserLite {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="tab")
    private Long id;

    @NotNull
    @Column(name="USERNAME")
    private String username;

    @Column(name="GROUP_FK")
    private Long groupFk;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getGroupFk() {
        return groupFk;
    }

    public void setGroupFk(Long groupFk) {
        this.groupFk = groupFk;
    }

}
