package nl.thewally.loginmanager.usermanagement.domain.domainrepository;

import nl.thewally.loginmanager.usermanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User findById(Long id);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    List<User> findByGroupFk(Long groupFk);
}
