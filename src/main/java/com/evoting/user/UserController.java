package com.evoting.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable(value = "id") Long id)
            throws Exception {
        User employee = userRepository.findById(id)
                .orElseThrow(() -> new Exception("USer not found for this id :: " + id));
        return ResponseEntity.ok().body(employee);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable(value = "id") Long id,
                                               @Valid @RequestBody User user) throws Exception {
        User userDb = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found for this id :: " + id));

        userDb.setEmail(user.getEmail());
        userDb.setLastName(user.getLastName());
        userDb.setFirstName(user.getFirstName());
        final User updatedUser = userRepository.save(userDb);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id)
            throws Exception {
        User employee = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found for this id :: " + id));

        userRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
