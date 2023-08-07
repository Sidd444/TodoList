package TodoList.TodoList.Services;

import TodoList.TodoList.Models.User;
import TodoList.TodoList.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerNewUser(String username, String password, String email) {
        // Implement user registration logic and password hashing if needed
        User newUser = new User(username, password, email);
        return userRepository.save(newUser);
    }

    public String authenticateUser(String username, String password) {
        // Implement user authentication logic and return a JWT token
        // (Implementation of JWT authentication is beyond the scope of this code snippet)
        return "JWT_TOKEN";
    }
}


