package evoting.app.demo.controllers;

import evoting.app.demo.model.User;
import evoting.app.demo.persistence.UserRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    // standard constructors

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepo.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepo.save(user);
    }
}
