package nl.thewally.loginmanager.usermanagement.repository;
import nl.thewally.loginmanager.usermanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
