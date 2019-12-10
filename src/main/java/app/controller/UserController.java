package app.controller;

import app.entity.User;
import app.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public User findByEmail(@RequestParam String email) {
        return this.userRepository.findByEmail(email);
    }
}
