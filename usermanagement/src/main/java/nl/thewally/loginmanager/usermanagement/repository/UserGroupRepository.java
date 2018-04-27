package nl.thewally.loginmanager.usermanagement.repository;

import nl.thewally.loginmanager.usermanagement.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, String> {
    UserGroup findByGroupName(String groupName);
}
