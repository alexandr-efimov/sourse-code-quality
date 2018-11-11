package software.sigma.principles.exercise5;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;

interface UserRepository {
    User getUser(String id);

    User getUserByUsername(String username);
}

@Log
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String id) {
        User user = userRepository.getUser(id);

        if (user == null) {
            return null;
        } else {
            saveToCache(user);
            return user;
        }
    }

    public User findUserByUsername(String username){
        User userByUsername = userRepository.getUserByUsername(username);
        return userByUsername;
    }

    private void saveToCache(User user) {
        log.info("Save to cache: " + user.getId());
    }

}

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
class User {
    String id;
    String username;
}