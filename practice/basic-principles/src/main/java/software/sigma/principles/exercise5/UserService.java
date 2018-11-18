package software.sigma.principles.exercise5;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;

interface UserRepository {
    User getUser(String id);

    User getUserByUsername(String username);

    User find(String email);
}

@Log
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(String id) {
        User user = userRepository.getUser(id);

        if (user == null) {
            return null;
        } else {
            resetPassword(user);
            return user;
        }
    }

    public User findUserByUsername(String username){
        User userByUsername = userRepository.getUserByUsername(username);
        return userByUsername;
    }

    public User find(String email){
        User user = userRepository.find(email);
        return user;
    }

    private void resetPassword(User user) {
        log.info("Reset password fo user: " + user.getId());
    }

}

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
class User {
    String id;
    String email;
    String username;
}