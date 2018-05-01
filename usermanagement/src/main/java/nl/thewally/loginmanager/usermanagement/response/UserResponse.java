package nl.thewally.loginmanager.usermanagement.response;

import nl.thewally.loginmanager.usermanagement.domain.User;
import nl.thewally.loginmanager.usermanagement.domain.domainrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserResponse {

    @Autowired
    private UserRepository userRepository;

    private List<ResponseItem> responseList = new ArrayList<>();

    public List generateUserResponse() {
        responseList.clear();
        List<User> userList = userRepository.findAll();
        for(User user:userList) {
            addResponseItem(user);
        }
        return responseList;
    }

    public ResponseItem generateUserResponse(Long userId) {
        User user = userRepository.findById(userId);
        return generateUserResponse(user);
    }

    public ResponseItem generateUserResponse(User user) {
        responseList.clear();
        return addResponseItem(user);
    }

    private ResponseItem addResponseItem(User user) {
        ResponseItem responseItem = new ResponseItem();
        responseItem.setId(user.getId());
        responseItem.setUsername(user.getUsername());
        responseItem.setGroupFk(user.getGroupFk());
        responseList.add(responseItem);
        return responseItem;
    }

    class ResponseItem {
        private Long id;
        private String username;
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
}
