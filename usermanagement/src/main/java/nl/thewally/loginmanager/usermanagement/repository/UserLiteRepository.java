package nl.thewally.loginmanager.usermanagement.repository;
import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.domain.UserLite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserLiteRepository extends JpaRepository<UserLite, String> {

    UserLite findById(Long id);

}
