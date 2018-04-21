package rest.managing.social.rest.managing.social.restController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.managing.social.model.User;
import rest.managing.social.repository.FriendRepository;
import rest.managing.social.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {

    private UserRepository userRepository;
    private FriendRepository friendRepository;

    public UserRestController(UserRepository userRepository, FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    @GetMapping("/api/users")
    public List<User> getList(@RequestParam(required = false) String name) {
        // returns list of users
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User presentUser = optionalUser.get();
            return ResponseEntity.ok(presentUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/users")
    public User insert(@RequestBody User user) {
        User tempUser = user;

        userRepository.save(tempUser);
        return user;
    }

    @PutMapping("/api/users/{id}")
    public void update(@PathVariable int id, @RequestBody User user) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User updateUser = optionalUser.get();

            // update
            updateUser = user;
            userRepository.save(updateUser);
        }
    }

    @DeleteMapping("/api/users/{id}")
    public void delete(@PathVariable int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User presentUser = optionalUser.get();
            userRepository.delete(presentUser);
        }
    }

}
