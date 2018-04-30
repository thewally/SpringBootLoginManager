package nl.thewally.loginmanager.usermanagement.domain.domainrepository;
import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {

    @Query(value ="select u.id, u.username from user u where u.id=(:id)", nativeQuery = true)
    UserResponse findInfoForId(@Param("id") Long id);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
