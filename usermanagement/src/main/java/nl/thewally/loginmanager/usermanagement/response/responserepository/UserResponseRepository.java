package nl.thewally.loginmanager.usermanagement.response.responserepository;
import nl.thewally.loginmanager.usermanagement.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserResponseRepository extends JpaRepository<UserResponse, String> {

    UserResponse findById(Long id);

}
