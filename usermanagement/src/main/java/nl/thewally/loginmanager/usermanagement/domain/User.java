package nl.thewally.loginmanager.usermanagement.domain;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="USER")
@TableGenerator(name="tab", initialValue=0, allocationSize=1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="tab")
    private Long id;

    @Column(name="USERNAME", nullable = false)
    private String username;

    @Column(name="PASSWORD", nullable = false)
    private String password;

    @Column(name="GROUP_FK", nullable = false)
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        byte[] encodedBytes = Base64.encodeBase64(password.getBytes());
        this.password = new String(encodedBytes);
    }

    public Long getGroupFk() {
        return groupFk;
    }

    public void setGroupFk(Long groupFk) {
        this.groupFk = groupFk;
    }

}
