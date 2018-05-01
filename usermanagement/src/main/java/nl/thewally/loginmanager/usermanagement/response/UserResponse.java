package nl.thewally.loginmanager.usermanagement.response;

import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserResponse {

    @Autowired
    private UserRepository userRepository;

    private Map<String, Object> response = new HashMap<>();

    public Map<String, Object> generateUserResponse() {
        List<User> userList = userRepository.findAll();
        for(User user:userList) {
            addResponseItem(user);
        }
        return response;
    }

    public Map<String, Object> generateUserResponse(User user) {
        addResponseItem(user);
        return response;
    }

    public Map<String, Object> generateUserResponse(Long userId) {
        User user = userRepository.findById(userId);
        addResponseItem(user);
        return response;
    }

    private void addResponseItem(User user) {
        response.put("id", user.getId());
        response.put("username", user.getUsername());
        response.put("groupFk", user.getGroupFk());
    }
}
