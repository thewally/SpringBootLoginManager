package nl.thewally.loginmanager.usermanagement.repository;
import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.domain.UserLite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    @Query(value ="select u.id, u.username from user u where u.id=(:id)", nativeQuery = true)
    UserLite findInfoForId(@Param("id") Long id);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
