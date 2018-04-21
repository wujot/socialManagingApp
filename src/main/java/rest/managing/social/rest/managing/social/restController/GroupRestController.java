package rest.managing.social.rest.managing.social.restController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.managing.social.model.Friend;
import rest.managing.social.model.User;
import rest.managing.social.repository.FriendRepository;
import rest.managing.social.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class GroupRestController {

    private UserRepository userRepository;
    private FriendRepository friendRepository;

    public GroupRestController(UserRepository userRepository, FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    @GetMapping("/api/groups")
    public List<Friend> getList() {
        // returns list of groups
        List<Friend> groups = friendRepository.findAll();
        return groups;
    }

    @GetMapping("/api/groups/{id}")
    public ResponseEntity<Friend> getFriend(@PathVariable Integer id) {
        Optional<Friend> optionalFriend = friendRepository.findById(id);

        if (optionalFriend.isPresent()) {
            Friend presentFriend = optionalFriend.get();
            return ResponseEntity.ok(presentFriend);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/groups")
    public Friend insert(@RequestBody Friend friend) {
        Friend tempGroup = friend;

        friendRepository.save(tempGroup);
        return tempGroup;
    }

    @PutMapping("/api/groups/{id}")
    public void update(@PathVariable int id, @RequestBody Friend friend) {
        Optional<Friend> optionalFriend = friendRepository.findById(id);

        if (optionalFriend.isPresent()) {
            Friend updateFriend = optionalFriend.get();

            // update
            updateFriend.setId(id);
            updateFriend.setUsername(friend.getUsername());

            friendRepository.save(updateFriend);
        }
    }

    @DeleteMapping("/api/groups/{id}")
    public void delete(@PathVariable int id) {
        Optional<Friend> optionalFriend = friendRepository.findById(id);

        if (optionalFriend.isPresent()) {
            Friend presentFriend = optionalFriend.get();
            friendRepository.delete(presentFriend);
        }
    }
}
